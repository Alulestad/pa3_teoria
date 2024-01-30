package com.programacion.avanzada.lista;

@FunctionalInterface
public interface Effect<T> {
    void apply(T t);
}
