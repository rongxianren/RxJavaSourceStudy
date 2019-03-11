package com.rongxianren.rxjava.subscribeFlow;

/**
 * Created by tangyun on 2019/3/10.
 */

public class MyEmitter<T> {

    MyObserver<T> observer;

    public MyEmitter(MyObserver<T> observer) {
        this.observer = observer;
    }

    public void onext(T item) {
        if (observer != null) {
            observer.next(item);
        }
    }
}
