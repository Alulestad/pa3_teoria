package com.programacion.avanzada.lista;

public record Const<T> (T head,Lista<T> tail) implements Lista<T> {


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
