package main;

import main.AFN.AFNCadena;
import main.AFN.AFNSubcadena;
import main.archivos.Archivos;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JFrame;

/** Clase del algoritmo principal, continene la lógica del programa
 * Created by Rodolfo on 21/11/2016.
 */
public class Busqueda {
    private boolean cadena;
    private boolean mayuscula;
    private Archivos archivos;

    /**
     * Constructor
     * @param parent 
     */
    public Busqueda(JFrame parent){
        cadena=true;
        mayuscula=false;
        archivos= new Archivos(parent);
    }

    /**
     * Regresa el directorio activo
     * @return firectorio actual
     */
    public String getPath(){
        return archivos.getCurrentPath();
    }

    /**
     * Poner la bandera de cadena / subcadena
     * @param actual true para poner el algoritmo en modo cadena, false para modo subcadena
     */
    public void setCadena(boolean actual){
        cadena=actual;
    }
    
    /**
     * Poner la bandera de mayúscula / minúscula
     * @param actual true para poner el algoritmo en modo que las diferencia, false para no diferenciarlas
     */
    public void setMayuscula(boolean actual){
        mayuscula=actual;
    }

    /**
     * Busca la palabra en los archivos, creando el AF requerido
     * @param palabra palabra a buscar
     * @return Tabla hash con las ocurrencias de la palabra
     */
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
    
    /**
     * Resetea el objeto archivos
     */
    public void resetFile(){
        archivos.reset();
    }


}
