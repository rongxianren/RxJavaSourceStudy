package com.rongxianren.rxjava.subscribeFlow;

/**
 * Created by tangyun on 2019/3/10.
 */

public interface MyObservableSource<U> {
    void subscribe(MyObserver<? super U> observer);
}
