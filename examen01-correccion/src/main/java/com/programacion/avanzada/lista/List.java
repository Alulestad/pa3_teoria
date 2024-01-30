package com.programacion.avanzada.lista;

import java.util.function.Consumer;
import java.util.function.Function;

public class List <T>{
    private final Nodo<T> start;
    List(Nodo<T> start){
        this.start=start;

    }

    //parametros variables
    public static <T> List<T> of(T... elems){
        Nodo<T> tmp=null;

        for (int i=elems.length-1;i>=0;i--){
            tmp=new Nodo<>(elems[i],tmp);
        }

        return new List<> (tmp);
    }

    //String Builder
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("[");

        var tmp=this.start;
        while (tmp!=null){
            sb.append(tmp.elem())
                    .append(", ");
            tmp=tmp.next();

        }

        sb.append("]");
        return sb.toString();
    }

    //----------------------------------------------prepend
    public List<T> prepend(T elem){
        Nodo<T> tmp= new Nodo<>(elem, this.start);

        return new List<>(tmp);
    }

    //----------------------------------------------append
    public List<T> append(T elem){
        Nodo<T> nodo= null;
        Nodo<T> tmp=this.start;
        while (tmp!=null){
            nodo = new Nodo<T>(tmp.elem(),nodo) ;
            tmp=tmp.next();
        }
        nodo=new Nodo<T>(elem,nodo);
        return new List<T> (nodo).invertir();
    }

    //----------------------------------------------invertir
    public List<T> invertir(){
        Nodo<T> nodo= null;
        Nodo<T> tmp=this.start;
        while (tmp!=null){
            nodo = new Nodo<T>(tmp.elem(),nodo) ;
            tmp=tmp.next();
        }
        return new List<T> (nodo);
    }

    //----------------------------------------------insert
    public List<T> insert(int index, T elem){
        Nodo<T> nodo= null;
        Nodo<T> tmp=this.start;
        int i = 0;
        while (tmp!=null && i<index){
            nodo = new Nodo<>(tmp.elem(),nodo) ;
            tmp=tmp.next();

            i++;
        }
        nodo=new Nodo<T>(elem,nodo);

        while (tmp!=null){
            nodo = new Nodo<>(tmp.elem(),nodo) ;
            tmp=tmp.next();
        }

        return new List<T> (nodo).invertir();
    }

    //----------------------------------------------remove
    public List<T> remove(int index){
        Nodo<T> nodo= null;
        Nodo<T> tmp=this.start;
        int i = 0;
        while (tmp!=null && i<=index){
            nodo = new Nodo<>(tmp.elem(),nodo) ;
            tmp=tmp.next();

            i++;
        }
        nodo=nodo.next();

        while (tmp!=null){
            nodo = new Nodo<>(tmp.elem(),nodo) ;
            tmp=tmp.next();
        }

        return new List<T> (nodo).invertir();
    }

    //----------------------------------------------get
    public T get(int index){
        Nodo<T> nodo= null;
        Nodo<T> tmp=this.start;
        int i = 0;
        while (tmp!=null && i<index){
            nodo = new Nodo<>(tmp.elem(),nodo) ;
            tmp=tmp.next();
            i++;
        }


        return tmp.elem();
    }

    //----------------------------------------------forEach
    public void forEach(Consumer<T> ac){
        Nodo<T> tmp=this.start;
        while (tmp!=null){
            ac.accept(tmp.elem());
            tmp=tmp.next();
        }
    }

    //################################ Parte 2 ################################"
    //prepend: T x List<T> ->  List<T>
    //T -> List <T> -> List<T>
    public static <T> Function<T, Function<List<T>, List<T>>> fnPrepend(){
        return elem -> ls -> ls.prepend(elem);
    }

    //append: List<T> x T ->  List<T> ==> List<T> -> T ->  List<T>
    public static <T> Function<List<T>, Function<T, List<T>>> fnAppend(){
        return ls -> elem -> ls.append(elem);
    }

    //invertir: List<T> ->  List<T>
    public static <T> Function<List<T>, List<T>> fnInvertir(){
        return ls -> ls.invertir();
    }

    //insert: List<T> x Integer x T ->  List<T> ==> List<T> -> Integer -> T ->  List<T>
    public static <T> Function<List<T>, Function<Integer, Function <T,List<T>>>> fnInsertar(){
        return ls -> position-> elem -> ls.insert(position,elem);
    }

    //remove: List<T> x Integer ->  List<T>  ==> List<T> -> Integer ->  List<T>
    public static <T> Function<List<T>, Function<Integer, List<T>>> fnRemove(){
        return ls -> position -> ls.remove(position);
    }

    //get: List<T> x Integer ->  T ==> List<T> -> Integer ->  List<T>
    public static <T> Function<List<T>, Function<Integer, T>> fnGet(){
        return ls -> position-> ls.get(position);
    }


}
