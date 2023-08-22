package logica;

public class AnalizadorLexico {
    
    public String[][] lexico(String cadena) {
        
        // Eliminar los espacios antes del primer caracter de la cadena
        while (cadena.charAt(0) == ' ') {
            if (cadena.length() == 1) {
                break;
            }
            cadena = recorrer(cadena);
        }
        
        String[] arSinEspacios = new String[100];
        
        int posSinEspacios = 0;
        int espacio = 0;
        
        // --- SEPARACIÓN POR ESPACIOS ---
        for (int i = 0; i < cadena.length(); i++) {
            //                                                          // 13 = enter               // 9 = tabulación
            if (cadena.charAt(i) != ' ' && cadena.charAt(i) != '\n' && cadena.charAt(i) != 13 && cadena.charAt(i) != 9) {
                if (espacio == 0) {
                    // Se inicializa la cadena
                    if (arSinEspacios[posSinEspacios] == null) {
                        arSinEspacios[posSinEspacios] = "";
                    }
                    // Se agrega el caracter a la subcadena
                    arSinEspacios[posSinEspacios] += cadena.charAt(i);
                } else {
                    // Si se detecta un primer caracter se cambia a caracteres para empezar a guardar información
                    // Además e avanza a la siguiente posición del arreglo con las subcadenas
                    espacio = 0;
                    posSinEspacios++;
                    arSinEspacios[posSinEspacios] = "";
                    arSinEspacios[posSinEspacios] += cadena.charAt(i);
                }
                
            } else {
                // Si se detecta un primer espacio se cambia a espacios para no guardar información
                espacio = 1;
            }
        }
        
        // Contando las posiciones en arSinEspacios
        int posicionesArSinEspacios = 0;
        for (int i = 0; i < 100; i++) {
            if (arSinEspacios[i] != null) {
                posicionesArSinEspacios++;
            } else {
                break;
            }
        }
        
        // --- SEPARACIÓN POR TIPO DE CARACTER ---
        String[] arPorTipo = new String[100];
        
        int posArPorTipo = 0;
        int tipoPrimerChar = 4, tipoActual = 5;
        /*
        Operadores: 0
        Alfanuméricos: 1
        <,>,=,!: 2
        &,|: 3
        */
        // Separar por tipo de caracteres
        for (int i = 0; i < posicionesArSinEspacios; i++) {
            for (int j = 0; j < arSinEspacios[i].length(); j++) {
                // Se inicializa la posición si está vacía
                if (arPorTipo[posArPorTipo] == null) {
                    arPorTipo[posArPorTipo] = "";
                }
                // Se actualiza el tipo cuando es el principio de la cadena
                if (j == 0) {
                    if (tipoCaracter(arSinEspacios[i].charAt(j)) == 0) {
                        tipoPrimerChar = 0;
                        tipoActual = 0;
                    }
                    if (tipoCaracter(arSinEspacios[i].charAt(j)) == 1) {
                        tipoPrimerChar = 1;
                        tipoActual = 1;
                    }
                    if (tipoCaracter(arSinEspacios[i].charAt(j)) == 2) {
                        tipoPrimerChar = 2;
                        tipoActual = 2;
                    }
                    if (tipoCaracter(arSinEspacios[i].charAt(j)) == 3) {
                        tipoPrimerChar = 3;
                        tipoActual = 3;
                    }
                // Si no es el principio, entonces se actualiza el tipo actual
                } else {
                    if (tipoCaracter(arSinEspacios[i].charAt(j)) == 0) {
                        tipoActual = 0;
                    }
                    if (tipoCaracter(arSinEspacios[i].charAt(j)) == 1) {
                        tipoActual = 1;
                    }
                    if (tipoCaracter(arSinEspacios[i].charAt(j)) == 2) {
                        tipoActual = 2;
                    }
                    if (tipoCaracter(arSinEspacios[i].charAt(j)) == 3) {
                        tipoActual = 3;
                    }                    
                }
                // Si el tipo del primer char es igual al actual, se agrega a la cadena
                if (tipoActual == tipoPrimerChar) {
                    arPorTipo[posArPorTipo] += arSinEspacios[i].charAt(j);
                    
                // Si no, se avanza a la siguiente posición del arreglo arPorTipo
                } else {
                    posArPorTipo++;
                    arPorTipo[posArPorTipo] = "";
                    arPorTipo[posArPorTipo] += arSinEspacios[i].charAt(j);
                    
                    if (tipoCaracter(arSinEspacios[i].charAt(j)) == 0) {
                        tipoPrimerChar = 0;
                        tipoActual = 0;
                    }
                    if (tipoCaracter(arSinEspacios[i].charAt(j)) == 1) {
                        tipoPrimerChar = 1;
                        tipoActual = 1;
                    }
                    if (tipoCaracter(arSinEspacios[i].charAt(j)) == 2) {
                        tipoPrimerChar = 2;
                        tipoActual = 2;
                    }
                    if (tipoCaracter(arSinEspacios[i].charAt(j)) == 3) {
                        tipoPrimerChar = 3;
                        tipoActual = 3;
                    }
                }
            }
            posArPorTipo++;
        }
        
        // Contando las posiciones en arPorTipo
        int posicionesArPorTipo = 0;
        for (int i = 0; i < 100; i++) {
            if (arPorTipo[i] != null) {
                posicionesArPorTipo++;
            } else {
                break;
            }
        }
        
        // --- SEPARACIÓN POR SÍMBOLOS ---
        String[][] resultado = new String[100][2];
        int posResultado = 0;
        
        // Separar por símbolos
        for (int i = 0; i < posicionesArPorTipo; i++) {
            resultado[posResultado][0] = "";
            for (int j = 0; j < arPorTipo[i].length(); j++) {
                // Si el caracter es símbolo
                if (tipoCaracter(arPorTipo[i].charAt(j)) == 0) {
                    resultado[posResultado][0] += arPorTipo[i].charAt(j);
                    posResultado++;
                    resultado[posResultado][0] = "";
                // Si el caracter es alfanumérico
                } else if (tipoCaracter(arPorTipo[i].charAt(j)) == 1) {
                    resultado[posResultado][0] += arPorTipo[i].charAt(j);
                // Si el caracter es un operador
                } else if (tipoCaracter(arPorTipo[i].charAt(j)) == 2) {
                    
                    if (resultado[posResultado][0].length() == 0) {
                        resultado[posResultado][0] += arPorTipo[i].charAt(j);
                        
                    } else if (resultado[posResultado][0].length() == 1 && arPorTipo[i].charAt(j) == '=') {
                        resultado[posResultado][0] += arPorTipo[i].charAt(j);
                        posResultado++;
                        resultado[posResultado][0] = "";
                        
                    } else if (resultado[posResultado][0].length() == 1 && arPorTipo[i].charAt(j) != '=') {
                        posResultado++;
                        resultado[posResultado][0] = "";
                        resultado[posResultado][0] += arPorTipo[i].charAt(j);
                    
                    } else {
                        posResultado++;
                        resultado[posResultado][0] = "";
                    }
                // Si el caracter es un And u Or
                } else if (tipoCaracter(arPorTipo[i].charAt(j)) == 3) {
                    resultado[posResultado][0] += arPorTipo[i].charAt(j);
                    // Se detecta el segundo caracter de la expresión
                    if (resultado[posResultado][0].length() == 2) {
                        posResultado++;
                        resultado[posResultado][0] = "";
                    }
                } else {
                    resultado[posResultado][0] += arPorTipo[i].charAt(j);
                }
            }
            if (resultado[posResultado][0] != "") {
                posResultado++;
            }
        }
        
        // Contando las posiciones en resultado
        int posicionesResultado = 0;
        for (int i = 0; i < 100; i++) {
            if (resultado[i][0] != null) {
                posicionesResultado++;
            } else {
                break;
            }
        }
        // Quitando la última posición vacía para evitar que se analice
        if (resultado[posicionesResultado-1][0] == "") {
            posicionesResultado--;
            System.out.println("-" + resultado[posicionesResultado][0] + "-");
            resultado[posicionesResultado][0] = null;
        }
        // Se evalúa con el autómata cada dato obtenido
        for (int i = 0; i < posicionesResultado; i++) {
            resultado[i][1] = String.valueOf(s0(resultado[i][0]));
        }
        
        return resultado;
    }
    // Función para detectar la categoría del caracter
    public int tipoCaracter(char caracter) {
        // Símbolos
        if (caracter == '(' || caracter == ')' ||
            caracter == '{' || caracter == '}' ||
            caracter == '+' || caracter == '-' ||
            caracter == '*' || caracter == '/' ||
            caracter == ';' || caracter == ',') {
            return 0;
        }
        // Alfanuméricos
        if (caracter >= 65 && caracter <= 90 ||
            caracter >= 97 && caracter <= 122 ||
            caracter >= 48 && caracter <= 57 ||
            caracter == '.') {
            return 1;
        }
        // Operadores
        if (caracter == '<' || caracter == '>' ||
            caracter == '=' || caracter == '!') {
            return 2;
        }
        // And y Or
        if (caracter == '&' || caracter == '|') {
            return 3;
        }
        return 4;
    }
    // Función para recorrer (eliminar) el primer caracter de la cadena
    public String recorrer(String cadena) {
        String aux = "";
        for (int i = 1; i < cadena.length(); i++) {
            aux += cadena.charAt(i);
        }
        return aux;
    }
    
    // --- ESTADOS DEL AUTÓMATA ---
    public int s0(String cadena) {
        
        if (cadena.charAt(0) == 'i') {
            cadena = recorrer(cadena);
            return s1(cadena);
            
        } else if (cadena.charAt(0) == 'w') {
            cadena = recorrer(cadena);
            return s5(cadena);
            
        } else if (cadena.charAt(0) == 'r') {
            cadena = recorrer(cadena);
            return s10(cadena);
            
        } else if (cadena.charAt(0) == 'e') {
            cadena = recorrer(cadena);
            return s16(cadena);
            
        } else if (cadena.charAt(0) == 'f') {
            cadena = recorrer(cadena);
            return s20(cadena);
            
        } else if (cadena.charAt(0) >= 97 && cadena.charAt(0) <= 122 ||
                   cadena.charAt(0) >= 65 && cadena.charAt(0) <= 90) {
            cadena = recorrer(cadena);
            return s25(cadena);
            
        } else if (cadena.charAt(0) >= 48 && cadena.charAt(0) <= 57) {
            cadena = recorrer(cadena);
            return s26(cadena);
            
        } else if (cadena.charAt(0) == '+' || cadena.charAt(0) == '-') {        
            cadena = recorrer(cadena);
            return s29(cadena);
            
        } else if (cadena.charAt(0) == '*' || cadena.charAt(0) == '/') {        
            cadena = recorrer(cadena);
            return s30(cadena);
            
        } else if (cadena.charAt(0) == '=') {        
            cadena = recorrer(cadena);
            return s31(cadena);
            
        } else if (cadena.charAt(0) == '<' || cadena.charAt(0) == '>') {        
            cadena = recorrer(cadena);
            return s32(cadena);
            
        } else if (cadena.charAt(0) == '!') {        
            cadena = recorrer(cadena);
            return s33(cadena);
            
        } else if (cadena.charAt(0) == '&') {        
            cadena = recorrer(cadena);
            return s35(cadena);
            
        } else if (cadena.charAt(0) == '|') {        
            cadena = recorrer(cadena);
            return s37(cadena);
            
        } else if (cadena.charAt(0) == '(') {        
            cadena = recorrer(cadena);
            return s39(cadena);
            
        } else if (cadena.charAt(0) == ')') {        
            cadena = recorrer(cadena);
            return s40(cadena);
            
        } else if (cadena.charAt(0) == '{') {        
            cadena = recorrer(cadena);
            return s41(cadena);
            
        } else if (cadena.charAt(0) == '}') {        
            cadena = recorrer(cadena);
            return s42(cadena);
            
        } else if (cadena.charAt(0) == ';') {        
            cadena = recorrer(cadena);
            return s43(cadena);
            
        } else if (cadena.charAt(0) == ',') {        
            cadena = recorrer(cadena);
            return s44(cadena);
        }
        return 24;
    }
    
    public int s1(String cadena) {

        if (cadena == "") {
            return 0;
            
        } else if (cadena.charAt(0) == 'n') {
            cadena = recorrer(cadena);
            return s2(cadena);
            
        } else if (cadena.charAt(0) == 'f') {
            cadena = recorrer(cadena);
            return s4(cadena);
            
        }  else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        
        return 24;
    }
    
    public int s2(String cadena) {
        if (cadena == "") {
            return 0;
            
        }  else if (cadena.charAt(0) == 't') {
            cadena = recorrer(cadena);
            return s3(cadena);
            
        }  else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public int s3(String cadena) {
        if (cadena == "") {
            return 4;
            
        }   else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public int s4(String cadena) {
        if (cadena == "") {
            return 19;
            
        }   else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public int s5(String cadena) {
        if (cadena == "") {
            return 0;
            
        }   else if (cadena.charAt(0) == 'h') {
            cadena = recorrer(cadena);
            return s6(cadena);
            
        }  else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public int s6(String cadena) {
        if (cadena == "") {
            return 0;
            
        }   else if (cadena.charAt(0) == 'i') {
            cadena = recorrer(cadena);
            return s7(cadena);
            
        }  else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public int s7(String cadena) {
        if (cadena == "") {
            return 0;
            
        }   else if (cadena.charAt(0) == 'l') {
            cadena = recorrer(cadena);
            return s8(cadena);
            
        }  else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public int s8(String cadena) {
        if (cadena == "") {
            return 0;
            
        }   else if (cadena.charAt(0) == 'e') {
            cadena = recorrer(cadena);
            return s9(cadena);
            
        }  else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public int s9(String cadena) {
        if (cadena == "") {
            return 20;
            
        } else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public int s10(String cadena) {
        if (cadena == "") {
            return 0;
            
        }   else if (cadena.charAt(0) == 'e') {
            cadena = recorrer(cadena);
            return s11(cadena);
            
        }  else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public int s11(String cadena) {
        if (cadena == "") {
            return 0;
            
        }   else if (cadena.charAt(0) == 't') {
            cadena = recorrer(cadena);
            return s12(cadena);
            
        }  else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public int s12(String cadena) {
        if (cadena == "") {
            return 0;
            
        }   else if (cadena.charAt(0) == 'u') {
            cadena = recorrer(cadena);
            return s13(cadena);
            
        }  else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public int s13(String cadena) {
        if (cadena == "") {
            return 0;
            
        }   else if (cadena.charAt(0) == 'r') {
            cadena = recorrer(cadena);
            return s14(cadena);
            
        }  else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public int s14(String cadena) {
        if (cadena == "") {
            return 0;
            
        }   else if (cadena.charAt(0) == 'n') {
            cadena = recorrer(cadena);
            return s15(cadena);
            
        }  else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public int s15(String cadena) {
        if (cadena == "") {
            return 21;
            
        } else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public int s16(String cadena) {
        if (cadena == "") {
            return 0;
            
        }   else if (cadena.charAt(0) == 'l') {
            cadena = recorrer(cadena);
            return s17(cadena);
            
        }  else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public int s17(String cadena) {
        if (cadena == "") {
            return 0;
            
        }   else if (cadena.charAt(0) == 's') {
            cadena = recorrer(cadena);
            return s18(cadena);
            
        }  else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public int s18(String cadena) {
        if (cadena == "") {
            return 0;
            
        }   else if (cadena.charAt(0) == 'e') {
            cadena = recorrer(cadena);
            return s19(cadena);
            
        }  else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public int s19(String cadena) {
        if (cadena == "") {
            return 22;
            
        } else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public int s20(String cadena) {
        if (cadena == "") {
            return 0;
            
        }   else if (cadena.charAt(0) == 'l') {
            cadena = recorrer(cadena);
            return s21(cadena);
            
        }  else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public int s21(String cadena) {
        if (cadena == "") {
            return 0;
            
        }   else if (cadena.charAt(0) == 'o') {
            cadena = recorrer(cadena);
            return s22(cadena);
            
        }  else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public int s22(String cadena) {
        if (cadena == "") {
            return 0;
            
        }   else if (cadena.charAt(0) == 'a') {
            cadena = recorrer(cadena);
            return s23(cadena);
            
        }  else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public int s23(String cadena) {
        if (cadena == "") {
            return 0;
            
        }   else if (cadena.charAt(0) == 't') {
            cadena = recorrer(cadena);
            return s24(cadena);
            
        }  else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public int s24(String cadena) {
        if (cadena == "") {
            return 4;
            
        } else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public int s25(String cadena) {
        if (cadena == "") {
            return 0;
            
        } else if (charAlfanumerico(cadena.charAt(0))) {
            cadena = recorrer(cadena);
            return s25(cadena);
        }
        return 24;
    }
    
    public boolean charAlfanumerico(char caracter) {
        if (caracter >= 97 && caracter <= 122 ||
            caracter >= 65 && caracter <= 90 ||
            caracter >= 48 && caracter <= 57) {
            return true;
        }
        return false;
    }
    
    public int s26(String cadena) {
        if (cadena == "") {
            return 1;
            
        } else if (cadena.charAt(0) >= 48 && cadena.charAt(0) <= 57) {
            cadena = recorrer(cadena);
            return s26(cadena);
            
        } else if (cadena.charAt(0) == '.') {
            cadena = recorrer(cadena);
            return s27(cadena);
        }
        return 24;
    }
    
    public int s27(String cadena) {
        if (cadena == "") {
            return 24;
            
        } else if (cadena.charAt(0) >= 48 && cadena.charAt(0) <= 57) {
            cadena = recorrer(cadena);
            return s28(cadena);
        }
        return 24;
    }
    
    public int s28(String cadena) {
        if (cadena == "") {
            return 2;
            
        } else if (cadena.charAt(0) >= 48 && cadena.charAt(0) <= 57) {
            cadena = recorrer(cadena);
            return s28(cadena);
        }
        return 24;
    }
    
    public int s29(String cadena) {
        if (cadena == "") {
            return 5;
        }
        return 24;
    }
    
    public int s30(String cadena) {
        if (cadena == "") {
            return 6;
        }
        return 24;
    }
    
    public int s31(String cadena) {
        if (cadena == "") {
            return 18;
            
        } else if (cadena.charAt(0) == '=') {
            cadena = recorrer(cadena);
            return s34b(cadena);
        }
        return 24;
    }
    
    public int s32(String cadena) {
        if (cadena == "") {
            return 7;
        } else if (cadena.charAt(0) == '=') {
            cadena = recorrer(cadena);
            return s34a(cadena);
        }
        return 24;
    }
    
    public int s33(String cadena) {
        if (cadena == "") {
            return 10;
        } else if (cadena.charAt(0) == '=') {
            cadena = recorrer(cadena);
            return s34b(cadena);
        }
        return 24;
    }
    
    public int s34a(String cadena) {
        if (cadena == "") {
            return 7;
        }
        return 24;
    }
    
    public int s34b(String cadena) {
        if (cadena == "") {
            return 11;
        }
        return 24;
    }
    
    public int s35(String cadena) {
        if (cadena == "") {
            return 24;
            
        } else if (cadena.charAt((0)) == '&') {
            cadena = recorrer(cadena);
            return s36(cadena);
        }
        return 24;
    }
    
    public int s36(String cadena) {
        if (cadena == "") {
            return 9;
        }
        return 24;
    }
    
    public int s37(String cadena) {
        if (cadena == "") {
            return 24;
            
        } else if (cadena.charAt((0)) == '|') {
            cadena = recorrer(cadena);
            return s38(cadena);
        }
        return 24;
    }
    
    public int s38(String cadena) {
        if (cadena == "") {
            return 8;
        }
        return 24;
    }
    
    public int s39(String cadena) {
        if (cadena == "") {
            return 14;
        }
        return 24;
    }
    
    public int s40(String cadena) {
        if (cadena == "") {
            return 15;
        }
        return 24;
    }
    
    public int s41(String cadena) {
        if (cadena == "") {
            return 16;
        }
        return 24;
    }
    
    public int s42(String cadena) {
        if (cadena == "") {
            return 17;
        }
        return 24;
    }
    
    public int s43(String cadena) {
        if (cadena == "") {
            return 12;
        }
        return 24;
    }
    
    public int s44(String cadena) {
        if (cadena == "") {
            return 13;
        }
        return 24;
    }
}
