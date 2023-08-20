package logica;

import java.util.Scanner;

public class Prueba {
    static String[] palabras(String cadena) {
            
        String[] resultado = new String[20];
        
        int posResultado = 0;
        int espacios = 0;
        
        for (int i = 0; i < cadena.length(); i++) {
            // Se inicializa la primer posición del arreglo
            if (posResultado == 0 && i == 0) {
                resultado[posResultado] = "";
            }
            // Se agrega el caracter si no es un espacio
            if (espacios == 0 && (cadena.charAt(i) != ' ' || cadena.charAt(i) != '\n')) {
                resultado[posResultado] += cadena.charAt(i);
                
            // Si se detecta un primer espacio se cambia a espacios para no guardar información
            } else if (espacios == 0 && (cadena.charAt(i) == ' ' || cadena.charAt(i) == '\n')) {
                espacios = 1;
                
            // Si se detecta un primer caracter se cambia a caracteres para empezar a guardar información
            // Además e avanza a la siguiente posición del arreglo con las subcadenas
            } else if (espacios == 1 && (cadena.charAt(i) != ' ' || cadena.charAt(i) != '\n')) {
                espacios = 0;
                posResultado++;
                resultado[posResultado] = "";
                resultado[posResultado] += cadena.charAt(i);
            }
        }
        
        return resultado;
    }
    
    public static void main(String[] args) {
        
        String cadena;
        Scanner entrada = new Scanner(System.in);
        
        System.out.print("Cadena: ");
        cadena = entrada.nextLine();
        
        String[] resultado = new String[20];
        
        resultado = palabras(cadena);
        
        for (int i = 0; i < 20; i++) {
            if (resultado[i] != null) { // NULLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL
                System.out.println(resultado[i]);
            }
        }
    }
}
