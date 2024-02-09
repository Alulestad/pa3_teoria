package com.pa3.dll.recursion.repaso;

import java.util.Objects;

public final class Nodo <T> {

    private final T elemt;

    private Nodo() {
        this.elemt = null;
    }
    public Nodo(T elemt) {
        this.elemt = elemt;
    }

    public T getElemt() {
        return elemt;
    }

    @Override
    public String toString() {
        return "Nodo{" +
                "elemt=" + elemt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nodo<?> nodo = (Nodo<?>) o;
        return Objects.equals(elemt, nodo.elemt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elemt);
    }
}
