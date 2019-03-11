package com.rongxianren.rxjava.operators;

import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tangyun on 2019/3/9.
 */

public class MergeOperators {


    private static Random random = new Random();

    public static void merge() {
        Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Thread.sleep(random.nextInt(10000));
                emitter.onNext("-----  来自缓存");
                emitter.onComplete();
                System.out.println(Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.newThread());

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Thread.sleep(random.nextInt(10000));
                emitter.onNext("-----  来自网络");
                emitter.onComplete();
                System.out.println(Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.newThread());


        Observable.merge(observable1, observable2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        d.dispose();
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                        System.out.println(Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("工作完成");
                    }
                });
    }
}
