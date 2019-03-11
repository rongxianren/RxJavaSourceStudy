package com.rongxianren.rxjava.subscribeFlow;

/**
 * Created by tangyun on 2019/3/10.
 */

public abstract class MyObservableWithUpstream<T, U> extends MyObservable<U> {

    protected final MyObservableSource<T> source;

    public MyObservableWithUpstream(MyObservableSource<T> source) {
        this.source = source;
    }

}
