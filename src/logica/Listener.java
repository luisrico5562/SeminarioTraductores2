package logica;

import gui.Interfaz;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Listener implements ActionListener {

    private final Interfaz interfaz;

    // Colores fondo
    Color gris = new Color(22, 22, 22);
    Color grisClaro = new Color(31, 31, 31);
    Color grisOscuro = new Color(15, 15, 15);

    // Colores fuente
    Color colorFuente = new Color(232, 232, 232);
    Color colorFuenteTB = new Color(160, 160, 160);
    Color colorFuenteAzul = new Color(0, 122, 226);

    // Fuentes
    Font fuenteInterfaz = new Font("Microsoft PhagsPa", Font.PLAIN, 18);
    Font fuenteTaskbar = new Font("Microsoft PhagsPa", Font.BOLD, 18);
    Font fuenteCodigo = new Font("Roboto Mono", Font.PLAIN, 18);

    public Listener(Interfaz bInterfaz) {
        interfaz = bInterfaz;
    }

    public void actionPerformed(ActionEvent e) {
        
        // Botones interfaz
        if (e.getSource() == interfaz.bReiniciar) {
            interfaz.txtpaneCodigo.setText("");
            interfaz.txtpaneResultado.setText("");
        }
        if (e.getSource() == interfaz.bLexico) {
            interfaz.analizadorActual = 1;
            interfaz.txtpaneResultado.setText("");
            
            interfaz.bLexico.setBackground(gris);
            interfaz.bLexico.setForeground(colorFuenteAzul);
            interfaz.bLexico.setFont(fuenteTaskbar);
            
            interfaz.bSintactico.setBackground(grisOscuro);
            interfaz.bSintactico.setForeground(colorFuenteTB);
            interfaz.bSintactico.setFont(fuenteInterfaz);
            
            interfaz.bSemantico.setBackground(grisOscuro);
            interfaz.bSemantico.setForeground(colorFuenteTB);
            interfaz.bSemantico.setFont(fuenteInterfaz);
        }
        if (e.getSource() == interfaz.bSintactico) {
            interfaz.analizadorActual = 2;
            interfaz.txtpaneResultado.setText("");
            
            interfaz.bLexico.setBackground(grisOscuro);
            interfaz.bLexico.setForeground(colorFuenteTB);
            interfaz.bLexico.setFont(fuenteInterfaz);
            
            interfaz.bSintactico.setBackground(gris);
            interfaz.bSintactico.setForeground(colorFuenteAzul);
            interfaz.bSintactico.setFont(fuenteTaskbar);
            
            interfaz.bSemantico.setBackground(grisOscuro);
            interfaz.bSemantico.setForeground(colorFuenteTB);
            interfaz.bSemantico.setFont(fuenteInterfaz);
        }
        if (e.getSource() == interfaz.bSemantico) {
            interfaz.analizadorActual = 3;
            interfaz.txtpaneResultado.setText("");
            
            interfaz.bLexico.setBackground(grisOscuro);
            interfaz.bLexico.setForeground(colorFuenteTB);
            interfaz.bLexico.setFont(fuenteInterfaz);
            
            interfaz.bSintactico.setBackground(grisOscuro);
            interfaz.bSintactico.setForeground(colorFuenteTB);
            interfaz.bSintactico.setFont(fuenteInterfaz);
            
            interfaz.bSemantico.setBackground(gris);
            interfaz.bSemantico.setForeground(colorFuenteAzul);
            interfaz.bSemantico.setFont(fuenteTaskbar);
        }

        if (e.getSource() == interfaz.bCorrer && interfaz.analizadorActual == 1) {          // LEXICO
            
            // Se manda todo el texto de txtPaneCodigo
            // y se retorna un arreglo de dos dimensiones 
            // (una con la subcadena y otra con el tipo)
            
            String[][] analizado;
            AnalizadorLexico anLexico = new AnalizadorLexico();
            analizado = anLexico.lexico(interfaz.txtpaneCodigo.getText().trim());
            String texto = "SÍMBOLO [NUM] TIPO\n\n";
            for (int i = 0; i < 100; i++) {
                if (analizado[i][0] == null) {
                    break;
                }
                // Se revisa si el tipo es igual a 24, en cuyo caso se imprime ERROR
                // Si no, se imprime la cadena y el tipo
                if (Integer.parseInt(analizado[i][1]) == 24) {
                    texto += analizado[i][0] + " - ERROR";
                } else {
                    texto += analizado[i][0] + " [" + analizado[i][1] + "] ";
                }
                
                // Se agrega el tipo a cada dato
                switch (Integer.parseInt(analizado[i][1])) {
                    case 0: texto += "identificador"; break;
                    case 1: texto += "entero"; break;
                    case 2: texto += "real"; break;
                    case 3: texto += "cadena"; break;
                    case 4: texto += "tipo"; break;
                    case 5: texto += "opSuma"; break;
                    case 6: texto += "opMul"; break;
                    case 7: texto += "opRelac"; break;
                    case 8: texto += "opOr"; break;
                    case 9: texto += "opAnd"; break;
                    case 10: texto += "opNot"; break;
                    case 11: texto += "opIgualdad"; break;
                    default: break;
                }
                texto += "\n";
            }
            interfaz.txtpaneResultado.setText(texto);
            
            
        } else if (e.getSource() == interfaz.bCorrer && interfaz.analizadorActual == 2) {   // SINTACTICO
            
            String[][] analizadoLex;
            AnalizadorLexico anLexico = new AnalizadorLexico();
            analizadoLex = anLexico.lexico(interfaz.txtpaneCodigo.getText().trim());
            
            //String[] valoresLex = new String[100];
            Stack pilaLex = new Stack();
            boolean errorLexico = false;
            int largoAnLex = 0;
            
            // Analizando la entrada con el analizador léxico
            for (int i = 0; i < 100; i++) {
                if (analizadoLex[i][0] == null) {
                    largoAnLex = i;
                    break;
                }
                
                if (Integer.parseInt(analizadoLex[i][1]) == 24) {
                    interfaz.txtpaneResultado.setText("Error léxico: '" + analizadoLex[i][0] + "'");
                    errorLexico = true;
                    break;
                }
                pilaLex.push(analizadoLex[i][1]);
            }
            
            // Invirtiendo la pila
            Stack pilaLexVolteada = new Stack();
            pilaLexVolteada.push("$");
            while (pilaLex.empty() != true) {
                pilaLexVolteada.push(pilaLex.peek());
                pilaLex.pop();
            }
            
            // Se analiza sintácticamente si no hay errores léxicos
            if (errorLexico == false) {
                String[][] analizadoSin;
                AnalizadorSintactico anSintactico = new AnalizadorSintactico();
                analizadoSin = anSintactico.sintactico(pilaLexVolteada, analizadoLex, largoAnLex);
                
                String texto = "";
                
                // Se imprimen los estados de la pila
                for (int i = 0; i < 100; i++) {
                    if (analizadoSin[i][0] == null) {
                        break;
                    }
                    texto += analizadoSin[i][0] + "\n";
                }
                interfaz.txtpaneResultado.setText(texto);
            }

        } else if (e.getSource() == interfaz.bCorrer && interfaz.analizadorActual == 3) {   // SEMANTICO
            
            interfaz.txtpaneResultado.setText("");

        }
    }
}
