package org.example;

import java.util.function.Function;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static int sumar(int x,int y){
        return x+y;
    }
    public static void main(String[] args) {
        System.out.println("Curring 1");
        /**
         * f(x,y)=x+y
         * h(x)=f(x,y)=g(y)
         * h(2)=f(2,y)=2+y=g(y)
         */
         //Integer->Integer->Integer
        Function<Integer, Function<Integer,Integer>> sumarFn= new Function<Integer, Function<Integer, Integer>>() {
            @Override
            public Function<Integer, Integer> apply(Integer x) {
                return new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer y) {
                        return x+y;
                    }
                };
            }
        };
        Function<Integer, Function<Integer,Integer>> sumarFnLB= x->y->x+y;

        var r1=sumar(2,3);
        System.out.printf("sumar = %d \n",r1);

        //h(x)(y)
        var r2=sumarFnLB.apply(2).apply(3);
        System.out.printf("sumarFnLB = %d \n",r2);

        //g(y)=f(2,y)=2+y
        Function<Integer,Integer> fn2=sumarFnLB.apply(2);
        System.out.println(fn2);
        System.out.println(fn2.apply(3));

    }
}