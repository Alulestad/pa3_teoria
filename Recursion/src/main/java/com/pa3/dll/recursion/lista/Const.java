package com.pa3.dll.recursion.lista;

public record Const<T> (T head, ListaTC<T> tail) implements ListaTC<T> {


    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("[%s,%s]",head,tail.toString());
    }

    @Override
    public boolean isEmpty() {
        return false;
    }


}
