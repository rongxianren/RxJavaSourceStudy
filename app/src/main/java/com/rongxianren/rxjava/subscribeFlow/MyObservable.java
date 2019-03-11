package com.rongxianren.rxjava.subscribeFlow;

/**
 * Created by tangyun on 2019/3/10.
 */

public abstract class MyObservable<U> implements MyObservableSource<U> {
    @Override
    public void subscribe(MyObserver<? super U> observer) {
        subscribeActual(observer);
    }

    abstract void subscribeActual(MyObserver<? super U> nodeObserver);


    public static <T> MyObservable<T> create(MyObservableOSubscribe<T> observableOSubscribe) {
        return new MyObservableCreate(observableOSubscribe);
    }


    public MyObservable<U> opreator() {
        return new NodeObservable<>(this);
    }
}
