package com.programacion.avanzada.lista;

public record Nodo <T> (T elem, Nodo<T> next) {

}

final class Nodo2<T>{
    private final T elem;
    private final Nodo2<T> next;

    public Nodo2(T elem, Nodo2<T> next) {
        this.elem = elem;
        this.next = next;
    }

    public T elem() {
        return elem;
    }

    public Nodo2<T> next() {
        return next;
    }
}
