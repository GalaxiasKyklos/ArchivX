package main;

import main.AFN.AFNCadena;

import javax.swing.*;
import main.AFN.AFNSubcadena;

/**
 * Created by Rodolfo on 05/11/2016.
 */

/**
 * Clase para probar la creación del Autómata
 * @author Rodolfo
 */
public class Mainprogram {
    public static void main(String[] args) {
        // Creamos el autómata a partir de una palabra
        String palabra= JOptionPane.showInputDialog("Introduzca la palabra a buscar");
        AFNCadena afn=new AFNCadena(palabra);
        AFNSubcadena afnsub = new AFNSubcadena(palabra);
        
        // Simulamos la entrada del archivo con entrada de usuario
        // (la lectura de archivos aun no está implementada)
        String prueba=JOptionPane.showInputDialog("Introduzca palabra a probar con cadena "+palabra);
        if(afn.isWElement(prueba)){
            JOptionPane.showMessageDialog(null,"Palabra aceptada", "Prueba de Cadena", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null,"Palabra no aceptada", "Prueba de Subcadena", JOptionPane.INFORMATION_MESSAGE);
        }
        
        if(afnsub.isWElement(prueba)){
            JOptionPane.showMessageDialog(null,"Palabra aceptada");
        }else{
            JOptionPane.showMessageDialog(null,"Palabra no aceptada");
        }
    }
}
