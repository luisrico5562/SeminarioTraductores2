package logica;

public class AnalizadorLexico {
    
    public String[][] lexico(String cadena) {
        
        String[][] resultado = new String[100][2];
        
        int posResultado = 0;
        int espacio = 0;
        
        for (int i = 0; i < cadena.length(); i++) {
            //                                                          // 13 = enter               // 9 = tabulación
            if (cadena.charAt(i) != ' ' && cadena.charAt(i) != '\n' && cadena.charAt(i) != 13 && cadena.charAt(i) != 9) {
                if (espacio == 0) {
                    // Se inicializa la cadena
                    if (resultado[posResultado][0] == null) {
                        resultado[posResultado][0] = "";
                    }
                    // Se agrega el caracter a la subcadena
                    resultado[posResultado][0] += cadena.charAt(i);
                } else {
                    // Se comprueba la subcadena antes de avanzar a la siguiente
                    resultado[posResultado][1] = String.valueOf(s0(resultado[posResultado][0]));
                    // Si se detecta un primer caracter se cambia a caracteres para empezar a guardar información
                    // Además e avanza a la siguiente posición del arreglo con las subcadenas
                    espacio = 0;
                    posResultado++;
                    resultado[posResultado][0] = "";
                    resultado[posResultado][0] += cadena.charAt(i);
                }
                
            } else {
                // Si se detecta un primer espacio se cambia a espacios para no guardar información
                espacio = 1;
            }
        }
        
        // Se comprueba la última subcadena
        resultado[posResultado][1] = String.valueOf(s0(resultado[posResultado][0]));
        
        return resultado;

    }
    
    public String recorrer(String cadena) {
        String aux = "";
        for (int i = 1; i < cadena.length(); i++) {
            aux += cadena.charAt(i);
        }
        return aux;
    }
    
    public int s0(String cadena) {
        if (cadena.charAt(0) >= 97 && cadena.charAt(0) <= 122) {
            cadena = recorrer(cadena);
            return s1(cadena);
            
        } else if (cadena.charAt(0) >= 48 && cadena.charAt(0) <= 57) {
            cadena = recorrer(cadena);
            return s2(cadena);
        }
        
        return 24; // Error
    }
    
    public int s1(String cadena) {
        
        if (cadena == "") {
            return 0;
        }
        
        if (cadena.charAt(0) >= 97 && cadena.charAt(0) <= 122 || cadena.charAt(0) >= 48 && cadena.charAt(0) <= 57) {
            cadena = recorrer(cadena);
            return s1(cadena);
        }
        
        return 24;
    }
    
    public int s2(String cadena) {
        
        if (cadena == "") {
            return 1;
        }
        
        if (cadena.charAt(0) >= 48 && cadena.charAt(0) <= 57) {
            cadena = recorrer(cadena);
            return s2(cadena);
            
        } else if (cadena.charAt(0) == '.') {
            cadena = recorrer(cadena);
            return s3(cadena);
        }
        
        return 24;
    }
    
    public int s3(String cadena) {
        
        if (cadena.charAt(0) >= 48 && cadena.charAt(0) <= 57) {
            cadena = recorrer(cadena);
            return s4(cadena);
        } 
        
        return 24;
    }
    
    public int s4(String cadena) {
        
        if (cadena == "") {
            return 2;
        }
        
        if (cadena.charAt(0) >= 48 && cadena.charAt(0) <= 57) {
            cadena = recorrer(cadena);
            return s4(cadena);
        } 
        
        return 24;
    }
}
