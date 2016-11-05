package main;

import main.AFN.AFN;

import javax.swing.*;

/**
 * Created by Rodolfo on 05/11/2016.
 */
public class Mainprogram {
    public static void main(String[] args) {
        String palabra= JOptionPane.showInputDialog("Introduzca la palabra a buscar");
        AFN afn=new AFN(palabra);
        String prueba=JOptionPane.showInputDialog("Introduzca palabra a probar con cadena "+palabra);
        if(afn.isWElement(prueba)){
            JOptionPane.showMessageDialog(null,"Palabra aceptada");
        }else{
            JOptionPane.showMessageDialog(null,"Palabra no aceptada");
        }
    }
}
