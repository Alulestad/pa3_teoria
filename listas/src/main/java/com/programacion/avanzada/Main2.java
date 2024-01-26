package com.programacion.avanzada;

import com.programacion.avanzada.lista.Lista;

public class Main2 {

    public static void main(String[] args) {

        System.out.println("Main2");

        var ls=Lista.of(1,2,3,4,5);
        System.out.println(ls);

        var ls2=Lista.of("aa","bb","cc");
        System.out.println(ls2);



        System.out.println("prepend: "+ls.prepend(99));

        System.out.println("append: "+ls.append(99));
        System.out.println("append2: "+ls.append(99));

        System.out.println("insert: "+ls.insert(3,99));
        System.out.println("insert2: "+ls.insertX(3,99));

        System.out.println("get 2: "+ls.get(2));
        //System.out.println("get 20: "+ls.get(20));
        System.out.println("getX 2: "+ls.get(2));

        System.out.println("count2: "+ls2.count2());
        System.out.println("count1: "+ls2.count());


        System.out.println("take 2: "+ls.take(2));
        System.out.println("take 20: "+ls.take(20));
        System.out.println("take 2: "+Lista.Empty.take(2));

        System.out.println("take 2: "+ls.takeX(2));

        System.out.println("drop 2: "+ls.drop(2));
        System.out.println("drop 20: "+ls.drop(20));
        System.out.println("drop 20: "+Lista.Empty.drop(20));
        System.out.println("dropX 2: "+ls.dropX(2));

        System.out.println("concatX : "+ls.concatX(Lista.of(6,7,8)));

        System.out.println("map1: "+ls.map(x->1.2*x));

        System.out.println("foldLeft suma: "+ls.foldLeft(0, x->y->x+y));

        System.out.println("foldLeft max: "+ls.foldLeft(ls.head(), x->y->Math.max(x,y)));

        System.out.println("foldLeft    : "+ls.foldLeft("", s->x->s+String.valueOf(x)));

        System.out.println("foldRigt    : "+ls.foldRight("", s-> x->s+String.valueOf(x)));

        System.out.println();

        System.out.println("invertir-fold: "+ls.invertFold());

        System.out.println("map-foldLeft: "+ls.mapFoldLeft(t->"x"+t));

        System.out.println("map-foldRight: "+ls.mapFoldRight(t->"x"+t));

        System.out.println("count-foldLeft: "+ls.countFoldLeft());

        System.out.println("append-foldRight: "+ls.appendFoldRight(99));

        System.out.println("reduce-foldLeft: "+ls.reduceFoldLeft(0,x->y->x+y));

        System.out.println("reduce-foldLeftSimplificado: "+ls.reduceFoldLeftSimplificado(x->y->x+y));

        System.out.println("take-foldLeft: "+ls.takeFoldLeft(2));

        System.out.println("drop-foldRight: "+ls.dropFoldRight(2));


        var ls22= Lista.of("aa","bb","cc");
        

    }


}



