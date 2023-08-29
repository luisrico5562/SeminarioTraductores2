package logica;

import java.util.*;

public class AnalizadorSintactico {
    
    public String[] sintactico(Stack pilaLex, int largoEntrada) {
        
        // Variable para guardar los estados de la pila
        String[] textoPila = new String[100];
        
        Stack pila = new Stack();
        pila.push("$");
        textoPila[0] = "" + pila.peek();
        pila.push("0");
        textoPila[0] += pila.peek();
        
        boolean estadoAceptacion = false;
        
        // Tabla LR
        int[][] tablaLR1 = {
            {2,0, 0,1},
            {0,0,-1,0},
            {0,3,-3,0},
            {2,0, 0,4},
            {0,0,-2,0}};
        
        // id = 0
        // + = 5
        int fila = 0;
        int columna = 0;
        int salida = 0;
        
        String peek = "";
        int posTextoPila = 1;
        while(estadoAceptacion == false) {
            
            // Obteniendo la columna según el tipo de entrada
            peek = String.valueOf(pilaLex.peek());
            if (peek.charAt(0) == '0') {
                columna = 0;
                
            } else if (peek.charAt(0) == '5') {
                columna = 1;
                
            } else if (peek.charAt(0) == '$') {
                columna = 2;
                
            } else if (peek.charAt(0) == 'E') {
                columna = 3;
                
            } else {
                textoPila[posTextoPila] = "ERROR";
                break;
            }
            
            // Obteniendo la fila según el último número de la fila
            fila = Integer.parseInt((String)pila.peek());
            
            // Obteniendo la salida de la tabla LR 
            salida = tablaLR1[fila][columna];
            
            // Estado de aceptación
            if (salida == -1) {
                estadoAceptacion = true;
                break;
            }
            
            // Estado d
            if (salida > 0) {
                pila.push(pilaLex.peek());
                pilaLex.pop();
                pila.push(String.valueOf(salida));
            
            // Estado r
            } else if (salida < 0) {
                if (salida == -2) {
                    pila.pop();
                    pila.pop();
                    pila.pop();
                    pila.pop();
                    pila.pop();
                    pila.pop();
                    pilaLex.push("E");
                    
                } else if (salida == -3) {
                    pila.pop();
                    pila.pop();
                    pilaLex.push("E");
                }
                
            // La salida cayó en una casilla 0
            } else {
                textoPila[posTextoPila] = "ERROR";
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
            textoPila[posTextoPila] = "";
            while (copiaVolteadaPila.empty() == false) {
                textoPila[posTextoPila] += "" + String.valueOf(copiaVolteadaPila.peek());
                copiaVolteadaPila.pop();
            }
            // Se avanza una posición en el arreglo de estados
            posTextoPila++;
        }
        
        return textoPila;
    }
}
