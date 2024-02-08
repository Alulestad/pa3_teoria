package com.programacion.avanzada;

import com.programacion.avanzada.lista.Lista;

public class MainDeber {

    public static void main(String[] args) {


        var ls=Lista.of(1,2,3,4,5);
        System.out.println("*************************************************************************");
        System.out.println("**********************************DEBER**********************************");
        System.out.println("***************************DANIEL LLUMIQUINGA****************************");

        System.out.println("rangeTailRec: "+Lista.rangeTailRec(1, 5,Lista.Empty));
        System.out.println("unfoldImperativo: "+Lista.unfoldImperativo(1, x->x+1, x->x.compareTo(5)<0));
        System.out.println("unfoldRecursivo: "+Lista.unfoldRecursivo(1, x->x+1, x->x.compareTo(5)<0));
        System.out.println("unfoldTailRecursivo: "+Lista.unfoldTailRecursivo(1, x->x+1, x->x.compareTo(5)<0,Lista.Empty));
        System.out.println("rangeUnfold: "+Lista.rangeUnfold(1, 5));



    }


    public static void impresiones(){
        /*
        Effect<Integer> printInteger= x->{
            System.out.println(x);
        };

        Executable neutro = ()-> {};

        //Executable version1
        Function<Executable, Function<Integer,Executable>> fn=
                ex->elem->()->{
                    ex.exec();
                    printInteger.apply(elem);
                };

        //Executable version2
        Function<Executable, Function<Integer,Executable>> fn1=
                ex->elem->{
                    ex.exec();
                    return () -> printInteger.apply(elem);
                };

        System.out.println("Executable version1");
        var ex11=fn.apply(neutro).apply(1);
        var ex22=fn.apply(ex11).apply(2);
        var ex33=fn.apply(ex22).apply(3);
        var ex44=fn.apply(ex33).apply(4);
        ex44.exec();

        System.out.println("Executable version2");
        var ex1=fn1.apply(neutro).apply(1);
        var ex2=fn1.apply(ex1).apply(2);
        var ex3=fn1.apply(ex2).apply(3);
        var ex4=fn1.apply(ex3).apply(4);
        fn1.apply(ex4).apply(5);
        */
    }


}



