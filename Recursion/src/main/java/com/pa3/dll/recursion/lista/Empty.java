package com.pa3.dll.recursion.lista;

class Empty implements ListaTC {


    @Override
    public Object head() {
        return null;
    }

    @Override
    public ListaTC tail() {
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
