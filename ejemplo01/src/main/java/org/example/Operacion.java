package org.example;

public class Operacion {

    public static int sumar(int x, int y){
        while (y>0){
            x++;
            y--;
        }
        return x;

    }

    public static double dividir(int x){

        if (x==0){
            return -1;
        }else {
            return 1.0/x;
        }


    }


}
