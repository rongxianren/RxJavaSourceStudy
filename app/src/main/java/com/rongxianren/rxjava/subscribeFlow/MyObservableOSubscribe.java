package com.rongxianren.rxjava.subscribeFlow;

/**
 * Created by tangyun on 2019/3/10.
 */

public interface MyObservableOSubscribe<T> {

    void subscribe(MyEmitter<T> emitter);
}
