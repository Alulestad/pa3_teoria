package com.pa3.dll.examenrecuperacion.arbol;

final class ConsBinTree <T> implements BinTree<T> {
    private final T value;
    private final BinTree<T> left;
    private final BinTree<T> right;

    public ConsBinTree(T value, BinTree<T> left, BinTree<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public ConsBinTree(T value) {
        this.value = value;
        this.left = BinTree.Empty;
        this.right = BinTree.Empty;
    }

    @Override
    public T value() {
        return this.value;
    }

    @Override
    public BinTree<T> left() {
        return this.left;
    }

    @Override
    public BinTree<T> right() {
        return this.right;
    }



    @Override
    public String toString() {
        return String.format("BinTree{%s, %s, %s}",
                value.toString(),
                left.toString(),
                right.toString()
                );
    }

    @Override
    public Integer size() {
        return 1 + left.size() + right.size();
    }

}
