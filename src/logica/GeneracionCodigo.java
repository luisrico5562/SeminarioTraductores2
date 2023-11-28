package logica;

public class GeneracionCodigo {
    
    private String codigoIf(String[][] analizadoSem, int tamanoArreglo, int posArreglo) {
        
        /* FUNCIONAMIENTO IF
            CMP
            JMP-contrario etiqueta
                # código
            etiqueta:
        */
        
        String codigo = "";
        
        // Se mueven los valores de las variables a los registros AL y CL 
        // para ser comparados
        codigo += "MOV AL,";
        if (analizadoSem[1][1].equals("var")) codigo += "VAR";
        codigo += analizadoSem[1][2] + "\nMOV CL,";
        if (analizadoSem[3][1].equals("var")) codigo += "VAR";
        codigo += analizadoSem[3][2] + "\nCMP AL,CL\n";
        posArreglo += 3;
        
        // Se usa el JMP contrario al de la condición
        if (analizadoSem[2][2].equals("==")) {
            codigo += "JNE";
        } else if (analizadoSem[2][2].equals("!=")) {
            codigo += "JE";
        } else if (analizadoSem[2][2].equals(">")) {
            codigo += "JNG";
        } else if (analizadoSem[2][2].equals("<")) {
            codigo += "JNL";
        } else if (analizadoSem[2][2].equals(">=")) {
            codigo += "JNGE";
        } else if (analizadoSem[2][2].equals("<=")) {
            codigo += "JNLE";
        }
        codigo += " IF" + posArreglo + "\n";
        
        // Se crea un subarreglo con el código dentro del IF
        String[][] subarreglo = new String[tamanoArreglo-4][3];
        for (int j = 0; j < tamanoArreglo-4; j++) {
            subarreglo[j] = analizadoSem[j+4];
        }
        
        // Se analiza el subarreglo por separado
        codigo += general(analizadoSem, posArreglo);
        
        codigo += "IF" + posArreglo + ":\n";
        
        return codigo;
    }
    
    private String codigoWhile(String[][] analizadoSem, int tamanoArreglo, int posArreglo) {
        
        /* FUNCIONAMIENTO WHILE
            etiquetaA:
            CMP
            JMP-contrario etiquetaB
                # código
            JMP etiquetaA
            etiquetaB:
        */
        
        String codigo = "";
        
        codigo += "WHILEA" + posArreglo + ":\n";
        
        // Se mueven los valores de las variables a los registros AL y CL 
        // para ser comparados
        codigo += "MOV AL,";
        if (analizadoSem[1][1].equals("var")) codigo += "VAR";
        codigo += analizadoSem[1][2] + "\nMOV CL,";
        if (analizadoSem[3][1].equals("var")) codigo += "VAR";
        codigo += analizadoSem[3][2] + "\nCMP AL,CL\n";
        
        // Se usa el JMP contrario al de la condición
        if (analizadoSem[2][2].equals("==")) {
            codigo += "JNE";
        } else if (analizadoSem[2][2].equals("!=")) {
            codigo += "JE";
        } else if (analizadoSem[2][2].equals(">")) {
            codigo += "JNG";
        } else if (analizadoSem[2][2].equals("<")) {
            codigo += "JNL";
        } else if (analizadoSem[2][2].equals(">=")) {
            codigo += "JNGE";
        } else if (analizadoSem[2][2].equals("<=")) {
            codigo += "JNLE";
        }
        codigo += " WHILEB" + posArreglo + "\n";
        
        // Se crea un subarreglo con el código dentro del WHILE
        String[][] subarreglo = new String[tamanoArreglo-4][3];
        for (int j = 0; j < tamanoArreglo-4; j++) {
            subarreglo[j] = analizadoSem[j+4];
        }
        
        // Se analiza el subarreglo por separado
        posArreglo += 3;
        codigo += general(analizadoSem, posArreglo);
        posArreglo -= 3;
        
        codigo += "JMP WHILEA" + posArreglo + "\n";
        codigo += "WHILEB" + posArreglo + ":\n";
        
        return codigo;
    }

    public String general(String[][] analizadoSem, int posArreglo) {
        
        String codigo = "";
        
        // Se obtiene el tamaño del arreglo
        int tamanoArreglo = 0;
        for (int i = 0; i < analizadoSem.length; i++) {
            if (analizadoSem[i][0] == null) break;
            tamanoArreglo++;
        }
        
        for (int i = 0; i < analizadoSem.length; i++) {
            posArreglo++;
            if (analizadoSem[i][0] == null) break;
            
            // Asignación de variable
            
            if (analizadoSem[i][1].equals("asig")) {
                codigo += "MOV AL,VAR" + analizadoSem[i][2] + "\nMOV AUX,AL\n";
                String variable = analizadoSem[i][2];
                
                int nivelAsignacion = Integer.parseInt(analizadoSem[i][0]);
                i++;
                posArreglo++;
                // Si se asigna un valor
                if (analizadoSem[i][1].equals("int")) {
                    codigo += "MOV VAR" + variable + "," + analizadoSem[i][2] + "\n";
                }
                // Si se signa su propio valor
                else if (analizadoSem[i][1].equals("var") && analizadoSem[i][2].equals(variable)) {
                    codigo += "MOV AL,AUX\nMOV VAR" + variable + ",AL\n";
                }
                // Si se asigna una variable
                else if (analizadoSem[i][1].equals("var")) {
                    codigo += "MOV AL,VAR" + analizadoSem[i][2] + "\nMOV VAR" + variable + ",AL\n";
                }
                
                // Si se hace una operación
                if (i+1 >= analizadoSem.length || analizadoSem[i+1][0] == null) break;
                if (Integer.parseInt(analizadoSem[i+1][0]) > nivelAsignacion) {
                    do {
                        i++;
                        posArreglo++;
                        if (i >= analizadoSem.length || analizadoSem[i][2] == null || Integer.parseInt(analizadoSem[i][0]) <= nivelAsignacion) break;
                        
                        String operacion = analizadoSem[i][2];
                        i++;
                        posArreglo++;
                        codigo += "MOV AL,VAR" + variable + "\n";
                        
                        // Operaciones
                        if (operacion.equals("+"))      codigo += "ADD AL,";
                        else if (operacion.equals("-")) codigo += "SUB AL,";
                        else if (operacion.equals("*")) codigo += "IMUL ";
                        else if (operacion.equals("/")) codigo += "DIV ";
                        
                        // Entero
                        if (analizadoSem[i][1].equals("int")) {
                            codigo += analizadoSem[i][2] + "\n";

                        // Variable
                        } else {
                            codigo += "VAR" + analizadoSem[i][2] + "\n";
                        }
                        codigo += "MOV VAR" + variable + ",AL\n";
                        
                    } while (Integer.parseInt(analizadoSem[i][0]) > nivelAsignacion);
                }
                if (i >= tamanoArreglo) break;
                if (analizadoSem[i][0] == null) return codigo;
            }
            // PRINT()
            if (analizadoSem[i][1].equals("fun") && analizadoSem[i][2].equals("print")) {
                
                int nivelPrint = Integer.parseInt(analizadoSem[i][0]);
                do {
                    i++;
                    posArreglo++;
                    if (i >= analizadoSem.length) break;
                    if (analizadoSem[i][0] == null) break;
                    if (Integer.parseInt(analizadoSem[i][0]) <= nivelPrint) {
                        i--;
                        break;
                    }
                    // Si se imprime una cadena
                    if (analizadoSem[i][2].charAt(0) == '"') {
                        codigo += "MOV AH,2\n";
                        for (int j = 1; j < analizadoSem[i][2].length(); j++) {
                            if (analizadoSem[i][2].charAt(j) == '"') break;
                            
                            char comillas = 34;
                            codigo += "MOV DL, " + comillas + analizadoSem[i][2].charAt(j) + comillas + "\nINT 21H\n";
                        }

                    // Si se imprime una variable ################################################################
                    } else {
                        codigo +=   "MOV AL,VAR" + analizadoSem[i][2] + 
                                    "\nAAM\n" +
                                    "MOV UNI,AL\n" +
                                    "MOV AL,AH\n" +
                                    "AAM\n" +
                                    "MOV CEN,AH\n" +
                                    "MOV DECE,AL\n" +
                                    "MOV AH,02H\n" +
                                    "MOV DL,CEN\n" +
                                    "ADD DL,30H\n" +
                                    "INT 21H\n" +
                                    "MOV DL,DECE\n" +
                                    "ADD DL,30H\n" +
                                    "INT 21H\n" +
                                    "MOV DL,UNI\n" +
                                    "ADD DL,30H\n" +
                                    "INT 21H\n";
                    }
                } while (Integer.parseInt(analizadoSem[i][0]) > nivelPrint);
                if (i >= tamanoArreglo) return codigo; 
                if (analizadoSem[i][0] == null) return codigo;
            }
            
            // if
            if (analizadoSem[i][2].equals("if")) {
                int nivelIf = Integer.parseInt(analizadoSem[i][0]);
                
                // Se obtiene el largo del subarreglo dentro del if
                int tamanoSubarreglo = 0;
                for (int j = i+1; j < analizadoSem.length; j++) {
                    if (analizadoSem[j][0] == null) break;
                    if (Integer.parseInt(analizadoSem[j][0]) <= nivelIf) break;
                    tamanoSubarreglo++;
                }
                // Se copia el contenido del subarreglo 
                String[][] subarreglo = new String[tamanoSubarreglo][3];
                for (int j = 0; j < tamanoSubarreglo; j++) {
                    subarreglo[j] = analizadoSem[i+j+1];
                }
                // Se llama el método para el if
                codigo += codigoIf(subarreglo, tamanoSubarreglo, i);
                
                // Se actualiza la posición actual
                i += tamanoSubarreglo;
                posArreglo += tamanoSubarreglo;
                
                if (analizadoSem[i][0] == null) return codigo;
            }
            
            // while
            if (analizadoSem[i][2].equals("while")) {
                
                int nivelWhile = Integer.parseInt(analizadoSem[i][0]);
                
                // Se obtiene el largo del subarreglo dentro del if
                int tamanoSubarreglo = 0;
                for (int j = i+1; j < analizadoSem.length; j++) {
                    if (analizadoSem[j][0] == null) break;
                    if (Integer.parseInt(analizadoSem[j][0]) <= nivelWhile) break;
                    tamanoSubarreglo++;
                }
                // Se copia el contenido del subarreglo 
                String[][] subarreglo = new String[tamanoSubarreglo][3];
                for (int j = 0; j < tamanoSubarreglo; j++) {
                    subarreglo[j] = analizadoSem[i+j+1];
                }
                
                // Se llama el método para el if
                codigo += codigoWhile(subarreglo, tamanoSubarreglo, i);
                
                // Se actualiza la posición actual
                i += tamanoSubarreglo;
                posArreglo += tamanoSubarreglo;
                
                if (analizadoSem[i][0] == null) return codigo;
            }
        }
        return codigo;
    }
    
    public String generarCodigo(String[][] analizadoSem, int posArreglo) {
        String codigo = ";programa.asm\nORG 100H\n.DATA\n";
        
        // Variable auxiliar para operaciones
        codigo += "AUX DB 0H\nUNI DB 0H\nDECE DB 0H\nCEN DB 0H\n";

        // Declaración de variables
        for (int i = 0; i < analizadoSem.length; i++) {
            if (analizadoSem[i][0] == null) break;
            
            if (analizadoSem[i][1].contains("dec") && analizadoSem[i][1].contains("int")) {
                codigo += "VAR" + analizadoSem[i][2] + " DB 0H" + "\n";
            }
        }
        
        codigo += ".CODE\n";
        
        codigo += general(analizadoSem, posArreglo);
        
        codigo += "MOV AH,4CH\nINT 21H\nRET";
        
        return codigo;
    }
}
