package com.pa3.dll;

import com.pa3.dll.recursion.TailCall;
import com.pa3.dll.recursion.lista.ListaTC;

import static com.pa3.dll.recursion.TailCall.tailReturn;
import static com.pa3.dll.recursion.TailCall.tailSuspend;

public class mainDeber {

    public static void main(String[] args) {

        System.out.println("##############################   DEBER   ##########################");
        System.out.println("##############################   Daniel Llumiquinga   ##########################");
        System.out.println("##############################   TailCall  #######################################");
        ListaTC<Integer> lsTC1= ListaTC.of(1,2,3,4,5);
        ListaTC<Integer> lsTC22= ListaTC.of(6,7,8,9);
        TailCall<ListaTC<Integer>> lsRangeBig=ListaTC.rangeTailCall(1,500_001,ListaTC.Empty);
        ListaTC<Integer> lsTCBig= lsRangeBig.eval();
        System.out.println("CountTC range(500_000): "+ListaTC.countTC(lsTCBig));
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

}

