package main.AFN;

import java.util.Hashtable;

/**
 * Created by Rodolfo on 05/11/2016.
 */
public class AFN {
    private int estados;
    private int estadoactual;
    private String[] estadosfinales;
    private String alfabeto[];
    Hashtable<String, String[]> transicion=new Hashtable<>();

    public AFN(int states,String alphabet[], String finalStates[]){
        estados=states;
        alfabeto=alphabet;
        estadosfinales=finalStates;
        estadoactual=0;
    }
    public AFN(String palabra){
        estados=palabra.length()+1;
        String f[]=new String[1];
        f[0]=""+palabra.length();
        estadosfinales=f;
        estadoactual=0;
        for(int i=0;i<estados-1;i++){
            String key=""+i+palabra.charAt(i);
            String tcion[]=new String[1];

                tcion[0] = "" +( i + 1);
                transicion.put(key, tcion);

        }

    }


    public void matrizTransicion(String key,String transiciones[]){
        transicion.put(key,transiciones);
        for(int i=0;i<transiciones.length;i++){
            System.out.println(key+""+transiciones[i]);
        }
    }
    /*public Boolean validW(String w){
        return esElemento(w, alfabeto.length,alfabeto, 0);
    }
    private Boolean esElemento(String w, int leng_l, String palabras[], int index) {
        for (int i = 0; i < leng_l; i++) {
            int length = palabras[i].length();//se obtiene la longitud de la palabra para saber el tamano del substring a obtener
            if (index + length <= w.length()) {//se revisa que no salgamos del tamano de la palabra w
                System.out.println(w.substring(index,index+length));
                System.out.println(palabras[i]);
                if (w.substring(index,index+length).equals(palabras[i].substring(0, 1))) {//se obtiene el substring y se compara
                    System.out.println("ok");
                    if (index + length == w.length()) {
                        return true;//si el tamano de w y el indice actual mas la longitd del elemento actual del lenguaje son iguales entonces w pertenece a L*
                    } else if (esElemento(w, leng_l, palabras, index + length)) {//recursividad para continuar analizando w
                        return true;//se revisa lo que la funcion regreso mas adelante, si se encontro que pertenecia entonces se sale, si no continua buscando
                    }
                }
            }
        }
        return false;//no se encontro cadena que satisfaga una seccion de w
    }*/
    private void nextState(String actual){
        String key="";
        key+=estadoactual+actual;
        //estadoactual= Integer.parseInt(transicion.get(key));
    }
    private Boolean isFinalState(int estado){
        for(int j=0;j<estadosfinales.length;j++){
            if(estado==Integer.parseInt(estadosfinales[j])){
                return true;
            }

        }
        return false;
    }
    public boolean isWElement(String w){
        return isWElement(w,0);
    }

    private Boolean isWElement(String w, int estado){
        String key=""+estado+w.substring(0,1);
        String keylambda=""+estado+"lamb";
        int numestados;
        if(transicion.containsKey(key)){
            numestados=transicion.get(key).length;
            System.out.println(key+" "+numestados);
            for(int i=0;i<numestados;i++){

                if(w.length()==1&&isFinalState(Integer.parseInt(transicion.get(key)[i]))){
                    return true;
                }else if (w.length()>1){
                    if(isWElement(w.substring(1),Integer.parseInt(transicion.get(key)[i]))){
                        return true;
                    }
                }else if(w.length()==1){
                    if(lastLambda(Integer.parseInt(transicion.get(key)[i]))){
                        return true;
                    }
                }
            }
        }
        if(transicion.containsKey(keylambda)){
            numestados=transicion.get(keylambda).length;
            System.out.println(keylambda+" "+numestados);
            for(int i=0;i<numestados;i++){
                if(isWElement(w, Integer.parseInt(transicion.get(keylambda)[i]))){
                    return true;
                }
            }
        }
        if(!transicion.containsKey(keylambda)&&!transicion.containsKey(key)){
            return false;
        }


        return false;
    }
    public Boolean lastLambda(int estado){
        String key=""+estado+"lamb";
        int numestados;
        if(transicion.containsKey(key)){
            numestados=transicion.get(key).length;
            for(int i=0;i<numestados;i++){
                if(isFinalState(Integer.parseInt(transicion.get(key)[i]))){
                    return true;
                }else{
                    if(lastLambda(Integer.parseInt(transicion.get(key)[i]))){
                        return true;
                    }
                }
            }
        }else{
            return false;
        }
        return false;
    }
}
