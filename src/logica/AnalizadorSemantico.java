package logica;

import java.util.*;

public class AnalizadorSemantico {
    
    public String[][] semantico(Stack pilaLex, String[][] analizadoLex, int largoEntrada) {
        
        // Arreglo a retornar con todas las variables y funciones
        String[][] listaVarFun = new String[100][3];
        int nivelVar = 0;
        int posListaVarFun = 0;
        
        // Arreglo para guardar las funciones y variables del bloque de código actual
        String[][] listaSem = new String[100][4];
        int nivelSem = 0;
        int posListaSem = 1;
        // Método print()
        listaSem[0][0] = "0";
        listaSem[0][1] = "fun";
        listaSem[0][2] = "print";
        
        // analizadoLex = [0] Cadena, [1] Valor (tipo)    
        // listaVarFun = listaSem = [0] Nivel, [1] Tipo, [2] Identificador, [3] Valor
        // Errores en [0] Nivel
        
        for (int i = 0; i < largoEntrada; i++) {
            
            // Si se cierra un bloque de código
            if (analizadoLex[i][1].equals("17")) {
                // #############################################################################################
                // #############################################################################################
                // #############################################################################################
                for (int j = 0; j < listaSem.length; j++) {
                    if (listaSem[j][2] == null) {
                        break;
                    }
                }
                // #############################################################################################
                // #############################################################################################
                // #############################################################################################
                // Se eliminan todas las variables del bloque en la tabla de bloque
                int variablesAEliminar = 0;
                for (int j = 0; j < posListaSem; j++) {
                    if (listaSem[j][0].equals(String.valueOf(nivelSem))) {
                        variablesAEliminar++;
                    }
                }
                posListaSem -= variablesAEliminar;
                
                nivelSem--;
                nivelVar--;
            }
            
            // Si hay una declaración de variable/función
            else if (analizadoLex[i][1].equals("4")) {
                for (int j = 0; j < posListaSem; j++) {
                    // Si hay una variable/función con el mismo identificador
                    if (analizadoLex[i+1][0].equals(listaSem[j][2]) && String.valueOf(nivelSem).equals(listaSem[j][0])) {
                    
                        listaVarFun[posListaVarFun][0] = "Error semántico: " + analizadoLex[i+1][0] + " ya declarada.";
                        return listaVarFun;
                    }
                }
                // Se guardan los valores en las tablas
                listaVarFun[posListaVarFun][0] = String.valueOf(nivelSem);
                listaSem[posListaSem][0] = String.valueOf(nivelSem);
                if (analizadoLex[i+2][1].equals("14")) {
                    listaVarFun[posListaVarFun][1] = "dec fun";
                    listaSem[posListaSem][1] = "fun";
                    nivelSem++;
                    nivelVar++;
                } else {
                    listaVarFun[posListaVarFun][1] = "dec " + analizadoLex[i][0];
                    listaSem[posListaSem][1] = analizadoLex[i][0];
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
                listaVarFun[posListaVarFun][0] = String.valueOf(nivelSem);
                listaVarFun[posListaVarFun][1] = "asig";
                listaVarFun[posListaVarFun][2] = analizadoLex[i][0];
                posListaVarFun++;
                
                // Buscar si existe la variable
                String tipoVariable = "";
                boolean variableEncontrada = false;
                int posVarAsignada = 0;
                for (int j = 0; j < posListaSem; j++) {
                    if (analizadoLex[i][0].equals(listaSem[j][2])) {
                        tipoVariable = listaSem[j][1];
                        posVarAsignada = j;
                        variableEncontrada = true;
                    }
                }
                // Si no existe, se retorna el error
                if (!variableEncontrada) {
                    listaVarFun[posListaVarFun][0] = "Error semántico: " + analizadoLex[i][0] + " no declarada.";
                    return listaVarFun;
                }
                
                int nivelActual = nivelVar;
                nivelVar++;
                
                // Se agregan las variables, valores y operadores de la asignación
                while (!analizadoLex[i][1].equals("12")) {
                    i ++;
                    // Variable
                    if (analizadoLex[i][1].equals("0")) {
                        String tipoVariableAsignar = "";
                        variableEncontrada = false;
                        for (int j = 0; j < posListaSem; j++) {
                            if (analizadoLex[i][0].equals(listaSem[j][2]) && !listaSem[j][1].equals("fun")) {
                                tipoVariableAsignar = listaSem[j][1];
                                variableEncontrada = true;
                                
                                if (listaSem[j][3] == null) {
                                    listaVarFun[posListaVarFun][0] = "Error semántico: " + listaSem[j][2] + " no inicializada.";
                                    return listaVarFun;
                                }
                            }
                        }
                        // Si no existe, se retorna el error
                        if (!variableEncontrada) {
                            listaVarFun[posListaVarFun][0] = "Error semántico: " + analizadoLex[i][0] + " no declarada.";
                            return listaVarFun;
                        // Si no coincide con el tipo de variable
                        } else if (!tipoVariable.equals(tipoVariableAsignar)) {
                            listaVarFun[posListaVarFun][0] = "Error semántico: " + analizadoLex[i][0] + " no es " + tipoVariable + ".";
                            return listaVarFun;
                        }
                        listaVarFun[posListaVarFun][0] = String.valueOf(nivelVar);
                        listaVarFun[posListaVarFun][1] = "var";
                        listaVarFun[posListaVarFun][2] = analizadoLex[i][0];
                        posListaVarFun++;
                    
                    // Entero
                    } else if (analizadoLex[i][1].equals("1")) {
                        if (!tipoVariable.equals("int")) {
                            listaVarFun[posListaVarFun][0] = "Error semántico: " + analizadoLex[i][0] + " no es " + tipoVariable + ".";
                            return listaVarFun;
                        }
                        listaVarFun[posListaVarFun][0] = String.valueOf(nivelVar);
                        listaVarFun[posListaVarFun][1] = "int";
                        listaVarFun[posListaVarFun][2] = analizadoLex[i][0];
                        posListaVarFun++;
                       
                    // Float
                    } else if (analizadoLex[i][1].equals("2")) {
                        if (!tipoVariable.equals("float")) {
                            listaVarFun[posListaVarFun][0] = "Error semántico: " + analizadoLex[i][0] + " no es " + tipoVariable;
                            return listaVarFun;
                        }
                        listaVarFun[posListaVarFun][0] = String.valueOf(nivelVar);
                        listaVarFun[posListaVarFun][1] = "float";
                        listaVarFun[posListaVarFun][2] = analizadoLex[i][0];
                        posListaVarFun++;
                    
                    // Operadores
                    } else if (analizadoLex[i][1].equals("5")
                            || analizadoLex[i][1].equals("6")) {
                        listaVarFun[posListaVarFun][0] = String.valueOf(nivelVar);
                        listaVarFun[posListaVarFun][1] = "";
                        listaVarFun[posListaVarFun][2] = analizadoLex[i][0];
                        posListaVarFun++;
                    }
                }
                
                listaSem[posVarAsignada][3] = "1";
               
                nivelVar = nivelActual;
            }
            
            // Si se utiliza una función
            else if (analizadoLex[i][1].equals("0") && analizadoLex[i+1][1].equals("14")) {
                // Se agrega el método a la que se le asignará un valor
                listaVarFun[posListaVarFun][0] = String.valueOf(nivelVar);
                listaVarFun[posListaVarFun][1] = "fun";
                listaVarFun[posListaVarFun][2] = analizadoLex[i][0];
                
                // Revisar si la función existe
                boolean funcionEncontrada = false;
                for (int j = 0; j < posListaSem; j++) {
                    if (analizadoLex[i][0].equals(listaSem[j][2]) && listaSem[j][1].equals("fun")) {
                        funcionEncontrada = true;
                    }
                }
                if (!funcionEncontrada) {
                    listaVarFun[posListaVarFun][0] = "Error semántico: " + analizadoLex[i][0] + "() no declarado.";
                }
                
                posListaVarFun++;
                
                int nivelActual = nivelVar;
                nivelVar++;
                
                // Se agregan las variables, valores y operadores de la asignación
                while (!analizadoLex[i][1].equals("12")) {
                    i ++;
                    // Se revisa si existe la variable y está inicializada
                    if (analizadoLex[i][1].equals("0")) {
                        boolean variableEncontrada = false;
                        for (int j = 0; j < posListaSem; j++) {
                            if (analizadoLex[i][0].equals(listaSem[j][2]) && !listaSem[j][1].equals("fun")) {
                                variableEncontrada = true;
                                
                                if (listaSem[j][3] == null) {
                                    listaVarFun[posListaVarFun][0] = "Error semántico: " + listaSem[j][2] + " no inicializada.";
                                    return listaVarFun;
                                }
                            }
                        }
                        if (!variableEncontrada) {
                            listaVarFun[posListaVarFun][0] = "Error semántico: " + analizadoLex[i][0] + " no declarada.";
                            return listaVarFun;
                        }
                        listaVarFun[posListaVarFun][0] = String.valueOf(nivelVar);
                        listaVarFun[posListaVarFun][1] = "";
                        listaVarFun[posListaVarFun][2] = analizadoLex[i][0];
                        posListaVarFun++;
                        
                    } else if (analizadoLex[i][1].equals("1") 
                            || analizadoLex[i][1].equals("2") || analizadoLex[i][1].equals("3")) {
                        listaVarFun[posListaVarFun][0] = String.valueOf(nivelVar);
                        listaVarFun[posListaVarFun][1] = "";
                        listaVarFun[posListaVarFun][2] = analizadoLex[i][0];
                        posListaVarFun++;
                    }
                }
                nivelVar = nivelActual;
            }
            
            // IF y WHILE
            else if (analizadoLex[i][1].equals("19") || analizadoLex[i][1].equals("20")) {
                // Se agrega el método a la que se le asignará un valor
                listaVarFun[posListaVarFun][0] = String.valueOf(nivelVar);
                listaVarFun[posListaVarFun][1] = "";
                listaVarFun[posListaVarFun][2] = analizadoLex[i][0];
                posListaVarFun++;                
                
                int nivelActual = nivelVar+1;
                nivelSem++;
                nivelVar++;
                i += 2;
                
                listaVarFun[posListaVarFun][0] = String.valueOf(nivelVar);
                listaVarFun[posListaVarFun][1] = "";
                listaVarFun[posListaVarFun][2] = "comp";
                nivelVar++;
                posListaVarFun++;
                
                // Primer valor
                listaVarFun[posListaVarFun][0] = String.valueOf(nivelVar);
                listaVarFun[posListaVarFun][1] = "";
                listaVarFun[posListaVarFun][2] = analizadoLex[i][0];
                // Si es variable
                String tipoPrimerVar = "";
                if (analizadoLex[i][1].equals("0")) {
                    boolean variableEncontrada = false;
                    for (int j = 0; j < posListaSem; j++) {
                        if (analizadoLex[i][0].equals(listaSem[j][2]) && !listaSem[j][1].equals("fun")) {
                            tipoPrimerVar = listaSem[j][1];
                            variableEncontrada = true;

                            if (listaSem[j][3] == null) {
                                listaVarFun[posListaVarFun][0] = "Error semántico: " + listaSem[j][2] + " no inicializada.";
                                return listaVarFun;
                            }
                        }
                    }
                    // Si no existe, se retorna el error
                    if (!variableEncontrada) {
                        listaVarFun[posListaVarFun][0] = "Error semántico: " + analizadoLex[i][0] + " no declarada.";
                        return listaVarFun;
                    }
                    listaVarFun[posListaVarFun][1] = "var";
                    
                // Entero
                } else if (analizadoLex[i][1].equals("1")) {
                   tipoPrimerVar = "int";
                   listaVarFun[posListaVarFun][1] = "int";

               // Float
               } else if (analizadoLex[i][1].equals("2")) {
                   tipoPrimerVar = "float";
                   listaVarFun[posListaVarFun][1] = "float";
               }
                posListaVarFun++;
                i++;
                
                // Operando
                listaVarFun[posListaVarFun][0] = String.valueOf(nivelVar);
                listaVarFun[posListaVarFun][1] = "";
                listaVarFun[posListaVarFun][2] = analizadoLex[i][0];
                posListaVarFun++;
                i++;
                
                // Segundo valor
                listaVarFun[posListaVarFun][0] = String.valueOf(nivelVar);
                listaVarFun[posListaVarFun][1] = "";
                listaVarFun[posListaVarFun][2] = analizadoLex[i][0];
                // Si es variable
                String tipoSegundaVar = "";
                if (analizadoLex[i][1].equals("0")) {
                    boolean variableEncontrada = false;
                    for (int j = 0; j < posListaSem; j++) {
                        if (analizadoLex[i][0].equals(listaSem[j][2]) && !listaSem[j][1].equals("fun")) {
                            tipoSegundaVar = listaSem[j][1];
                            variableEncontrada = true;

                            if (listaSem[j][3] == null) {
                                listaVarFun[posListaVarFun][0] = "Error semántico: " + listaSem[j][2] + " no inicializada.";
                                return listaVarFun;
                            }
                        }
                    }
                    // Si no existe, se retorna el error
                    if (!variableEncontrada) {
                        listaVarFun[posListaVarFun][0] = "Error semántico: " + analizadoLex[i][0] + " no declarada.";
                        return listaVarFun;
                    }
                    listaVarFun[posListaVarFun][1] = "var";
                // Entero
                } else if (analizadoLex[i][1].equals("1")) {
                   tipoSegundaVar = "int";
                   listaVarFun[posListaVarFun][1] = "int";

               // Float
                } else if (analizadoLex[i][1].equals("2")) {
                    tipoSegundaVar = "float";
                    listaVarFun[posListaVarFun][1] = "float";
                }
                // Si no coincide con el tipo de variable
                if (!tipoPrimerVar.equals(tipoSegundaVar)) {
                    listaVarFun[posListaVarFun][0] = "Error semántico: " + analizadoLex[i][0] + " no es " + tipoPrimerVar + ".";
                    return listaVarFun;
                }
                
                posListaVarFun++;
                i++;
                
                nivelVar = nivelActual;
            }
            
        }
        
        return listaVarFun;
    }
}
