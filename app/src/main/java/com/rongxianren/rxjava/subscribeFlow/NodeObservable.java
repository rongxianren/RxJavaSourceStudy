package com.rongxianren.rxjava.subscribeFlow;

/**
 * Created by tangyun on 2019/3/10.
 */

public class NodeObservable<T, U> extends MyObservableWithUpstream<T, U> {


    public NodeObservable(MyObservableSource<T> source) {
        super(source);
    }

    @Override
    void subscribeActual(MyObserver<? super U> myObserver) {
        source.subscribe((MyObserver<? super T>) new NodeObserver<U>(myObserver));
    }
}
