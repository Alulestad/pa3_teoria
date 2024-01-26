package com.programacion.avanzada.lista;

import java.util.List;
import java.util.function.Function;

/**
 * {1,2,3,4}
 *
 * [1,[2,[3,[4,[5,Empty]]]]]
 * @param <T>
 */
public interface Lista <T> {

    Lista Empty=new Empty();



    T head();
    Lista<T> tail();

    boolean isEmpty();

    static <T> Lista<T> of(T head, Lista<T> tail){
        return new Const<>(head,tail);
    }

    static <T> Lista<T> of(T... elems){
        var tmp=Lista.Empty;
        for(int i=elems.length-1;i>=0;i--){
            tmp= new Const(elems[i],tmp);

        }

        return tmp;
    }

    default int count2(){

        var tmp = this;
        int cc=0;
        while (!tmp.isEmpty()){
            cc++;
            tmp= tmp.tail();
        }

        return cc;
    }

    default int count(){

        return 1 + tail().count();
    }


    default Lista<T> prepend(T elemn){
        return new Const<>(elemn, this);
    }

    default Lista<T> prependOf(T elemn){
        return Lista.of(elemn, this);
    }

    default Lista<T> append(T elemn){

        if(this.isEmpty()){
            return new Const<>(elemn,Lista.Empty);

        }else {
            return new Const<>(
                this.head(),
                this.tail().append(elemn)
            );
        }


    }

    default Lista<T> append2(T elemn){

        return this.isEmpty()
                ?new Const<>(elemn,Lista.Empty)
                :new Const<>(
                    this.head(),
                    this.tail().append(elemn)
            );



    }

    default Lista<T> appendOf(T elemn){

        return this.isEmpty()
                ?Lista.of(elemn,Lista.Empty)
                :Lista.of(
                this.head(),
                this.tail().append(elemn)
        );



    }

    default Lista<T> insert(int index, T elem){
        if(index==0){
            return new Const<>(elem,this);

        }else{
            return new Const<>(
                    this.head(),
                    this.tail().insert(index-1,elem)
            );
        }
    }

    default Lista<T> insertX(int index, T elem){
        return index==0
                ? new Const<>(elem,this)
                :new Const<>(
                this.head(),
                this.tail().insert(index-1,elem)
        );
    }

    default Lista<T> insertOf(int index, T elem){
        return index==0
                ?Lista.of(elem,this)
                :Lista.of(
                this.head(),
                this.tail().insert(index-1,elem)
        );
    }

    default T get(int index){

        if(index==0 ){
            return this.head();

        }else{
            return this.tail().get(index-1);
        }
    }

    default T getX(int index){
       return index==0
               ? this.head()
               :this.tail().get(index-1);
    }

    default Lista<T> take(int n){
        if(n<=0 || this.isEmpty()){
            return Lista.Empty;
        }else{
            return Lista.of(this.head(),this.tail().take(n-1));
        }

    }

    default Lista<T> takeX(int n){
        return (n<=0 || this.isEmpty())
        ?Lista.Empty
        : Lista.of(this.head(),this.tail().take(n-1));

    }


    default Lista<T> drop(int n) {

        if (n <= 0 || this.isEmpty()) {
            return this;
        } else {
            return this.tail().drop(n - 1);
        }

    }

    default Lista<T> dropX(int n) {
        return (n <= 0 || this.isEmpty())
                ?this
                :this.tail().drop(n - 1);


    }

    default Lista<T> concat(Lista<T> ls) {
        if(this.isEmpty()){
            return ls;
        }else{
            return Lista.of(this.head(),this.tail().concat(ls));

        }
    }

    default Lista<T> concatX(Lista<T> ls) {
        return  this.isEmpty()
                ?ls
                :Lista.of(this.head(),this.tail().concat(ls));

    }

    default <U> Lista <U> map(Function<T,U> fn ){
        if(isEmpty()){
            return Lista.Empty;
        }else {
            return Lista.of(fn.apply(this.head()),this.tail().map(fn));
        }
    }


    default <U> U foldLeft(U identity, Function<U,Function<T,U>> fn){
        U ret = identity;

        var tmp=this;
        while(!tmp.isEmpty()){
            ret =fn.apply(ret).apply(tmp.head());
            tmp=tmp.tail();
        }

        return ret;
    }

    default <U> U foldRight(U identity, Function<T,Function<U,U>> fn){
        return this.isEmpty()
                ?identity
                :fn.apply(this.head()).apply(this.tail().foldRight(identity,fn));
    }

    default Lista<T> invertFold(){
        return foldLeft(Lista.Empty,ls->t->ls.prepend(t));

    }

    default <U> Lista<U> mapFoldLeft(Function<T,U> fn){
        return foldLeft(Lista.Empty,ls->t->ls.append(fn.apply(t)));
                                    //ls la lista,t el elemento.
    }

    default <U> Lista<U> mapFoldRight(Function<T,U> fn){
        return foldRight(Lista.Empty, t-> ls->ls.prepend(fn.apply(t)));

    }

    default Integer countFoldLeft(){
        //en este caso da igual left o rigth pues las suma es conmutativa
        return foldLeft(0,n->t->n+1);
    }

    default Lista<T> appendFoldRight(T elem){
        return foldRight(Lista.of(elem), t-> ls->ls.prepend(t));
    }

    default T reduceFoldLeft(T identity,Function<T,Function<T,T>> fn){
        return foldLeft(identity,u->t->fn.apply(u).apply(t));

    }

    default T reduceFoldLeftSimplificado(Function<T,Function<T,T>> fn){
        return this.tail().foldLeft(this.head(),u->t->fn.apply(u).apply(t));

    }

    default Lista<T> takeFoldLeft(int n ){
        return foldLeft(Lista.Empty,
                ls->t->ls.count()<n?ls.append(t):ls


                /*
                ls->t->{
                    if(ls.count()<n){
                        return ls.append(t);
                    }else{
                       return ls;
                    }
                }*/
        );
    }

    default Lista<T> dropFoldRight(int n){
        int tot=this.count()-n;
        return foldRight(
                Lista.Empty,
                t->ls->ls.count()<tot?ls.prepend(t):ls
        );

    }



}
