package com.rongxianren.rxjava.threadswitch;


import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.concurrent.Executor;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tangyun on 2019/3/10.
 */

public class ThreadSwitch {

    Executor executor = new Executor() {
        @Override
        public void execute(@NonNull Runnable command) {
            Thread thread = new Thread(command);
            thread.setName("我是自定义的线程");
            thread.start();
        }
    };


    public void threadSwitch() {
        Observable<String> observable = Observable.create(
                emitter -> {
                    System.out.println("发生器要发射数据啦  thread  " + Thread.currentThread().getName());
                    emitter.onNext("看看大家在干嘛");
                }
        );


        observable
                .subscribeOn(Schedulers.from(executor))
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.from(executor))
                .map(s -> {
                    System.out.println("我是map操作符  thread " + Thread.currentThread().getName());
                    return s;
                })
                .observeOn(Schedulers.newThread())
                .filter(s -> {
                    System.out.println("我是filter操作符 thread " + Thread.currentThread().getName());
                    if (!TextUtils.isEmpty(s)) {
                        return true;
                    } else {
                        return false;
                    }
                })
                .observeOn(Schedulers.from(executor))
                .subscribe(result -> {
                    System.out.println(result + "----thread---- " + Thread.currentThread().getName());
                });
    }
}
