/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.archivos;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Saúl
 */
public class Archivos {
    private File files[];
    private String currentStr[];
    private File currentFile;
    private int innerIndex;
    private int outerIndex;
    
    public Archivos() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto", "txt");
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int c = chooser.showOpenDialog(null);
        
        if (c == JFileChooser.APPROVE_OPTION) {
            File folder = chooser.getCurrentDirectory();
            files = folder.listFiles();
        }
        
        innerIndex = 0;
        outerIndex = 0;
    }
    
    public String nextString() {
        return "";
    }
}
