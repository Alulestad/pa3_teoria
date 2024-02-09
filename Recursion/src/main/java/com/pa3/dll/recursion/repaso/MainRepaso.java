package com.pa3.dll.recursion.repaso;

import com.pa3.dll.recursion.lista.ListaTC;


public class MainRepaso {
    public static void main(String[] args) {
        System.out.println("###############   Repaso   ###############");
        System.out.println("###############   Daniel Llumiquinga   ###############");
        System.out.println("###############   Algoritmo tail-recursivo");
        Nodo<String> m=new Nodo<>("m");
        Nodo<String> n=new Nodo<>("n");
        Nodo<String> p=new Nodo<>("p");
        Nodo<String> o=new Nodo<>("o");
        Nodo<String> q=new Nodo<>("q");
        Nodo<String> r=new Nodo<>("r");
        Nodo<String> s=new Nodo<>("s");

        System.out.println(m);
        System.out.println(n);
        System.out.println(p);
        System.out.println(o);
        System.out.println(q);
        System.out.println(r);
        System.out.println(s);

        System.out.println();

        Tuple mn=new Tuple(m,n);
        Tuple mp=new Tuple(m,p);
        Tuple mo=new Tuple(m,o);
        Tuple nq=new Tuple(n,q);
        Tuple pq=new Tuple(p,q);
        Tuple qr=new Tuple(q,r);
        Tuple qs=new Tuple(q,s);

        ListaTC<Tuple> listaTuplas=ListaTC.of(mn,mp,mo,nq,pq,qr,qs);

        ListaTC<Nodo> nodosSucesoresM=ListaTC.nodosSucesores(listaTuplas,m);
        System.out.println("lista de nodos m: " +nodosSucesoresM);

        ListaTC<Nodo> nodosSucesoresN=ListaTC.nodosSucesores(listaTuplas,n);
        System.out.println("lista de nodos n: " +nodosSucesoresN);

        ListaTC<Nodo> nodosSucesoresP=ListaTC.nodosSucesores(listaTuplas,p);
        System.out.println("lista de nodos p: " +nodosSucesoresP);

        ListaTC<Nodo> nodosSucesoresQ=ListaTC.nodosSucesores(listaTuplas,q);
        System.out.println("lista de nodos q: " +nodosSucesoresQ);

        ListaTC<Nodo> nodosSucesoresR=ListaTC.nodosSucesores(listaTuplas,r);
        System.out.println("lista de nodos r: " +nodosSucesoresR);

        ListaTC<Nodo> nodosSucesoresS=ListaTC.nodosSucesores(listaTuplas,s);
        System.out.println("lista de nodos s: " +nodosSucesoresS);

        System.out.println();
        System.out.println("###############   Algoritmo folding");
        System.out.println("lista de nodos m: " +listaTuplas.nodosSucesores(m));
        System.out.println("lista de nodos n: " +listaTuplas.nodosSucesores(n));
        System.out.println("lista de nodos p: " +listaTuplas.nodosSucesores(p));
        System.out.println("lista de nodos q: " +listaTuplas.nodosSucesores(q));
        System.out.println("lista de nodos r: " +listaTuplas.nodosSucesores(r));
        System.out.println("lista de nodos s: " +listaTuplas.nodosSucesores(s));

        System.out.println();
        System.out.println("###############   Monedas");
        Integer dinero=19;
        ListaTC<Integer> monedas=ListaTC.of(5,2,1);
        System.out.println("Descomposicion: "+ListaTC.descomposicionMonedas(monedas,dinero));

        System.out.println();
        System.out.println("###############   eliminados");
        ListaTC<Integer> listaNumeros=ListaTC.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
        System.out.println("eliminados: "+ListaTC.eliminacionN_TC(listaNumeros,(x)->x%2==0,6));





    }
}

