package logica;

import java.util.*;

public class AnalizadorSemantico {
    
    public String[][] semantico(Stack pilaLex, String[][] analizadoLex, int largoEntrada) {
        
        // Arreglo a retornar con todas las variables y funciones
        String[][] listaVarFun = new String[100][3];
        int nivel = 0;
        int posListaVarFun = 0;
        
        // Arreglo para guardar las funciones y variables del bloque de código actual
        String[][] listaSem = new String[100][3];
        int posListaSem = 0;
        
        // analizadoLex = [0] Cadena, [1] Valor        
        // listaVarFun = listaSem = [0] Nivel, [1] Tipo, [2] Identificador, [3] Valor
        // Errores en [0] Nivel
        
        for (int i = 0; i < largoEntrada; i++) {
            
            // Si se cierra un bloque de código
            if (analizadoLex[i][1].equals("17")) {
                // Se eliminan todas las variables del bloque en la tabla de bloque
                /*
                int variablesAEliminar = 0;
                for (int j = 0; j < posListaSem; j++) {
                    if (listaSem[j][0].equals(String.valueOf(nivel))) {
                        variablesAEliminar++;
                    }
                }
                posListaSem -= variablesAEliminar;
                */
                nivel--;
            }
            
            // Si hay una declaración de variable/función
            else if (analizadoLex[i][1].equals("4")) {
                for (int j = 0; j < posListaSem; j++) {
                    // Si hay una variable/función con el mismo identificador
                    if (analizadoLex[i+1][0].equals(listaSem[j][2]) && String.valueOf(nivel).equals(listaSem[j][0])) {
                    
                        listaVarFun[posListaVarFun][0] = "Error semántico: " + analizadoLex[i+1][0] + " ya declarada.";
                        return listaVarFun;
                    }
                }
                // Se guardan los valores en las tablas
                listaVarFun[posListaVarFun][0] = String.valueOf(nivel);
                listaSem[posListaSem][0] = String.valueOf(nivel);
                if (analizadoLex[i+2][1].equals("14")) {
                    listaVarFun[posListaVarFun][1] = "dec fun";
                    listaSem[posListaSem][1] = "dec fun";
                    nivel++;
                } else {
                    listaVarFun[posListaVarFun][1] = "dec " + analizadoLex[i][0];
                    listaSem[posListaSem][1] = "dec " + analizadoLex[i][0];
                }
                listaVarFun[posListaVarFun][2] = analizadoLex[i+1][0];
                listaSem[posListaSem][2] = analizadoLex[i+1][0];
                
                // Se actualizan las posiciones de las tablas
                posListaVarFun++;
                posListaSem++;
                i += 2;
            }
            
            // Si se asgina el valor de una variable: a = b;
            else if (analizadoLex[i][1].equals("0") && analizadoLex[i+1][1].equals("18")) {
                // Se agrega la variable a la que se le asignará un valor
                listaVarFun[posListaVarFun][0] = String.valueOf(nivel);
                listaVarFun[posListaVarFun][1] = "asig";
                listaVarFun[posListaVarFun][2] = analizadoLex[i][0];
                posListaVarFun++;
                
                int nivelActual = nivel;
                nivel++;
                
                // Se agregan las variables, valores y operadores de la asignación
                while (!analizadoLex[i][1].equals("12")) {
                    i ++;
                    if (analizadoLex[i][1].equals("0") || analizadoLex[i][1].equals("1") 
                            || analizadoLex[i][1].equals("2") || analizadoLex[i][1].equals("5")
                            || analizadoLex[i][1].equals("6")) {
                        listaVarFun[posListaVarFun][0] = String.valueOf(nivel);
                        listaVarFun[posListaVarFun][1] = "";
                        listaVarFun[posListaVarFun][2] = analizadoLex[i][0];
                        posListaVarFun++;
                        nivel++;
                    }
                }
                nivel = nivelActual;
            }
            
            // Si se utiliza una función
            else if (analizadoLex[i][1].equals("0") && analizadoLex[i+1][1].equals("14")) {
                // Se agrega el método a la que se le asignará un valor
                listaVarFun[posListaVarFun][0] = String.valueOf(nivel);
                listaVarFun[posListaVarFun][1] = "fun";
                listaVarFun[posListaVarFun][2] = analizadoLex[i][0];
                posListaVarFun++;
                
                int nivelActual = nivel;
                nivel++;
                
                // Se agregan las variables, valores y operadores de la asignación
                while (!analizadoLex[i][1].equals("12")) {
                    i ++;
                    if (analizadoLex[i][1].equals("0") || analizadoLex[i][1].equals("1") 
                            || analizadoLex[i][1].equals("2") || analizadoLex[i][1].equals("3")) {
                        listaVarFun[posListaVarFun][0] = String.valueOf(nivel);
                        listaVarFun[posListaVarFun][1] = "";
                        listaVarFun[posListaVarFun][2] = analizadoLex[i][0];
                        posListaVarFun++;
                        nivel++;
                    }
                }
                nivel = nivelActual;
            }
            
            // Si se utiliza una variable: if(a == b) while(a < b)  IF y WHILE
            else if (analizadoLex[i][1].equals("19") || analizadoLex[i][1].equals("20")) {
                // Se agrega el método a la que se le asignará un valor
                listaVarFun[posListaVarFun][0] = String.valueOf(nivel);
                listaVarFun[posListaVarFun][1] = "";
                listaVarFun[posListaVarFun][2] = analizadoLex[i][0];
                posListaVarFun++;                
                
                int nivelActual = nivel+1;
                nivel++;
                i += 2;
                
                listaVarFun[posListaVarFun][0] = String.valueOf(nivel);
                listaVarFun[posListaVarFun][1] = "comp";
                listaVarFun[posListaVarFun][2] = "";
                nivel++;
                posListaVarFun++;
                
                // Primer valor
                listaVarFun[posListaVarFun][0] = String.valueOf(nivel);
                listaVarFun[posListaVarFun][1] = "";
                listaVarFun[posListaVarFun][2] = analizadoLex[i][0];
                nivel++;
                posListaVarFun++;
                i++;
                
                // Operando
                listaVarFun[posListaVarFun][0] = String.valueOf(nivel);
                listaVarFun[posListaVarFun][1] = "";
                listaVarFun[posListaVarFun][2] = analizadoLex[i][0];
                nivel++;
                posListaVarFun++;
                i++;
                
                // Segundo valor
                listaVarFun[posListaVarFun][0] = String.valueOf(nivel);
                listaVarFun[posListaVarFun][1] = "";
                listaVarFun[posListaVarFun][2] = analizadoLex[i][0];
                nivel++;
                posListaVarFun++;
                i++;
                
                nivel = nivelActual;
            }
            
        }
        
        return listaVarFun;
    }
}
