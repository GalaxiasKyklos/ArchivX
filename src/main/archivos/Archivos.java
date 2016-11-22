/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Permite crear un objeto que conecta a un directorio y habre los txt en él
 * @author Saúl
 */
public class Archivos {
    private File files[];
    private ArrayList<String[]> currentStr;
    private int fileIndex;
    private int lineIndex;
    private int strIndex;
    private String currentPath;
    
    
    /**
     * Constructor, crea un JFileChooser para elegir el directorio en el que se va a buscar
     * @param parent 
     */
    public Archivos(JFrame parent) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto", "txt");
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int c = chooser.showOpenDialog(parent);
        
        if (c == JFileChooser.APPROVE_OPTION) {
            File folder = chooser.getSelectedFile();
            currentPath = chooser.getSelectedFile().getAbsolutePath();
            System.out.println(chooser.getCurrentDirectory().getAbsolutePath());

            files = folder.listFiles((File dir, String name) -> name.toLowerCase().endsWith(".txt"));
        }
        
        fileIndex = 0;
        lineIndex = 0;
        strIndex = 0;
        
        currentStr = new ArrayList<>();
        
        readFile(0);
    }
    
    /**
     * Regresa la siguiente palabra en los archivos del directorio
     * @return palabra siguiente
     */
    public String nextString() {
        if (strIndex == currentStr.get(lineIndex).length) {
            strIndex = 0;
            lineIndex++;
        }
        
        if (lineIndex == currentStr.size())
            if (fileIndex != files.length - 1) {
                lineIndex = 0;
                strIndex = 0;
                readFile(++fileIndex);
            }
        
        return currentStr.get(lineIndex)[strIndex++];
    }
    
    /**
     * Abre un archivo del directorio y lo carga a memoria, de uso interno de la clase
     * @param index número de archivo en el directorio
     */
    private void readFile(int index) {
        currentStr.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(files[index]))) {
                String line;
                while ((line = br.readLine()) != null) {
                    currentStr.add(line.split(" "));
                    //System.out.println(line);
                }
        } catch (Exception e) {}
    }
    
    /**
     * Permite verificar si hay una palabra siguiente en el directorio
     * @return true si aun hay palabras en el directorio, false en otro caso
     */
    public boolean hasNext() {
        return !(fileIndex == files.length - 1 && lineIndex == currentStr.size() - 1 && strIndex == currentStr.get(lineIndex).length);
    }
    
    /**
     * Regresa el archivo actual
     * @return archivo que se está leyendo en ese momento
     */
    public File getCurrentFile() {
        return files[fileIndex];
    }
    
    /**
     * Regresa la linea actual en el archivo actual
     * @return línea actual que se está leyendo
     */
    public int getCurrentLine() {
        return lineIndex+1;
    }
    
    /**
     * Regresa el directorio actual
     * @return directorio que se está leyendo
     */
    public String getCurrentPath() {
        return currentPath;
    }
    
    /**
     * Restablece los índice internos de la clase
     */
    public void reset(){
        strIndex=0;
        fileIndex=0;
        lineIndex=0;
        readFile(0);
    }
}
