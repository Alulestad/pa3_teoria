package org.example;

import java.util.Optional;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {

        System.out.println("asdfsadfdsf");







    }




/////////////////////////////////////////////////////////////////////////
    public static void ejerciciosIniciales(){
        System.out.println("Ejemplo 1++++++++++++++++");


        int suma=Operacion.sumar(3,4);

        System.out.println("Suma: "+ suma);

        double divi=Operacion.dividir(3);

        System.out.println("Divicion: "+ divi);


        //Divicion forma 1
        Function<Integer, Integer> f = x-> {
            return 1/x;
        };
        int ret=f.apply(1);
        System.out.println("forma 1: "+ret);

        //Divicion forma 2
        Function<Integer, Integer> f2 = x-> {
            if (x==0){
                return 0;
            }else {
                return 1/x;
            }
        };
        int ret2=f2.apply(0);
        System.out.println("forma 2: "+ret2);

        //Divicion forma 3
        Function<Integer, Optional<Integer>> f3 = x-> {
            if (x==0){
                return Optional.empty();
            }else {
                return Optional.of(1/x);
            }
        };

        Optional<Integer> ret3=f3.apply(1);

        if(ret3.isPresent())
            //System.out.printf("\n forma 3: %.2f",ret3.get());
            System.out.println("forma 3:"+ret3.get());
        else
            System.out.println("No existe la divicion ");





    }
}


