/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Saúl
 */
public class Archivos {
    private File files[];
    private ArrayList<String[]> currentStr;
    private File currentFile;
    private int fileIndex;
    private int lineIndex;
    private int strIndex;
    
    public Archivos() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto", "txt");
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int c = chooser.showOpenDialog(null);
        
        if (c == JFileChooser.APPROVE_OPTION) {
            File folder = new File(chooser.getCurrentDirectory().getAbsolutePath());
            System.out.println(chooser.getCurrentDirectory().getAbsolutePath());
            files = folder.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".txt");
                }
             });
        }
        
        fileIndex = 0;
        lineIndex = 0;
        strIndex = 0;
        
        currentStr = new ArrayList<>();
        
        readFile(0);
    }
    
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
    
    public boolean hasNext() {
        return !(fileIndex == files.length - 1 && lineIndex == currentStr.size() - 1 && strIndex == currentStr.get(lineIndex).length);
    }
}
