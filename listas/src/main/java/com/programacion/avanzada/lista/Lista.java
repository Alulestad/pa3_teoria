package com.programacion.avanzada.lista;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * {1,2,3,4}
 *
 * [1,[2,[3,[4,[5,Empty]]]]]
 * @param <T>
 */
public interface Lista <T> {
    //######Variables de clase, finales y static######
    Lista Empty=new Empty();

    //######Metodos abstractos######
    T head();
    Lista<T> tail();
    boolean isEmpty();

    //######of con cabecera y cola (lista=Const=Nodo)######
    private static <T> Lista<T> of(T head, Lista<T> tail){
        return new Const<>(head,tail);
    }

    //######of con parametros variables######
    static <T> Lista<T> of(T... elems){
        var tmp=Lista.Empty;

        for(int i=elems.length-1;i>=0;i--){
            tmp= new Const(elems[i],tmp);
        }

        return tmp;
    }

    //##################count##################
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

    //##################prepend##################
    default Lista<T> prepend(T elemn){
        return new Const<>(elemn, this);
    }

    default Lista<T> prependOf(T elemn){
        return Lista.of(elemn, this);
    }

    //##################append##################
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

    //##################insert##################
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

    //##################insert##################
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

    //##################take##################
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

    //##################drop##################
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

    //##################concat##################
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

    //##################map##################
    default <U> Lista <U> map(Function<T,U> fn ){
        if(isEmpty()){
            return Lista.Empty;
        }else {
            return Lista.of(fn.apply(this.head()),this.tail().map(fn));
        }
    }

    //##################foldLeft##################
    default <U> U foldLeft(U identity, Function<U,Function<T,U>> fn){
        U ret = identity;
        var tmp=this;

        while(!tmp.isEmpty()){
            ret =fn.apply(ret).apply(tmp.head());
            tmp=tmp.tail();
        }

        return ret;
    }

    //##################foldRight##################
    default <U> U foldRight(U identity, Function<T,Function<U,U>> fn){
        return this.isEmpty()
                ?identity
                :fn.apply(this.head()).apply(this.tail().foldRight(identity,fn));
    }

    //##################invertFoldLeft##################
    default Lista<T> invertFold(){
        return foldLeft(Lista.Empty,ls->t->ls.prepend(t));

    }

    //##################mapFoldLeft##################
    default <U> Lista<U> mapFoldLeft(Function<T,U> fn){
        return foldLeft(Lista.Empty,ls->t->ls.append(fn.apply(t)));
                                    //ls la lista,t el elemento.
    }

    //##################mapFoldRight##################
    default <U> Lista<U> mapFoldRight(Function<T,U> fn){
        return foldRight(Lista.Empty, t-> ls->ls.prepend(fn.apply(t)));

    }

    //##################countFoldLeft##################
    default Integer countFoldLeft(){
        //en este caso da igual left o rigth pues las suma es conmutativa
        return foldLeft(0,n->t->n+1);
    }

    //##################appendFoldRight##################
    default Lista<T> appendFoldRight(T elem){
        return foldRight(Lista.of(elem), t-> ls->ls.prepend(t));
    }

    //##################prependFoldRight##################
    default Lista<T> prependFoldLeft(T elem){
        return foldLeft(Lista.of(elem), ls-> t->ls.append(t));
    }

    //##################reduceFoldLeft##################
    default T reduceFoldLeft(T identity,Function<T,Function<T,T>> fn){
        return foldLeft(identity,u->t->fn.apply(u).apply(t));

    }

    //##################reduceFoldLeftSimplificado##################
    default T reduceFoldLeftSimplificado(Function<T,Function<T,T>> fn){
        return this.tail().foldLeft(this.head(),u->t->fn.apply(u).apply(t));
    }//resulta que "this.head()" es mi identidad, por ello como que me salto
    //el primer "nodo/const" porque ya lo mando directo como parametro.

    //##################takeFoldLeft##################
    default Lista<T> takeFoldLeft(int n ){
        return foldLeft(Lista.Empty,
                ls->t->ls.count()<n
                        ?ls.append(t)
                        :ls
                );
    }

    //##################dropFoldRight##################
    default Lista<T> dropFoldRight(int n){
        int tot=this.count()-n;
        return foldRight(
                Lista.Empty,
                t->ls->ls.count()<tot
                        ?ls.prepend(t)
                        :ls
        );

    }

    static Lista<Integer> rangeRec(Integer start, Integer end){
        if(start<end){
            return Lista.of(start, rangeRec(start+1,end));
        }

        return Lista.Empty;
    }



//#########################################################################################################
//*********************************************************************************************************
//*********************************************DEBER*******************************************************
//*********************************************************************************************************
//#########################################################################################################

    static Lista<Integer> rangeTailRec(Integer start, Integer end, Lista<Integer> acc) {
        if (start < end) {
            return rangeTailRec(start, end - 1, acc.prepend(end - 1));
        } else {
            return acc;
        }
    }
    static <T> Lista<T> unfoldImperativo(T start, Function<T, T> f, Predicate<T> p) {
        Lista listaR=Lista.of(start);
        var value=f.apply(start);
        while(p.test(value)){
            listaR= listaR.prepend(value);

            value=f.apply(value);
        }
        return listaR.invertFold();
    }
    static <T> Lista<T> unfoldRecursivo(T start, Function<T, T> f, Predicate<T> p) {
        if(p.test(start)){
            return Lista.of(start,unfoldRecursivo(f.apply(start),f,p));
        }
        return Lista.Empty;
    }

    static <T> Lista<T> unfoldTailRecursivo(T start, Function<T, T> f, Predicate<T> p,Lista<T> acc) {

        if(p.test(start)){
            return unfoldTailRecursivo(f.apply(start),f,p,acc.prepend(start));
        }
        return acc.invertFold();
    }

    static Lista<Integer> rangeUnfold(Integer start, Integer end) {

        return Lista.unfoldTailRecursivo(start, x->x+1, x->x.compareTo(end)<0,Lista.Empty);

    }

}




