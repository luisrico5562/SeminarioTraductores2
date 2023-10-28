package logica;

import java.util.*;

public class AnalizadorSintactico {
    
    public String[][] sintactico(Stack pilaLex, String[][] analizadoLex, int largoEntrada) {
        
        // Variable para guardar los estados de la pila
        String[][] textoPila = new String[300][2];
        
        Stack pila = new Stack();
        pila.push("$");
        textoPila[0][0] = "" + pila.peek();
        pila.push("0");
        textoPila[0][0] += pila.peek();
        
        boolean estadoAceptacion = false;
        
        // Tabla LR
        /*
        int[][] tablaLR1 = {
            {2,0, 0,1},
            {0,0,-1,0},
            {0,3,-3,0},
            {2,0, 0,4},
            {0,0,-2,0}};
        */
        
        TablaSintactico tablasin = new TablaSintactico();
        
        // id = 0
        // + = 5
        int fila = 0;
        int columna = 0;
        int salida = 0;
        
        String peek = "";
        int posTextoPila = 1;
        int posAnalizadoLex = 0;
        while(estadoAceptacion == false) {
            
            // Obteniendo la columna según el tipo de entrada
            peek = String.valueOf(pilaLex.peek());

            if (peek.equals("0")) {
                columna = 0;
            } else if (peek.equals("1")) {
                columna = 1;
            } else if (peek.equals("2")) {
                columna = 2;
            } else if (peek.equals("3")) {
                columna = 3;
            } else if (peek.equals("4")) {
                columna = 4;
            } else if (peek.equals("5")) {
                columna = 5;
            } else if (peek.equals("6")) {
                columna = 6;
            } else if (peek.equals("7")) {
                columna = 7;
            } else if (peek.equals("8")) {
                columna = 8;
            } else if (peek.equals("9")) {
                columna = 9;
            } else if (peek.equals("10")) {
                columna = 10;
            } else if (peek.equals("11")) {
                columna = 11;
            } else if (peek.equals("12")) {
                columna = 12;
            } else if (peek.equals("13")) {
                columna = 13;
            } else if (peek.equals("14")) {
                columna = 14;
            } else if (peek.equals("15")) {
                columna = 15;
            } else if (peek.equals("16")) {
                columna = 16;
            } else if (peek.equals("17")) {
                columna = 17;
            } else if (peek.equals("18")) {
                columna = 18;
            } else if (peek.equals("19")) {
                columna = 19;
            } else if (peek.equals("20")) {
                columna = 20;
            } else if (peek.equals("21")) {
                columna = 21;
            } else if (peek.equals("22")) {
                columna = 22;
            } else if (peek.equals("$")) {
                columna = 23;
            } else if (peek.equals("<programa>")) {
                columna = 24;
            } else if (peek.equals("<Definiciones>")) {
                columna = 25;
            } else if (peek.equals("<Definicion>")) {
                columna = 26;
            } else if (peek.equals("<DefVar>")) {
                columna = 27;
            } else if (peek.equals("<ListaVar>")) {
                columna = 28;
            } else if (peek.equals("<DefFunc>")) {
                columna = 29;
            } else if (peek.equals("<Parametros>")) {
                columna = 30;
            } else if (peek.equals("<ListaParam>")) {
                columna = 31;
            } else if (peek.equals("<BloqFunc>")) {
                columna = 32;
            } else if (peek.equals("<DefLocales>")) {
                columna = 33;
            } else if (peek.equals("<DefLocal>")) {
                columna = 34;
            } else if (peek.equals("<Sentencias>")) {
                columna = 35;
            } else if (peek.equals("<Sentencia>")) {
                columna = 36;
            } else if (peek.equals("<Otro>")) {
                columna = 37;
            } else if (peek.equals("<Bloque>")) {
                columna = 38;
            } else if (peek.equals("<ValorRegresa>")) {
                columna = 39;
            } else if (peek.equals("<Argumentos>")) {
                columna = 40;
            } else if (peek.equals("<ListaArgumentos>")) {
                columna = 41;
            } else if (peek.equals("<Termino>")) {
                columna = 42;
            } else if (peek.equals("<LlamadaFunc>")) {
                columna = 43;
            } else if (peek.equals("<SentenciaBloque>")) {
                columna = 44;
            } else if (peek.equals("<Expresion>")) {
                columna = 45;
            } else {
                textoPila[posTextoPila][0] = "Error sintáctico: " + analizadoLex[posAnalizadoLex][0];
                textoPila[posTextoPila][1] = "1";
                break;
            }
            
            
            // Obteniendo la fila según el último número de la fila
            fila = Integer.parseInt((String)pila.peek());
            
            // Obteniendo la salida de la tabla LR 
            salida = tablasin.tabla[fila][columna];
            
            // Estado de aceptación
            if (salida == -1) {
                estadoAceptacion = true;
                break;
            }
            textoPila[posTextoPila][1] = "0";
            // Estado d
            if (salida > 0) {
                pila.push(pilaLex.peek());
                pilaLex.pop();
                posAnalizadoLex++;
                pila.push(String.valueOf(salida));
            
            // Estado r
            } else if (salida < 0) {
                if (salida == -2) { // R1 <programa> ::= <Definiciones> 
                    pila.pop();pila.pop();
                    pilaLex.push("<programa>");
                } else if (salida == -3) { // R2 <Definiciones> ::= \e 
                    pilaLex.push("<Definiciones>");
                } else if (salida == -4) { // R3 <Definiciones> ::= <Definicion> <Definiciones>
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<Definiciones>");
                } else if (salida == -5) { //  R4 <Definicion> ::= <DefVar> 
                    pila.pop();pila.pop();
                    pilaLex.push("<Definicion>");
                } else if (salida == -6) { // R5 <Definicion> ::= <DefFunc> 
                    pila.pop();pila.pop();
                    pilaLex.push("<Definicion>");
                } else if (salida == -7) { // R6 <DefVar> ::= tipo identificador <ListaVar> ;
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<DefVar>");
                } else if (salida == -8) { // R7 <ListaVar> ::= \e 
                    pilaLex.push("<ListaVar>");
                } else if (salida == -9) { // R8 <ListaVar> ::= , identificador <ListaVar> 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<ListaVar>");
                } else if (salida == -10) { // R9 <DefFunc> ::= tipo identificador ( <Parametros> ) <BloqFunc> 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<DefFunc>");
                } else if (salida == -11) { // R10 <Parametros> ::= \e 
                    pilaLex.push("<Parametros>");
                } else if (salida == -12) { // R11 <Parametros> ::= tipo identificador <ListaParam> 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<Parametros>");
                } else if (salida == -13) { // R12 <ListaParam> ::= \e 
                    pilaLex.push("<ListaParam>");
                } else if (salida == -14) { // R13 <ListaParam> ::= , tipo identificador <ListaParam> 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<ListaParam>");
                } else if (salida == -15) { // R14 <BloqFunc> ::= { <DefLocales> } 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<BloqFunc>");
                } else if (salida == -16) { // R15 <DefLocales> ::= \e 
                    pilaLex.push("<DefLocales>");
                } else if (salida == -17) { // R16 <DefLocales> ::= <DefLocal> <DefLocales> 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<DefLocales>");
                } else if (salida == -18) { // R17 <DefLocal> ::= <DefVar> 
                    pila.pop();pila.pop();
                    pilaLex.push("<DefLocal>");
                } else if (salida == -19) { // R18 <DefLocal> ::= <Sentencia> 
                    pila.pop();pila.pop();
                    pilaLex.push("<DefLocal>");
                } else if (salida == -20) { // R19 <Sentencias> ::= \e 
                    pilaLex.push("<Sentencias>");
                } else if (salida == -21) { // R20 <Sentencias> ::= <Sentencia> <Sentencias> 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<Sentencias>");
                } else if (salida == -22) { // R21 <Sentencia> ::= identificador = <Expresion> ; 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<Sentencia>");
                } else if (salida == -23) { // R22 <Sentencia> ::= if ( <Expresion> ) <SentenciaBloque> <Otro> 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<Sentencia>");
                } else if (salida == -24) { // R23 <Sentencia> ::= while ( <Expresion> ) <Bloque> 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<Sentencia>");
                } else if (salida == -25) { // R24 <Sentencia> ::= return <ValorRegresa> ; 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<Sentencia>");
                } else if (salida == -26) { // R25 <Sentencia> ::= <LlamadaFunc> ; 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<Sentencia>");
                } else if (salida == -27) { // R26 <Otro> ::= \e 
                    pilaLex.push("<Otro>");
                } else if (salida == -28) { // R27 <Otro> ::= else <SentenciaBloque> 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<Otro>");
                } else if (salida == -29) { // R28 <Bloque> ::= { <Sentencias> } 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<Bloque>");
                } else if (salida == -30) { // R29 <ValorRegresa> ::= \e 
                    pilaLex.push("<ValorRegresa>");
                } else if (salida == -31) { // R30 <ValorRegresa> ::= <Expresion> 
                    pila.pop();pila.pop();
                    pilaLex.push("<ValorRegresa>");
                } else if (salida == -32) { // R31 <Argumentos> ::= \e 
                    pilaLex.push("<Argumentos>");
                } else if (salida == -33) { // R32 <Argumentos> ::= <Expresion> <ListaArgumentos> 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<Argumentos>");
                } else if (salida == -34) { // R33 <ListaArgumentos> ::= \e 
                    pilaLex.push("<ListaArgumentos>");
                } else if (salida == -35) { // R34 <ListaArgumentos> ::= , <Expresion> <ListaArgumentos> 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<ListaArgumentos>");
                } else if (salida == -36) { // R35 <Termino> ::= <LlamadaFunc> 
                    pila.pop();pila.pop();
                    pilaLex.push("<Termino>");
                } else if (salida == -37) { // R36 <Termino> ::= identificador 
                    pila.pop();pila.pop();
                    pilaLex.push("<Termino>");
                } else if (salida == -38) { // R37 <Termino> ::= entero 
                    pila.pop();pila.pop();
                    pilaLex.push("<Termino>");
                } else if (salida == -39) { // R38 <Termino> ::= real 
                    pila.pop();pila.pop();
                    pilaLex.push("<Termino>");
                } else if (salida == -40) { // R39 <Termino> ::= cadena 
                    pila.pop();pila.pop();
                    pilaLex.push("<Termino>");
                } else if (salida == -41) { // R40 <LlamadaFunc> ::= identificador ( <Argumentos> ) 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<LlamadaFunc>");
                } else if (salida == -42) { // R41 <SentenciaBloque> ::= <Sentencia> 
                    pila.pop();pila.pop();
                    pilaLex.push("<SentenciaBloque>");
                } else if (salida == -43) { // R42 <SentenciaBloque> ::= <Bloque> 
                    pila.pop();pila.pop();
                    pilaLex.push("<SentenciaBloque>");
                } else if (salida == -44) { // R43 <Expresion> ::= ( <Expresion> ) 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<Expresion>");
                } else if (salida == -45) { // R44 <Expresion> ::= opSuma <Expresion> 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<Expresion>");
                } else if (salida == -46) { // R45 <Expresion> ::= opNot <Expresion> 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<Expresion>");
                } else if (salida == -47) { // R46 <Expresion> ::= <Expresion> opMul <Expresion> 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<Expresion>");
                } else if (salida == -48) { // R47 <Expresion> ::= <Expresion> opSuma <Expresion> 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<Expresion>");
                } else if (salida == -49) { // R48 <Expresion> ::= <Expresion> opRelac <Expresion> 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<Expresion>");
                } else if (salida == -50) { // R49 <Expresion> ::= <Expresion> opIgualdad <Expresion> 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<Expresion>");
                } else if (salida == -51) { // R50 <Expresion> ::= <Expresion> opAnd <Expresion> 
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<Expresion>");
                } else if (salida == -52) { // R51 <Expresion> ::= <Expresion> opOr <Expresion>
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pila.pop();pila.pop();
                    pilaLex.push("<Expresion>");
                } else if (salida == -53) { // R52 <Expresion> ::= <Termino> 
                    pila.pop();pila.pop();
                    pilaLex.push("<Expresion>");
                }
                
            // La salida cayó en una casilla 0
            } else {
                textoPila[posTextoPila][0] = "Error sintáctico: " + analizadoLex[posAnalizadoLex][0];
                textoPila[posTextoPila][1] = "1";
                break;
            }
            
            // Creando una copia de la pila para retornarla en el arreglo de estados
            Stack copiaPila = (Stack)pila.clone();
            Stack copiaVolteadaPila = new Stack();
            while (copiaPila.empty() == false) {
                copiaVolteadaPila.push(copiaPila.peek());
                copiaPila.pop();
            }
            
            // Invirtiendo la pila y guardándola en el arreglo de estados            
            textoPila[posTextoPila][0] = "";
            int posAnalizadorLex = 0;
            int posPila = 0;
            while (copiaVolteadaPila.empty() == false) {
                // Se escribe lo que está en la pila
                if (posPila % 2 == 1 || posPila == 0 || String.valueOf(copiaVolteadaPila.peek()).charAt(0) == '<') {
                    textoPila[posTextoPila][0] += "" + String.valueOf(copiaVolteadaPila.peek()); 
                } else {
                // O se escribe lo que está en la lista
                    textoPila[posTextoPila][0] += "" + analizadoLex[posAnalizadorLex][0];
                    posAnalizadorLex++;
                }
                copiaVolteadaPila.pop();
                posPila++;
            }
            
            // Se avanza una posición en el arreglo de estados
            posTextoPila++;
        }

        // Se indica si se acepta la cadena
        if (textoPila[posTextoPila][1] != "1") {
            textoPila[posTextoPila][0] = "Aceptada";
            textoPila[posTextoPila][1] = "0";
        }
        
        return textoPila;
    }
}
