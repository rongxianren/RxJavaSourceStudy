package com.rongxianren.rxjava.backpressure;


import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by tangyun on 2019/3/10.
 */

public class BackPressure {

    public void backPressure() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
//                Log.d(TAG, "发送事件 1");
//                emitter.onNext(1);
//                Log.d(TAG, "发送事件 2");
//                emitter.onNext(2);
//                Log.d(TAG, "发送事件 3");
//                emitter.onNext(3);
//                Log.d(TAG, "发送事件 4");
//                emitter.onNext(4);
//                Log.d(TAG, "发送完成");
                for (int i = 0; i < 2100000000; i++) {
                    emitter.onNext(i);
                    System.out.println("发送完成" + i);
                }
                emitter.onComplete();
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "接收到了事件" + integer);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
