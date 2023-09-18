package gui;

import javax.swing.*;
import java.awt.*;
import logica.Listener;

//public class Interfaz extends JFrame implements ActionListener {
public class Interfaz extends JFrame {
    
    // Taskbar
    private JPanel pBarraLateral, pTaskbar;
    private JLabel lanalizador;
    public JButton bReiniciar, bLexico, bSintactico, bSemantico;
    public int analizadorActual = 1; // 1 = léxico, 2 = sintáctico, 3 = semántico
    
    // Código
    private JPanel pCodigo;
    private JLabel lCodigo;
    public JScrollPane scrollCodigo;
    public JTextPane txtpaneCodigo;
    
    // Resultado
    private JPanel pResultadoSuperior, pResultadoInferior;
    private JLabel lResultado;
    public JButton bCorrer;
    public JScrollPane scrollResultado;
    public JTextPane txtpaneResultado;
    
    public Interfaz() {
        setLayout(null); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Colores fondo
        Color gris = new Color(22, 22, 22);
        Color grisClaro = new Color (31, 31, 31);
        Color grisOscuro = new Color (15, 15, 15);
        
        // Colores fuente
        Color colorFuente = new Color (232, 232, 232);
        Color colorFuenteTB = new Color (160, 160, 160);
        Color colorFuenteAzul = new Color (0, 122, 226);
        
        // Fuentes
        Font fuenteInterfaz = new Font("Microsoft PhagsPa", Font.PLAIN, 18);
        Font fuenteTaskbar = new Font("Microsoft PhagsPa", Font.BOLD, 18);
        Font fuenteCodigo = new Font("Roboto Mono", Font.PLAIN, 18);
        Font fuenteResultado = new Font("Roboto Mono", Font.PLAIN, 16);
        
        // Listener
        Listener listener = new Listener(this);
        
        // ### Taskbar ###
        lanalizador = new JLabel("Analizador");
        lanalizador.setBounds(20, 0, 100, 40);
        lanalizador.setForeground(colorFuenteAzul);
        lanalizador.setFont(fuenteTaskbar);
        add(lanalizador);

        bReiniciar = new JButton("Reiniciar");
        bReiniciar.setBounds(130, 0, 140, 40);
        bReiniciar.setBackground(gris);
        bReiniciar.setForeground(colorFuenteTB);
        bReiniciar.setFont(fuenteInterfaz);
        bReiniciar.setBorderPainted(false);
        bReiniciar.setFocusPainted(false);
        bReiniciar.addActionListener(listener);
        add(bReiniciar);
        
        bLexico = new JButton("Lexico");
        bLexico.setBounds(0, 40, 130, 40);
        bLexico.setBackground(gris);
        bLexico.setForeground(colorFuenteAzul);
        bLexico.setFont(fuenteTaskbar);
        bLexico.setBorderPainted(false);
        bLexico.setFocusPainted(false);
        bLexico.addActionListener(listener);
        add(bLexico);
        
        bSintactico = new JButton("Sintáctico");
        bSintactico.setBounds(0, 80, 130, 40);
        bSintactico.setBackground(grisOscuro);
        bSintactico.setForeground(colorFuenteTB);
        bSintactico.setFont(fuenteInterfaz);
        bSintactico.setBorderPainted(false);
        bSintactico.setFocusPainted(false);
        bSintactico.addActionListener(listener);
        add(bSintactico);
        
        bSemantico = new JButton("Semántico");
        bSemantico.setBounds(0, 120, 130, 40);
        bSemantico.setBackground(grisOscuro);
        bSemantico.setForeground(colorFuenteTB);
        bSemantico.setFont(fuenteInterfaz);
        bSemantico.setBorderPainted(false);
        bSemantico.setFocusPainted(false);
        bSemantico.addActionListener(listener);
        add(bSemantico);
        
        // ### Código ###
        
        lCodigo = new JLabel("Código");
        lCodigo.setBounds(150, 40, 850, 40);
        lCodigo.setForeground(colorFuente);
        lCodigo.setFont(fuenteInterfaz);
        add(lCodigo);
        
        txtpaneCodigo = new JTextPane();
        txtpaneCodigo.setFont(fuenteCodigo);
        txtpaneCodigo.setForeground(colorFuente);
        txtpaneCodigo.setBackground(grisClaro);
        scrollCodigo = new JScrollPane(txtpaneCodigo);
        scrollCodigo.setBounds(140, 90, 435, 485);
        scrollCodigo.setBorder(null);
        add(scrollCodigo);
        
        // ### Resultado ###
        lResultado = new JLabel("Resultado");
        lResultado.setBounds(600, 40, 850, 40);
        lResultado.setForeground(colorFuente);
        lResultado.setFont(fuenteInterfaz);
        add(lResultado);
        
        bCorrer = new JButton("▷");
        bCorrer.setBounds(990, 41, 60, 38);
        bCorrer.setBackground(grisClaro);
        bCorrer.setForeground(colorFuenteAzul);
        bCorrer.setFont(new Font("SansSerif", Font.BOLD, 24));
        bCorrer.setBorderPainted(false);
        bCorrer.setFocusPainted(false);
        bCorrer.addActionListener(listener);
        add(bCorrer);
        
        txtpaneResultado = new JTextPane();
        txtpaneResultado.setFont(fuenteResultado);
        txtpaneResultado.setForeground(colorFuente);
        txtpaneResultado.setBackground(grisClaro);
        txtpaneResultado.setEditable(false);
        scrollResultado = new JScrollPane(txtpaneResultado);
        scrollResultado.setBounds(590, 90, 445, 485);
        scrollResultado.setBorder(null);
        add(scrollResultado);
        
        // ### Páneles ###
        pBarraLateral = new JPanel();
        pBarraLateral.setBounds(0, 40, 131, 550);
        pBarraLateral.setBackground(grisOscuro);
        pBarraLateral.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(pBarraLateral);
        
        pTaskbar = new JPanel();
        pTaskbar.setBounds(0, 0, 1050, 41);
        pTaskbar.setBackground(gris);
        pTaskbar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(pTaskbar);
        
        pResultadoSuperior = new JPanel();
        pResultadoSuperior.setBounds(580, 40, 850, 40);
        pResultadoSuperior.setBackground(grisClaro);
        pResultadoSuperior.setBorder(BorderFactory.createLineBorder(gris));
        add(pResultadoSuperior);
        
        pResultadoInferior = new JPanel();
        pResultadoInferior.setBounds(580, 78, 850, 530);
        pResultadoInferior.setBackground(grisClaro);
        pResultadoInferior.setBorder(BorderFactory.createLineBorder(gris));
        add(pResultadoInferior);
        
        pCodigo = new JPanel();
        pCodigo.setBounds(110, 40, 850, 40);
        pCodigo.setBackground(grisClaro);
        pCodigo.setBorder(BorderFactory.createLineBorder(gris));
        add(pCodigo);
    }
    
    /*
    public void actionPerformed(ActionEvent e) {
        
    }
    */
    
    /*
    public static void main(String[] args) {
        Interfaz interfaz = new Interfaz();
        Color fondo = new Color(31,31,31);
        
        interfaz.setBounds(0, 0, 960, 540);
        interfaz.getContentPane().setBackground(fondo);
        interfaz.setUndecorated(false);
        interfaz.setTitle("Analizador léxico");
        interfaz.setVisible(true);
        interfaz.setResizable(false);
        interfaz.setLocationRelativeTo(null);
        
        //interfaz.txtpaneResultado.setText(" TEXTO ");
    }
    */
}
