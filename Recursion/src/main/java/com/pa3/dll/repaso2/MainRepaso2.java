package com.pa3.dll.repaso2;

import com.pa3.dll.recursion.lista.ListaTC;


public class MainRepaso2 {

    public static void main(String[] args) {
        System.out.println("Repaso 2");
        //ListaTC<Integer> ls = ListaTC.of(1, 3, 5, 2, 6);
        ListaTC<Integer> ls2 = ListaTC.of(5, 1, 4);
        //ListaTC listaDeListas = split(ls);
        ListaTC listaDeListas2 = split(ls2);
        //System.out.println("Lista1: " + ls);
        System.out.println("Lista2: " + ls2);
        System.out.println();
        //System.out.println("merge1: " + listaDeListas);
        System.out.println("merge2: " + listaDeListas2);



    }


    static ListaTC<Integer> splitAux(ListaTC<Integer> ls, ListaTC<Integer> lsIzq, ListaTC<Integer> lsDer, int contador) {

        if (!ls.isEmpty()) {
            if (ls.tail().isEmpty() && lsIzq.isEmpty() && lsDer.isEmpty()){
                return ls;
            }
            if (contador % 2 != 0) {
                ListaTC<Integer> lsImp= splitAux(ls.tail(), lsIzq, lsDer.append(ls.head()), contador + 1);
                return lsImp;
            } else {
                ListaTC<Integer> lsPar= splitAux(ls.tail(), lsIzq.append(ls.head()), lsDer, contador + 1);
                return lsPar;
            }

        }else {
            var left= splitAux(lsIzq, ListaTC.Empty, ListaTC.Empty, 0);
            var rigth= splitAux(lsDer, ListaTC.Empty, ListaTC.Empty, 0);
            System.out.println("--L: "+left+" --R: "+rigth);
            return merge(left,rigth);
        }
    }



    static ListaTC<Integer> split(ListaTC<Integer> ls) {
        return splitAux(ls, ListaTC.Empty, ListaTC.Empty, 0);
    }

    private static ListaTC<Integer> mergeAux(ListaTC<Integer> a, ListaTC<Integer> b, ListaTC<Integer> result) {
        if (a.isEmpty())
            return result.concat(b);
        if (b.isEmpty())
            return result.concat(a);

        if (a.head() <= b.head()) {
            result = result.concat(ListaTC.of(a.head()));
            return mergeAux(a.tail(), b, result);
        } else {
            result = result.concat(ListaTC.of(b.head()));
            return mergeAux(a, b.tail(), result);
        }
    }

    public static ListaTC<Integer> merge(ListaTC<Integer> left, ListaTC<Integer> rigth) {
        return mergeAux(left,rigth,ListaTC.Empty);
    }

    //static Tupla split();
    //la lsita original le diveiden ordenan las liastas unicas y mexclan,
    //cuando tengo un elemnto la lista ya esta ordenada.
    //si la lista esta vacia o esta ordenada, retorno el mismo elemento.
    ///////



}
