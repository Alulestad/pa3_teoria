package com.pa3.dll.examenrecuperacion;

import com.pa3.dll.examenrecuperacion.arbol.BinTree;
import com.pa3.dll.recursion.lista.ListaTC;

public class MainExRecuperacion {
    public static void main(String[] args) {
        System.out.println("##############################         DEBER          ##########################");
        System.out.println("##############################   Daniel Llumiquinga   ##########################");
        //1 y 2.-
        ListaTC<Integer> lsNumero= ListaTC.of(1,2,3,4,5,6,7,8);
        var ls1 = ListaTC.takeTC(lsNumero,4);
        var ls2 = ListaTC.dropTC(lsNumero,4);

        System.out.println("ls1 (take): "+ls1);
        System.out.println("ls2 (drop): "+ls2);

        //3 y 5.-
        var arbol=BinTree.buildTree(lsNumero);
        System.out.println("buildtree: "+BinTree.buildTree(lsNumero));

        //4.-
        System.out.println("size: "+arbol.size());



        var n2=BinTree.of(2);
        var n9=BinTree.of(9);
        var n5=BinTree.of(5,n9,BinTree.Empty);
        var n1=BinTree.of(1,n2,n5);


        var tree=n1;
        System.out.println(tree);

        var lpre=preorder(tree);
        System.out.println(lpre);

        var lin=inorder(tree);
        System.out.println(lin);

        var lpos=posorder(tree);
        System.out.println(lpos);


    }


    public static <T> ListaTC<T> preorder(BinTree<T> tre) {
        if (tre==BinTree.Empty) {
            return ListaTC.Empty;
        } else {
            var l = preorder(tre.left());
            var r = preorder(tre.right());
            var tmp=l.concat(r);

            return tmp.prepend(tre.value());


        }
    }

    public static <T> ListaTC<T> inorder(BinTree<T> tre) {
        if (tre==BinTree.Empty) {
            return ListaTC.Empty;
        } else {
            var l = inorder(tre.left());
            var r = inorder(tre.right());

            var tmp=r.prepend(tre.value());

            return l.concat(tmp);


        }
    }

    public static <T> ListaTC<T> posorder(BinTree<T> tre) {
        if (tre==BinTree.Empty) {
            return ListaTC.Empty;
        } else {
            var l = posorder(tre.left());
            var r = posorder(tre.right());

            var tmp=r.append(tre.value());

            return l.concat(tmp);


        }
    }

}
