package com.rongxianren.rxjava.subscribeFlow;

/**
 * Created by tangyun on 2019/3/10.
 */

public class NodeObserver<T> implements MyObserver<T> {

    MyObserver downStream;


    public NodeObserver(MyObserver observer) {
        this.downStream = observer;
    }
    

    @Override
    public void next(T item) {
        downStream.next(item);
    }
}
