package com.rongxianren.rxjava.subscribeFlow;

/**
 * Created by tangyun on 2019/3/10.
 */

public class MyObservableCreate<U> extends MyObservable<U> {

    private MyObservableOSubscribe source;

    public MyObservableCreate(MyObservableOSubscribe<U> myObservableOSubscribe) {
        source = myObservableOSubscribe;
    }

    @Override
    void subscribeActual(MyObserver nodeObserver) {
        source.subscribe(new MyEmitter(nodeObserver));
    }
}
