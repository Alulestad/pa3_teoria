package com.pa3.dll.recursion;

import java.util.function.Supplier;

final class Suspend <T> implements TailCall<T>{

    //private TailCall<T> next; //Usa el Stack
    private Supplier<TailCall<T>> next;

    //public Suspend(TailCall<T> next){//Usa el Stack
    public Suspend(Supplier<TailCall<T>> next){
        //this.next=next;//Usa el Stack
        this.next=next;
    }


    @Override
    public T eval() {

        TailCall<T> tmp=this;
        while(tmp.isSuspend()){
            //System.out.println("Suspend>eval>while");
            tmp=tmp.resume();

        }

        return tmp.eval();
    }

    @Override
    public TailCall<T> resume() {
        //return next; //usa el stack
        return next.get();
    }

    @Override
    public boolean isSuspend() {
        return true;
    }



}