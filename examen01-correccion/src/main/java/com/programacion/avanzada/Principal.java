package com.programacion.avanzada;

import com.programacion.avanzada.lista.List;

public class Principal {

    public static void main(String[] args) {

        System.out.println("###################### Parte 1 ######################");
        List<Integer> ls=List.of(1,2,3,4,5);
        System.out.println(ls);

        //--pretend
        System.out.println("pretend");
        List<Integer> ls2= ls.prepend(Integer.valueOf(99));
        System.out.println(ls2);

        //--append
        System.out.println("append");
        List<Integer> ls3= ls.append(Integer.valueOf(99));
        System.out.println(ls3);

        //--invertir
        System.out.println("invertir");
        List<Integer> ls4= ls.invertir();
        System.out.println(ls4);

        //--insert
        System.out.println("insert 2");
        List<Integer> ls5= ls.insert(2,99);
        System.out.println(ls5);

        System.out.println("insert 3");
        List<Integer> ls6= ls.insert(3,99);
        System.out.println(ls6);

        //--remove
        System.out.println("remove 2");
        List<Integer> ls7= ls.remove(2);
        System.out.println(ls7);

        System.out.println("remove 3");
        List<Integer> ls8= ls.remove(3);
        System.out.println(ls8);

        //--get
        System.out.println("get 2");
        Integer ls9= ls.get(2);
        System.out.println(ls9);

        System.out.println("get 3");
        Integer ls10= ls.get(3);
        System.out.println(ls10);

        //--forEach
        System.out.println("forEach");
        ls.forEach(s-> System.out.println("elem: "+s));

        System.out.println("###################### Parte 2 ######################");
        //--prepend
        List <Integer> fn1= List.<Integer>fnPrepend()
                .apply(99)
                .apply(ls);
        System.out.println("prepend: "+fn1);

        //--append
        List <Integer> fn2= List.<Integer>fnAppend()
                .apply(ls)
                .apply(99);
        System.out.println("append: "+fn2);

        //--invertir
        List <Integer> fn3= List.<Integer>fnInvertir()
                .apply(ls);
        System.out.println("invertir: "+fn3);

        //--insertar
        List <Integer> fn4= List.<Integer>fnInsertar()
                .apply(ls)
                .apply(2)
                .apply(99);
        System.out.println("insertar 2: "+fn4);

        //--remove
        List <Integer> fn5= List.<Integer>fnRemove()
                .apply(ls)
                .apply(2);
        System.out.println("remove 2: "+fn5);

        //--get
        Integer fn6= List.<Integer>fnGet()
                .apply(ls)
                .apply(2);
        System.out.println("get 2: "+fn6);




    }
}
