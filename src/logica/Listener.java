package logica;

import gui.Interfaz;
import java.awt.*;
import java.awt.event.*;

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
        
        if (e.getSource() == interfaz.bReiniciar) {
            interfaz.txtpaneCodigo.setText("");
            interfaz.txtpaneResultado.setText("");
        }

        if (e.getSource() == interfaz.bLexico) {
            interfaz.analizadorActual = 1;
            
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
            
            // Se manda todo el texto de txtpaneCodigo
            // y se retorna un arreglo de dos dimensiones 
            // (una con la subcadena y otra con el tipo)
            
            String[][] analizado;
            AnalizadorLexico anLexico = new AnalizadorLexico();
            analizado = anLexico.lexico(interfaz.txtpaneCodigo.getText());
            String texto = "Símbolo - Tipo\n";
            for (int i = 0; i < 100; i++) {
                if (analizado[i][0] == null) {
                    break;
                }

                // Se revisa si el tipo es igual a 24, en cuyo caso se imprime ERROR
                // Si no, se imprime la cadena y el tipo
                if (analizado[i][1].length() == 2) {
                    if (analizado[i][1].charAt(0) == '2' && analizado[i][1].charAt(1) == '4') {
                        texto += analizado[i][0] + " - ERROR\n";
                    } else {
                        texto += analizado[i][0] + " - " + analizado[i][1] + "\n";
                    }
                } else {
                    texto += analizado[i][0] + " - " + analizado[i][1] + "\n";
                }
                
            }
            interfaz.txtpaneResultado.setText(texto);
            
            
        } else if (e.getSource() == interfaz.bCorrer && interfaz.analizadorActual == 2) {   // SINTACTICO

        } else if (e.getSource() == interfaz.bCorrer && interfaz.analizadorActual == 3) {   // SEMANTICO

        }
    }
}
