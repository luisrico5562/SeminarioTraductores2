package logica;

import gui.Interfaz;
import java.awt.Color;

public class MainClass {

    public static void main(String[] args) {
        
        Interfaz interfaz = new Interfaz();
        Color fondo = new Color(31, 31, 31);

        //interfaz.txtpaneCodigo.setSelectionColor(new Color(255,0,0));
        //interfaz.bLexico.setHoverBackgroundColor(new Color(3, 59, 90).brighter());
        
        interfaz.setBounds(0, 0, 960, 540);
        interfaz.getContentPane().setBackground(fondo);
        interfaz.setUndecorated(false);
        interfaz.setTitle("Analizador léxico");
        interfaz.setVisible(true);
        interfaz.setResizable(false);
        interfaz.setLocationRelativeTo(null);
    }
}
