package com.pa3.dll.recursion.repaso;

public final class Tuple {

    private final Nodo arg1;
    private final Nodo arg2;


    public Tuple(Nodo arg1, Nodo arg2) {
        super();
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public Nodo arg1() {
        return this.arg1;
    }

    public Nodo arg2() {
        return this.arg2;

    }

    public Boolean contains(Nodo nodo){
        if(nodo.equals(arg1)){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){

        return String.format("(%s, %s)", this.arg1, this.arg2);
    }



}
