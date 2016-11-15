/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.AFN;

import java.util.Hashtable;

/**
 *
 * @author Saúl
 */
public class AFNSubcadena {
    private int estados;
    private int estadoactual;
    private int estadosfinales;
    private String alfabeto[];
    Hashtable<String, Integer> transicion=new Hashtable<>();

    /**
     * Constructor
     * @param palabra Palabra que aceptara el autómata a crear
     */
    public AFNSubcadena(String palabra){
        estados=palabra.length()+1;
        estadosfinales=palabra.length();
        estadoactual=0;
        transicion.put(""+0+"(",0);
        transicion.put(""+0+" ",0);
        
        // Agregamos transiciones para que el autómata acepte cualquier cosa al 
        // principio y al final
        for (int i = 0; i < 0xFF; i++) {
            transicion.put("0" + ((char) i), 0);
            transicion.put((estados-1) + "" + ((char) i), estados-1);
        }
        
        
        for(int i=0;i<estados-1;i++){
            String key=""+i+palabra.charAt(i);
            transicion.put(key, i+1);
        }
        transicion.put(""+estadosfinales+".",estadosfinales);
        transicion.put(""+estadosfinales+",",estadosfinales);
        transicion.put(""+estadosfinales+";",estadosfinales);
        transicion.put(""+estadosfinales+":",estadosfinales);
        transicion.put(""+estadosfinales+"'",estadosfinales);
        transicion.put(""+estadosfinales+")",estadosfinales);
        transicion.put(""+estadosfinales+" ",estadosfinales);

    }
    
    /**
     * Obtienene el siguiente estado
     * @param actual Estado acutal
     */
    private void nextState(String actual){
        String key="";
        key+=estadoactual+actual;
        //estadoactual= Integer.parseInt(transicion.get(key));
    }
    
    /**
     * Determina si un estado es final o no
     * @param estado Estado a verificar
     * @return true si el estado es final, false en otro caso
     */
    private Boolean isFinalState(int estado){
        if(estado==estadosfinales){
            return true;
        }
        return false;
    }
    
    /**
     * Función para determinar si la cadena w es aceptada por el autómata
     * Solo llama a otra que hace entrabajo
     * @param w cadena a verificar
     * @return true si es aceptada, false en otro caso
     */
    public boolean isWElement(String w){
        return isWElement(w,0);
    }

    /**
     * Determina si la cadena w es aceptada por el autómata, función recursiva
     * @param w cadena a verificar
     * @param estado esatdo actual de en la llamada recursiva
     * @return true si es aceptada, false en otro caso
     */
    private Boolean isWElement(String w, int estado){
        String key=""+estado+w.substring(0,1);
        String keylambda=""+estado+"lamb";
        int numestados;
        if(transicion.containsKey(key)){
            System.out.println(key);
            if(w.length()==1&&isFinalState(transicion.get(key))){
                return true;
            }else if (w.length()>1){
                if(isWElement(w.substring(1),transicion.get(key))){
                    return true;
                }
            }else if(w.length()==1){
                if(lastLambda(transicion.get(key))){
                    return true;
                }
            }
        }

        return false;
    }
    
    /**
     * Determina si se puede llegar a un estado final sólo con transiciones con lambda
     * @param estado estado actual de la llamada recursiva
     * @return true si se llega a un estado final, false en otro caso
     */
    public Boolean lastLambda(int estado){
        String key=""+estado+"lamb";
        int numestados;
        if(transicion.containsKey(key)){
            //numestados=transicion.get(key).length;
            //for(int i=0;i<numestados;i++){
                if(isFinalState(transicion.get(key))){
                    return true;
                }else{
                    if(lastLambda(transicion.get(key))){
                        return true;
                    }
                }
            //}
        }else{
            return false;
        }
        return false;
    }
}
