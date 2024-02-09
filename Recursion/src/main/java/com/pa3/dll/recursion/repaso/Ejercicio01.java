package com.pa3.dll.recursion.repaso;

import com.pa3.dll.recursion.TailCall;
import com.pa3.dll.recursion.lista.ListaTC;

import java.util.function.Function;

record Tupla(String t1, String t2){


}
public class Ejercicio01 {

    //--p1.1
    static ListaTC<String> sucesorAux(ListaTC<String> ret,ListaTC<Tupla> graph,String nodo){
        if(graph.isEmpty()){
           return ret;
        }else{
            if(graph.head().t1().equals(nodo)){
                var tmp= ret.prepend(graph.head().t2());
                return sucesorAux(tmp,graph.tail(),nodo);
            }else{
                return sucesorAux(ret,graph.tail(),nodo);
            }
        }

    }

    static TailCall<ListaTC<String>> sucesorAuxTC(ListaTC<String> ret,ListaTC<Tupla> graph,String nodo){
        if(graph.isEmpty()){
            return TailCall.tailReturn(ret);
        }else{
            if(graph.head().t1().equals(nodo)){
                var tmp= ret.prepend(graph.head().t2());
                return TailCall.tailSuspend(()->sucesorAuxTC(tmp,graph.tail(),nodo));
            }else{
                return TailCall.tailSuspend(()->sucesorAuxTC(ret,graph.tail(),nodo));
            }
        }

    }
    static ListaTC<String> sucesor(ListaTC<Tupla> graph,String nodo){
        return sucesorAux(ListaTC.Empty,graph,nodo).invertFold();
    }

    static ListaTC<String> sucesorTC(ListaTC<Tupla> graph,String nodo){
        TailCall<ListaTC<String>> ret=  sucesorAuxTC(ListaTC.Empty,graph,nodo);
        return ret.eval().invertFold();
    }

    static ListaTC<String> sucesorFold(ListaTC<Tupla> graph,String nodo){
        ListaTC<String> vi= ListaTC.Empty; //tipo U
        Function<ListaTC<String>,Function<Tupla,ListaTC<String>>> fn= ls->tup->{
            if (tup.t1().equals(nodo)){
                return ls.prepend(tup.t2());
            }else{
                return ls;
            }
        };
        return graph.foldLeft( vi,fn).invertFold();
    }
    //p2

    static ListaTC<Integer>  cambiarMonedasAux(ListaTC<Integer> ret,ListaTC<Integer> denom, Integer valor) {
        if (valor==0){
            return ret;
        }else{
            if(valor<denom.head()){
                return cambiarMonedasAux(ret,denom.tail(),valor);
            }else{

                return cambiarMonedasAux(ret.prepend(denom.head()),denom,valor-denom.head());
            }
        }

    }

    static TailCall<ListaTC<Integer>>  cambiarMonedasAuxTC(ListaTC<Integer> ret,ListaTC<Integer> denom, Integer valor) {
        if (valor==0){
            return TailCall.tailReturn(ret);
        }else{
            if(valor<denom.head()){
                return TailCall.tailSuspend(()-> cambiarMonedasAuxTC(ret,denom.tail(),valor));
            }else{

                return TailCall.tailSuspend(()-> cambiarMonedasAuxTC(ret.prepend(denom.head()),denom,valor-denom.head()));
            }
        }

    }
    static ListaTC<Integer>  cambiarMonedas(ListaTC<Integer> denom, Integer valor) {
        return cambiarMonedasAux(ListaTC.Empty,denom,valor).invertFold();
    }

    static ListaTC<Integer>  cambiarMonedasTC(ListaTC<Integer> denom, Integer valor) {
        return ((ListaTC<Integer>)cambiarMonedasAuxTC(ListaTC.Empty,denom,valor).eval()).invertFold();
    }


    //--p1.1
    public static void main(String[] args) {
        var ls = ListaTC.of(
                new Tupla("m","n"),
                new Tupla("m","p"),
                new Tupla("m","o"),
                new Tupla("n","q"),
                new Tupla("p","q"),
                new Tupla("o","r"),
                new Tupla("q","r"),
                new Tupla("q","s")
        );

        var sucM =sucesor(ls,"m");
        System.out.println(sucM);
        System.out.println(sucesorTC(ls,"m"));

        System.out.println();
        System.out.println(sucesorFold(ls,"m"));

        var monedas=ListaTC.of(5,2,1);
        System.out.println("cabiar moendas" +cambiarMonedas(monedas,19));
        System.out.println("cabiar moendas" +cambiarMonedasTC(monedas,19));


    }


}
