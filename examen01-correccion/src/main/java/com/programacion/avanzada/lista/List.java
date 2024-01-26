package com.programacion.avanzada.lista;

public class List <T>{
    private final Nodo<T> start;
    List(Nodo<T> start){
        this.start=start;

    }


    public static <T> List<T> of(T... elems){

        Nodo<T> tmp=null;

        for (int i=elems.length;i>=0;i--){
            tmp=new Nodo<T>(elems[i],tmp);
        }

        return new List<T> (tmp);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        var tmp=this.start;
        while (tmp!=null){
            sb.append(tmp.elem())
                    .append(", ");
            tmp=tmp.next();

        }
        return tmp.toString();
    }

    //----------------------------------------------
    public List<T> prepend(T elem){
        Nodo<T> tmp= new Nodo<>(elem, this.start);

        return new List<>(tmp);
    }

    //----------------append
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

    //----- invertir
    public List<T> invertir(){
        Nodo<T> nodo= null;
        Nodo<T> tmp=this.start;
        while (tmp!=null){
            nodo = new Nodo<T>(tmp.elem(),nodo) ;
            tmp=tmp.next();
        }
        //nodo=new Nodo<T>(elem,nodo);
        return new List<T> (nodo);
    }

    public List<T> insert(int index, T elem){
        Nodo<T> nodo= null;
        Nodo<T> tmp=this.start;
        int i=0;
        while (tmp!=null && i<=index){
            nodo = new Nodo<T>(tmp.elem(),nodo) ;
            tmp=tmp.next();

            i++;
        }
        nodo=new Nodo<T>(elem,nodo);

        while (tmp!=null){
            nodo = new Nodo<T>(tmp.elem(),nodo) ;
            tmp=tmp.next();

        }

        return new List<T> (nodo);
    }

    ///--

}
