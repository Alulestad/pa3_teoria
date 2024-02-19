package com.pa3.dll.examenrecuperacion.arbol;

final class EmptyBinTree<T> implements BinTree<T> {

    @Override
    public T value() {
        return null;
    }

    @Override
    public BinTree<T> left() {
        return null;
    }

    @Override
    public BinTree<T> right() {
        return null;
    }

    @Override
    public String toString() {
        return "EmptyBinTree{}";
    }

    @Override
    public Integer size() {
        return 0;
    }




}
