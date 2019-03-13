package com.rongxianren.rxjava.backpressure;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tangyun on 2019/3/11.
 */

public class FlowableBackPressure {

    private Subscription subscription;

    public void backPressure() {


        Flowable.interval(1, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        subscription = s;
                        s.request(10);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        //观察者每过800ms处理一个事件
                        try {
                            Thread.sleep(800);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(aLong);
                        if (subscription != null) {
                            subscription.request(1);
                        }
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
