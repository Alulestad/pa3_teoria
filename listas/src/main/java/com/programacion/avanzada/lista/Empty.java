package com.programacion.avanzada.lista;

import java.util.List;

class Empty implements Lista{


    @Override
    public Object head() {
        return null;
    }

    @Override
    public Lista tail() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public String toString() {
        return "Empty{}";
    }

    @Override
    public int count() {
        return 0;
    }
}
