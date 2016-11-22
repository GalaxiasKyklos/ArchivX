package main;

import main.AFN.AFNCadena;
import main.AFN.AFNSubcadena;
import main.archivos.Archivos;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Rodolfo on 21/11/2016.
 */
public class Busqueda {
    private boolean cadena;
    private boolean mayuscula;
    private Archivos archivos;

    public Busqueda(){
        cadena=true;
        mayuscula=false;
        archivos= new Archivos();
    }

    public String getPath(){
        return archivos.getCurrentPath();
    }

    public void setCadena(boolean actual){
        cadena=actual;
    }
    public void setMayuscula(boolean actual){
        mayuscula=actual;
    }

    public Hashtable<File,ArrayList<Integer>> verifyString(String palabra){
        Hashtable<File,ArrayList<Integer>> table=new Hashtable<>();
        if(cadena){
            if(mayuscula)palabra=palabra.toLowerCase();
            AFNCadena afnCadena=new AFNCadena(palabra);
            while (archivos.hasNext()){
                String cadena=archivos.nextString();
                if(mayuscula)cadena=cadena.toLowerCase();
                if(cadena.length()>0)
                if(afnCadena.isWElement(cadena)){
                    /*if(!table.containsKey(archivos.getCurrentFile())) {
                        table.put(archivos.getCurrentFile(), null);
                    }*/
                    if(table.get(archivos.getCurrentFile())==null){
                        table.put(archivos.getCurrentFile(),new ArrayList<>());
                        table.get(archivos.getCurrentFile()).add(archivos.getCurrentLine());
                    }else{
                        table.get(archivos.getCurrentFile()).add(archivos.getCurrentLine());
                    }

                }
            }

        }else{
            if(mayuscula)palabra=palabra.toLowerCase();
            AFNSubcadena afnSubcadena=new AFNSubcadena(palabra);
            while (archivos.hasNext()){
                String cadena=archivos.nextString();
                if(mayuscula)cadena=cadena.toLowerCase();
                if(cadena.length()>0)
                if(afnSubcadena.isWElement(cadena)){
                    /*if(!table.containsKey(archivos.getCurrentFile())) {
                        table.put(archivos.getCurrentFile(), null);
                    }*/
                    if(table.get(archivos.getCurrentFile())==null){
                        table.put(archivos.getCurrentFile(),new ArrayList<>());
                        table.get(archivos.getCurrentFile()).add(archivos.getCurrentLine());
                    }else{
                        table.get(archivos.getCurrentFile()).add(archivos.getCurrentLine());
                    }

                }
            }
        }

        return table;
    }
    public void resetFile(){
        archivos.reset();
    }


}
