package com.programacion.avanzada;

import com.programacion.avanzada.lista.List;

public class Principal {

    public static void main(String[] args) {
        List<Integer> ls=List.of(1,2,3,4,5);
        //System.out.println(ls);

        //--pretend
        //List<Integer> ls2= ls.prepend(Integer.valueOf(99));
        //System.out.println(ls2);

        //
        //List<Integer> ls3= ls.append(Integer.valueOf(99));
        //System.out.println(ls3);
    }
}
