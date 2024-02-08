package com.pa3.dll.recursion;

import java.util.function.Supplier;

public interface TailCall<T>{
    T eval();
    TailCall<T> resume();
    boolean isSuspend();

    static <T> TailCall<T> tailReturn(T value){
        return new Return<>(value);
    }

    //static <T> TailCall<T> tailSuspend(TailCall<T> next){ //usa el stack
    static <T> TailCall<T> tailSuspend(Supplier<TailCall<T>> next){
        return new Suspend <>(next);
    }

    /*
    static <T> TailCall<T> tailSuspend(Supplier<TailCall<T>> next){
        return new Suspend <>(next);
    }

     */
}