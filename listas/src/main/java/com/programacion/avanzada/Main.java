package com.programacion.avanzada;

import com.programacion.avanzada.lista.Const;
import com.programacion.avanzada.lista.Lista;

public class Main {

    public static void main(String[] args) {

        System.out.println("Main");

        var n6 =Lista.Empty;
        var n5=new Const<>(5,n6);
        var n4=new Const<>(4,n5);
        var n3=new Const<>(3,n4);
        var n2=new Const<>(2,n3);
        Lista<?> n1=new Const<>(1,n2);

        System.out.println("asdf: "+n1);
        System.out.println("asdf: "+n1.isEmpty());
        System.out.println("asdf: "+Lista.Empty.isEmpty());
        System.out.println("asdf: "+n5.tail().isEmpty());

        var tmp = n1;
        while (!tmp.isEmpty()){
            System.out.println(tmp.head());
            tmp= tmp.tail();
        }

        //-------
        Lista<Integer> ls2=new Const<>(1,
                new Const<>(2,
                        new Const<>(3,
                                new Const<>(4,
                                        new Const<>(5,Lista.Empty)))));
        System.out.println("ls2: "+ls2);

        //-------
        var ls3= Lista.of(1,2,3,4,5);

    }


}



