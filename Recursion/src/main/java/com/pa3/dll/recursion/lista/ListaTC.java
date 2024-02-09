package com.pa3.dll.recursion.lista;

import com.pa3.dll.recursion.TailCall;
import com.pa3.dll.recursion.repaso.Nodo;
import com.pa3.dll.recursion.repaso.Tuple;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * {1,2,3,4}
 *
 * [1,[2,[3,[4,[5,Empty]]]]]
 * @param <T>
 */
public interface ListaTC<T> {
    //######   Variables de clase, finales y static   ######
    ListaTC Empty=new Empty();

    //######   Metodos abstractos   ######
    T head();
    ListaTC<T> tail();
    boolean isEmpty();

    //######   of con cabecera y cola (lista=Const=Nodo)   ######
    private static <T> ListaTC<T> of(T head, ListaTC<T> tail){
        return new Const<>(head,tail);
    }

    static <T> TailCall<ListaTC<T>> ofTCR(T head, ListaTC<T> tail) {
        return TailCall.tailReturn(new Const<>(head, tail));
    }

    //######   of con parametros variables   ######
    static <T> ListaTC<T> of(T... elems){
        var tmp= ListaTC.Empty;

        for(int i=elems.length-1;i>=0;i--){
            tmp= new Const(elems[i],tmp);
        }

        return tmp;
    }


    //##################   count   ##################

    default int count(){
        return 1 + tail().count();
    }

    static <T> int countTRBase(int contador, ListaTC<T> lista){
        if(lista.isEmpty()){
            return contador;
        }else{
            return countTRBase(contador+1,lista.tail());
        }
    }
    static <T> int countTR(ListaTC<T> lista){
        return countTRBase(0,lista);
    }

    static <T> TailCall<Integer> countTCAux(ListaTC<T> lista, Integer intit) {
        if (lista.isEmpty()) {
            return TailCall.tailReturn(intit);
        } else {
            Supplier<TailCall<Integer>> ret = () ->
                    countTCAux(lista.tail(), intit + 1);
            return TailCall.tailSuspend(ret);
        }
    }

    static <T> Integer countTC(ListaTC<T> lista) {

        return countTCAux(lista, 0).eval();
    }

    //##################   prepend   ##################
    default ListaTC<T> prepend(T elemn){
        return new Const<>(elemn, this);
        //return ListaTC.of(elemn, this);
    }


    static <T> ListaTC<T> preppendTRBase(T val, ListaTC<T> original,ListaTC<T> acum) {
        if (acum.isEmpty()) {
            return ListaTC.of(val, original);
        } else {
            return preppendTRBase(val,original.tail(), ListaTC.of(original.head(),acum));
        }
    }

    static <T> ListaTC<T> preppendTR(T val, ListaTC<T> original) {
        return preppendTRBase(val, original,ListaTC.Empty);

    }

    ///
    static <T> TailCall<ListaTC<T>> preppendTCAux(T val, ListaTC<T> original) {
        if (original.isEmpty()) {
            return TailCall.tailReturn(ListaTC.of(val));
        } else {
            return TailCall.tailReturn(ListaTC.of(val, original));
        }
    }

    static <T> ListaTC<T> preppendTC(T val, ListaTC<T> original) {
        return preppendTCAux(val, original).eval();
    }

    //##################   append   ##################

    default ListaTC<T> append(T elemn){
        if(this.isEmpty()){
            return new Const(elemn,ListaTC.Empty);

        }else {
            return new Const<>(
                    this.head(),
                    this.tail().append(elemn)
            );
        }

    }
    static <T> ListaTC<T> appendTRBase(ListaTC<T> orginal, ListaTC<T> acum, T elem) {
        if (acum.isEmpty()) {
            return invertirTR(preppendTR(elem, invertirTR(orginal)));
        } else {
            return appendTRBase(orginal.tail(), preppendTR(orginal.head(),acum), elem);
        }
    }
    static <T> ListaTC<T> appendTR(ListaTC<T> list,T elem) {
        return appendTRBase(list, ListaTC.Empty, elem);
    }

    static <T> TailCall<ListaTC<T>> appendTCAux(ListaTC<T> original, ListaTC<T> acum, T elem) {
        if (acum.isEmpty()) {
            return TailCall.tailReturn(invertirTC(ListaTC.of(elem, invertirTC(original)))); //Ojo el invertir
        } else {
            return TailCall.tailSuspend(() -> appendTCAux2(original.tail(), preppendTC(original.head(),acum), elem));
        }
    }
    static <T> ListaTC<T> appendTC(T elem, ListaTC<T> list) {
        return appendTCAux(list, ListaTC.of(), elem).eval();
    }

    static <T> TailCall<ListaTC<T>> appendTCAux2(ListaTC<T> orginal, ListaTC<T> acum, T elem) {
        if (acum.isEmpty()) {
            return TailCall.tailReturn(invertirTC(ListaTC.of(elem, orginal))); //Ojo el invertir
        } else {
            return TailCall.tailSuspend(() -> appendTCAux(ListaTC.of(orginal.head(), acum), acum.tail(), elem));
        }
    }


    static <T> ListaTC<T> appendTC2(T elem, ListaTC<T> list) {
        return appendTCAux2(list, ListaTC.of(), elem).eval();
    }


    //##################   insert   ##################

    default ListaTC<T> insert(int index, T elem){
        if(index==0){
            return ListaTC.of(elem,this);
        }else{
            return ListaTC.of(this.head(),this.tail().insert(index-1,elem));
        }

    }

    static <T> ListaTC<T> insertTRBase(ListaTC<T> original, ListaTC<T> acum,T elem, int index){
        if(index==0){
            return invertirTR(concatTR(invertirTR(original),ListaTC.of(elem,acum)));
        }else{
            return insertTRBase(original.tail(),preppendTR(original.head(),acum), elem,index-1);
        }

    }
    static <T> ListaTC<T> insertTR(ListaTC<T> orginal, T elem,int index) {

        return insertTRBase(orginal, ListaTC.Empty, elem,index);

    }

    static <T> TailCall<ListaTC<T>> insertTCBase(ListaTC<T> original, ListaTC<T> acum,T elem, int index){
        if(index==0){
            return TailCall.tailReturn(invertirTR(concatTR(invertirTR(original),ListaTC.of(elem,acum))));    //Ojo el concat
        }else{
            Supplier<TailCall<ListaTC<T>>> ret=()->insertTCBase(original.tail(),preppendTR(original.head(),acum), elem,index-1);
            return TailCall.tailSuspend(ret);
        }

    }
    static <T> ListaTC<T> insertTC(ListaTC<T> orginal, T elem,int index) {

        return insertTCBase(orginal, ListaTC.of(), elem,index).eval();

    }


    //##################   get   ##################
    default T get(int index){
        if(index==0 ){
            return this.head();

        }else{
            return this.tail().get(index-1);
        }

    }

    default TailCall<T> getTCAux(int index){
        if(index==0 ){
            return TailCall.tailReturn(this.head());
        }else{
            Supplier<TailCall<T>> ret= ()-> this.tail().getTCAux(index-1);
            return TailCall.tailSuspend(ret);
        }
    }

    default T getTC(int index){
        return getTCAux(index).eval();
    }

    //##################   take   ##################
    default ListaTC<T> take(int n){
        if(n<=0 || this.isEmpty()){
            return ListaTC.Empty;
        }else{
            return ListaTC.of(this.head(),this.tail().take(n-1));
        }

    }

    static <T> ListaTC<T> takeTRBase(ListaTC<T> list, ListaTC<T> acum, Integer elem) {
        if (list.isEmpty()) {
            return ListaTC.Empty;
        } else {
            if (elem == 0) {
                return acum;
            } else {
                return takeTRBase(list.tail(), preppendTR(list.head(), acum), elem - 1);
            }
        }
    }

    static <T> ListaTC<T> takeTR(ListaTC<T> list, Integer elem) {
        return invertirTR(takeTRBase(list, ListaTC.Empty, elem));
    }

    static <T> TailCall<ListaTC<T>> takeTCAux(ListaTC<T> list, ListaTC<T> acum, Integer elem) {
        if (list.isEmpty()) {
            return TailCall.tailReturn(ListaTC.Empty);
        } else {
            if (elem == 0) {
                return TailCall.tailReturn(acum);
            } else {
                Supplier<TailCall<ListaTC<T>>> ret = () ->
                        takeTCAux(list.tail(), preppendTC(list.head(), acum), elem - 1);
                return TailCall.tailSuspend(ret);
            }
        }

    }

    static <T> ListaTC<T> takeTC(ListaTC<T> list, Integer elem) {
        return invertirTC(takeTCAux(list, ListaTC.of(), elem).eval());
    }

    //##################   drop   ##################
    default ListaTC<T> drop(int n) {

        if (n <= 0 || this.isEmpty()) {
            return this;
        } else {
            return this.tail().drop(n - 1);
        }

    }

    static <T> ListaTC<T> dropTRBase(ListaTC<T> list, Integer elem) {
        if (!(elem == 0)) {

            return dropTRBase(list.tail(), elem - 1);

        } else if (elem == 0) {

            return list;
        } else {
            return list.tail();
        }

    }

    static <T> ListaTC<T> dropTR(ListaTC<T> list, Integer elem) {

        return dropTRBase(list, elem);
    }


    static <T> TailCall<ListaTC<T>> dropTCAux(ListaTC<T> list, Integer elem) {
        if (!(elem == 0)) {
            Supplier<TailCall<ListaTC<T>>> ret = () ->
                    dropTCAux(list.tail(), elem - 1);
            return TailCall.tailSuspend(ret);
        } else if (elem == 0) {
            return TailCall.tailReturn(list);
        } else {
            return TailCall.tailReturn(list.tail());
        }
    }

    static <T> ListaTC<T> dropTC(ListaTC<T> list, Integer elem) {
        return dropTCAux(list, elem).eval();
    }

    //##################   concat   ##################
    default ListaTC<T> concat(ListaTC<T> ls) {
        if(this.isEmpty()){
            return ls;
        }else{
            return ListaTC.of(this.head(),this.tail().concat(ls));

        }
    }

    static <T> ListaTC<T> concatTRBase(ListaTC<T> primero, ListaTC<T> segundo, ListaTC<T> acumulador) {
        if(segundo.isEmpty() && primero.isEmpty()){
            return invertirTR(acumulador);
        }else if(primero.isEmpty()){
            return concatTRBase(primero,segundo.tail(),preppendTR(segundo.head(),acumulador));
        }else{
            return concatTRBase(primero.tail(),segundo,preppendTR(primero.head(),acumulador));

        }
    }
    static <T> ListaTC<T> concatTR(ListaTC<T> primero, ListaTC<T> segundo) {
        return concatTRBase(primero,segundo,ListaTC.Empty);
    }


    static <T> TailCall<ListaTC<T>> concatTCAux(ListaTC<T> ListaTC1, ListaTC<T> ListaTC2) {
        if (ListaTC1.isEmpty()) {
            return TailCall.tailReturn(ListaTC2);
        } else {
            Supplier<TailCall<ListaTC<T>>> ret1 = () ->
                    concatTCAux(ListaTC1.tail(), preppendTC(ListaTC1.head(), ListaTC2));
            return TailCall.tailSuspend(ret1);
        }
    }

    static <T> ListaTC<T> concatTC(ListaTC<T> lista1, ListaTC<T> lista2) {
        return concatTCAux(invertirTC(lista1), lista2).eval();
    }

    //##################   map   ##################
    default <U> ListaTC<U> map(Function<T,U> fn ){
        if(isEmpty()){
            return ListaTC.Empty;
        }else {
            return ListaTC.of(fn.apply(this.head()),this.tail().map(fn));
        }
    }

    static <T, U> ListaTC<U> mapTRBase(ListaTC<T> original, ListaTC<U> acumulador, Function<T, U> fn) {
        if (original.isEmpty()) {
            return invertirTR(acumulador);
        } else {
            return mapTRBase(original.tail(), ListaTC.of(fn.apply(original.head()), acumulador), fn);
        }
    }

    static <T,U> ListaTC<U> mapTR(ListaTC<T> original,Function<T, U> fn) {
        return mapTRBase(original, ListaTC.Empty, fn);
    }

    static <T, U> TailCall<ListaTC<U>> mapTCBase(ListaTC<T> original, ListaTC<U> acumulador, Function<T, U> fn) {
        if (original.isEmpty()) {
            return TailCall.tailReturn(invertirTR(acumulador));
        } else {
            Supplier<TailCall<ListaTC<U>>> ret= ()->mapTCBase(original.tail(), ListaTC.of(fn.apply(original.head()), acumulador), fn);
            return TailCall.tailSuspend(ret);
        }
    }

    static <T,U> ListaTC<U> mapTC(ListaTC<T> original,Function<T, U> fn) {
        return (ListaTC<U>) mapTCBase(original, ListaTC.Empty, fn).eval();
    }





    //##################   foldLeft   ##################
    default <U> U foldLeft(U identity, Function<U,Function<T,U>> fn){
        U ret = identity;
        var tmp=this;

        while(!tmp.isEmpty()){
            ret =fn.apply(ret).apply(tmp.head());
            tmp=tmp.tail();
        }

        return ret;
    }

    //##################   foldRight   ##################
    default <U> U foldRight(U identity, Function<T,Function<U,U>> fn){
        if(this.isEmpty()){
            return identity;
        }else{
            return fn.apply(this.head()).apply(this.tail().foldRight(identity,fn));
        }

    }

    //##################   invert   ##################
    //invertFoldLeft
    default ListaTC<T> invertFold(){
        return foldLeft(ListaTC.Empty, ls-> t->ls.prepend(t));

    }

    static <T> ListaTC<T> invertirTRBase(ListaTC<T> listaTC, ListaTC<T> acum) {
        if (listaTC.isEmpty()) {
            return acum;
        } else {
            return invertirTRBase(listaTC.tail(), preppendTR(listaTC.head(), acum));
        }
    }

    static <T> ListaTC<T> invertirTR(ListaTC<T> list) {
        return invertirTRBase(list, ListaTC.Empty);
    }

    static <T> TailCall<ListaTC<T>> invertirTCAux(ListaTC<T> lista, ListaTC<T> acum) {
        if (lista.isEmpty()) {
            return TailCall.tailReturn(acum);
        } else {
            Supplier<TailCall<ListaTC<T>>> ret = () ->
                    invertirTCAux(lista.tail(), preppendTC(lista.head(), acum));
            return TailCall.tailSuspend(ret);
        }
    }

    static <T> ListaTC<T> invertirTC(ListaTC<T> list) {
        return invertirTCAux(list, ListaTC.of()).eval();
    }

    //##################   mapFoldLeft   ##################
    default <U> ListaTC<U> mapFoldLeft(Function<T,U> fn){
        return foldLeft(ListaTC.Empty, ls-> t->ls.append(fn.apply(t)));
                                    //ls la lista,t el elemento.
    }



    //##################   mapFoldRight   ##################
    default <U> ListaTC<U> mapFoldRight(Function<T,U> fn){
        return foldRight(ListaTC.Empty, t-> ls->ls.prepend(fn.apply(t)));

    }

    //##################   countFoldLeft   ##################
    default Integer countFoldLeft(){
        //en este caso da igual left o rigth pues las suma es conmutativa
        return foldLeft(0,n->t->n+1);
    }

    //##################   appendFoldRight   ##################
    default ListaTC<T> appendFoldRight(T elem){
        return foldRight(ListaTC.of(elem), t-> ls->ls.prepend(t));
    }

    //##################   prependFoldRight   ##################
    default ListaTC<T> prependFoldLeft(T elem){
        return foldLeft(ListaTC.of(elem), ls-> t->ls.append(t));
    }

    //##################   reduceFoldLeft   ##################
    default T reduceFoldLeft(T identity,Function<T,Function<T,T>> fn){
        return foldLeft(identity,u->t->fn.apply(u).apply(t));

    }

    //##################   reduceFoldLeftSimplificado   ##################
    default T reduceFoldLeftSimplificado(Function<T,Function<T,T>> fn){
        return this.tail().foldLeft(this.head(),u->t->fn.apply(u).apply(t));
    }//resulta que "this.head()" es mi identidad, por ello como que me salto
    //el primer "nodo/const" porque ya lo mando directo como parametro.

    //##################   takeFoldLeft   ##################
    default ListaTC<T> takeFoldLeft(int n ){
        return foldLeft(ListaTC.Empty,
                ls->t->ls.count()<n
                        ?ls.append(t)
                        :ls
                );
    }

    //##################   dropFoldRight   ##################
    default ListaTC<T> dropFoldRight(int n){
        int tot=this.count()-n;
        return foldRight(
                ListaTC.Empty,
                t->ls->ls.count()<tot
                        ?ls.prepend(t)
                        :ls
        );

    }

    //##################   range   ##################
    static ListaTC<Integer> rangeRec(Integer start, Integer end){
        if(start<end){
            return ListaTC.of(start, rangeRec(start+1,end));

        }

        return ListaTC.Empty;
    }


    static ListaTC<Integer> rangeTailRec(Integer start, Integer end, ListaTC<Integer> acc) {
        if (start < end) {
            return rangeTailRec(start, end - 1, acc.prepend(end - 1));
        } else {
            return acc;
        }
    }

    static TailCall<ListaTC<Integer>> rangeTailCall(Integer start, Integer end, ListaTC<Integer> acc) {
        if (start < end) {
            //return rangeTailRec(start, end - 1, acc.prepend(end - 1));
            Supplier<TailCall<ListaTC<Integer>>> ret = () ->{
                return  rangeTailCall(start,end - 1, preppendTC(end-1, acc));
            };

            return TailCall.tailSuspend(ret);

        } else {
            //return acc;
            return TailCall.tailReturn(acc);
        }


    }
    static <T> ListaTC<T> unfoldImperativo(T start, Function<T, T> f, Predicate<T> p) {
        ListaTC listaTCR = ListaTC.of(start);
        var value=f.apply(start);
        while(p.test(value)){
            listaTCR = listaTCR.prepend(value);

            value=f.apply(value);
        }
        return listaTCR.invertFold();
    }
    static <T> ListaTC<T> unfoldRecursivo(T start, Function<T, T> f, Predicate<T> p) {
        if(p.test(start)){
            return ListaTC.of(start,unfoldRecursivo(f.apply(start),f,p));
        }
        return ListaTC.Empty;
    }

    static <T> ListaTC<T> unfoldTailRecursivo(T start, Function<T, T> f, Predicate<T> p, ListaTC<T> acc) {

        if(p.test(start)){
            return unfoldTailRecursivo(f.apply(start),f,p,acc.prepend(start));
        }
        return acc.invertFold();
    }

    static ListaTC<Integer> rangeUnfold(Integer start, Integer end) {

        return ListaTC.unfoldTailRecursivo(start, x->x+1, x->x.compareTo(end)<0, ListaTC.Empty);

    }

    /////////////////////////////////////////////////////////////////////////////////

    private static ListaTC<Tuple> tuplasSucesorasTRAux(ListaTC<Tuple> listaTuplas, ListaTC<Tuple> acum , Nodo nodo){

        if(listaTuplas.isEmpty()){
            return ListaTC.invertirTR(acum);
        }else{
            return tuplasSucesorasTRAux(listaTuplas.tail(), listaTuplas.head().contains(nodo)? ListaTC.preppendTR(listaTuplas.head(),acum): acum,nodo);
        }

    }

    private static ListaTC<Tuple> tuplasSucesorasTR(ListaTC<Tuple> listaTupla ,Nodo nodo){

        return tuplasSucesorasTRAux(listaTupla,ListaTC.Empty,nodo);

    }

    private static ListaTC<Nodo> sucesoresTRAux(ListaTC<Tuple> listaTuplasSucesoras,ListaTC<Nodo> listaNodos){

        if(listaTuplasSucesoras.isEmpty()){
            return ListaTC.invertirTR(listaNodos);
        }else{
            return sucesoresTRAux(listaTuplasSucesoras.tail(),ListaTC.preppendTR(listaTuplasSucesoras.head().arg2(),listaNodos));
        }

    }

    private static ListaTC<Nodo> sucesoresTR(ListaTC<Tuple> listaTuplasSucesoras){
        return sucesoresTRAux(listaTuplasSucesoras,ListaTC.Empty);
    }

    public static ListaTC<Nodo> nodosSucesores(ListaTC<Tuple> listaTuplasOriginal,Nodo nodo){
        return sucesoresTR(tuplasSucesorasTR(listaTuplasOriginal,nodo));
    }

    /////////////////////////////////////////////////////////////////////////////////folding
    default ListaTC<Tuple> tuplasSucesorasFolding(Nodo nodo){
        return foldLeft(ListaTC.Empty,ls->tp-> ((Tuple)tp).contains(nodo)?ls.append(tp):ls    );
    }

    default ListaTC<Nodo> nodosSucesores(Nodo nodo){
        ListaTC<Tuple> tuplasSucesorasFolding=tuplasSucesorasFolding(nodo);
        return tuplasSucesorasFolding.foldLeft(ListaTC.Empty,ls->nd-> ls.append(nd.arg2())    );
    }

    ////////////////////////Monedas
    public static ListaTC<Integer> descomposicionMonedasAux(ListaTC<Integer> listaMonedas,ListaTC<Integer> acumulador, Integer dinero){
        if(dinero.intValue()<=0){
            return acumulador.invertFold();
        }else if(dinero.intValue()>=listaMonedas.head()){
                return descomposicionMonedasAux(listaMonedas,acumulador.prepend(listaMonedas.head()),dinero-listaMonedas.head());
        }else{
                return descomposicionMonedasAux(listaMonedas.tail(),acumulador,dinero);
        }

    }
    public static ListaTC<Integer> descomposicionMonedas(ListaTC<Integer> listaMonedas, Integer dinero){
        return descomposicionMonedasAux(listaMonedas,ListaTC.Empty,dinero);
    }

    ////////////////////////Eliminacion de elementos
    public static <T> ListaTC<T> eliminacionNAux(ListaTC<T> lista,ListaTC<T> acumulador,Predicate<T> predicado, Integer n){
        if(n<=0){
            return concatTC(invertirTC(acumulador),lista);
        }else{
            return eliminacionNAux(lista.tail(),predicado.test(lista.head())?acumulador:acumulador.prepend(lista.head()),predicado, n-1);
        }

    }

    public static <T> TailCall<ListaTC<T>> eliminacionNAux_TC(ListaTC<T> lista,ListaTC<T> acumulador,Predicate<T> predicado, Integer n){
        if(n<=0){
            return TailCall.tailReturn(concatTC(invertirTC(acumulador),lista));
        }else{
            Supplier<TailCall<ListaTC<T>>> ret=()-> eliminacionNAux_TC(lista.tail(),predicado.test(lista.head())?acumulador:acumulador.prepend(lista.head()),predicado, n-1);
            return TailCall.tailSuspend(ret);
        }
    }

    public static <T> ListaTC<T> eliminacionN_TC(ListaTC<T> lista,Predicate<T> predicado, Integer n){
        return eliminacionNAux_TC(lista,ListaTC.of(),predicado,n).eval();
    }


}




