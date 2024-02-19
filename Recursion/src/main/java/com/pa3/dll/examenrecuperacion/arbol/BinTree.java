package com.pa3.dll.examenrecuperacion.arbol;

import com.pa3.dll.recursion.lista.ListaTC;

import java.util.function.Consumer;

public interface BinTree<T> {

    BinTree Empty = new EmptyBinTree();

    T value();
    BinTree<T> left();
    BinTree<T> right();

    static <T> BinTree<T> of(T value, BinTree<T> left, BinTree<T> right) {
        return new ConsBinTree<>(value,left,right);
    }

    static <T> BinTree<T> of(T value) {
        return new ConsBinTree<>(value);
    }

    Integer size();


    static <T> BinTree<T> buildTree(ListaTC<T> ls) {
        if( ls.isEmpty() ) {
            return BinTree.Empty;
        }
        else {
            var head_valor = ls.head();
            var cola = ls.tail();

            int k = cola.count() / 2;

            var leftList = cola.take(k);
            var rightList = cola.drop(k);

            return BinTree.of(head_valor, buildTree(leftList), buildTree(rightList));
        }
    }



}
