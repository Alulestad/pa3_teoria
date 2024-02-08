package com.pa3.dll;

import com.pa3.dll.recursion.TailCall;
import com.pa3.dll.recursion.lista.ListaTC;

import static com.pa3.dll.recursion.TailCall.*;

public class TestRecursion {
    static Integer sumOriginal(Integer x, Integer y) {
        return y == 0
                ? x
                : sumOriginal(x + 1, y - 1);
    }

    /*
    static TailCall<Integer> sumRec(Integer x, Integer y) {
        return y == 0
                ? tailReturn(x)
                : tailSuspend(
                        sumRec(x + 1, y - 1));
    }
*/ // usa el stack

    static TailCall<Integer> sumRec(Integer x, Integer y) {
        return y == 0
                ? tailReturn(x)
                : tailSuspend(
                    () -> {
                        //System.out.println("x: " + x + ", y: " + y);
                        return sumRec(x + 1, y - 1);
                    }
                );

    }


    public static void main(String[] args) {

/*
        var ret = tailReturn(10);
        var n1 = tailSuspend(ret);
        var n2 = tailSuspend(n1);
        var n3 = tailSuspend(n2);

        //[n3,n2,n1,ret]
        var res = n3.eval();
        System.out.println(res);


        var n3_b =
                tailSuspend(
                        tailSuspend(
                                tailSuspend(
                                        tailReturn(10)
                                )
                        )
                );
        System.out.println(n3_b.eval());


        var ret_c = sumRec(3, 5); //con 5=500_000 no funciona sigue en el stack
        var suma_c = ret_c.eval();
        System.out.println(suma_c);

*/ //usa el stack
        System.out.println("#################################################################### Notas de clase");
        var n3_d =
                tailSuspend( ()->
                        tailSuspend( ()->
                                tailSuspend( ()->
                                        tailReturn(10)
                                )
                        )
                );
        System.out.println(n3_d.eval());

        var ret_d = sumRec(3, 500_000);
        var suma_d = ret_d.eval();
        System.out.println(suma_d);


        System.out.println("##################################################################### Tail Recursivo");
        ListaTC<Integer> lsTC= ListaTC.of(1,2,3,4,5);
        ListaTC<Integer> lsTC2= ListaTC.of(6,7,8,9);
        System.out.println("CountTR: "+ListaTC.countTR(lsTC));
        ListaTC<Integer> tmp= ListaTC.preppendTR(99,lsTC);
        System.out.println("prependTR: "+tmp);
        System.out.println("invertirTR: " + ListaTC.invertirTR(lsTC));
        System.out.println("appendTR: " + ListaTC.appendTR(lsTC,99));
        System.out.println("insertTR: " + ListaTC.insertTR(lsTC,99,2));
        System.out.println("getTR: " + lsTC.get(2));
        System.out.println("takeTR: " + ListaTC.takeTR(lsTC,2));
        System.out.println("dropTR: " + ListaTC.dropTR(lsTC,2));
        System.out.println("concatTR: " + ListaTC.concatTR(lsTC,lsTC2));
        System.out.println("mapTR: " + ListaTC.mapTR(lsTC,(x)->x*2));

        System.out.println("##############################   DEBER   ##########################");
        System.out.println("##################################################################### TailCall");
        ListaTC<Integer> lsTC1= ListaTC.of(1,2,3,4,5);
        ListaTC<Integer> lsTC22= ListaTC.of(6,7,8,9);
        TailCall<ListaTC<Integer>> lsRangeBig=ListaTC.rangeTailCall(1,101,ListaTC.Empty);
        ListaTC<Integer> lsTCBig= lsRangeBig.eval();
        System.out.println("CountTC: "+ListaTC.countTC(lsTCBig));
        System.out.println(lsTC1);
        System.out.println("preppendTC: "+ListaTC.preppendTC(99,lsTC1));
        System.out.println("invertirTC: " + ListaTC.invertirTC(lsTC1));
        System.out.println("appendTC: " + ListaTC.appendTC(99,lsTC1));
        System.out.println("insertTC: " + ListaTC.insertTC(lsTC1,99,2));
        System.out.println("getTC: " + lsTC1.getTC(2));
        System.out.println("takeTC: " + ListaTC.takeTC(lsTC1,2));
        System.out.println("dropTC: " + ListaTC.dropTC(lsTC1,2));
        System.out.println("concatTC: " + ListaTC.concatTC(lsTC1,lsTC22));
        System.out.println("mapTC: " + ListaTC.mapTC(lsTC1,(x)-> x*2));


    }
    //sirve para la generacion de los rangos,
    // dejamos el sigueinte pendiente
    //uso el suspend y genero rangos de mayor
    // envergadura.

    //tecnicamente usamos una sola llamada recursiva.
    //Esto de llama la evaluacion tardia.


    //DEBER: re implementar la clase lista
    //pero usando esta forma de recurscion.
    //


    //estos deven ser Tail recursivos y luego se hace
    //esta extrapolacion
}

