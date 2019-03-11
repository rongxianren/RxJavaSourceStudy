package com.rongxianren.rxjava;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tangyun on 2019/3/9.
 */

public class LightAndSwitcher {


    /**
     * 台灯订阅开关的动作
     */
    public void lightSubscribeSwitcher() {

        Observable<String> switcher1 = Observable.create(
                emitter -> {
                    emitter.onNext("turn on");
                    emitter.onNext("turn off");
                    emitter.onNext("turn on");
                    emitter.onNext("turn off");
                }
        );


        Observable<String> switcher2 = Observable.just("turn on", "turn off", "turn on", "turn off");


        String[] items = {"turn on", "turn off", "turn on", "turn off"};
        Observable<String> switcher3 = Observable.fromArray(items);


        Observer<String> light1 = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("开关操作完成");
            }
        };


        Consumer<String> light2 = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        };

        System.out.println("开始第一轮");
        switcher1.subscribe(light1);
        switcher1.subscribe(light2);

        System.out.println("开始第二轮");
        switcher2.subscribeOn(Schedulers.newThread()).subscribe(light1);
        switcher2.subscribe(light2);

        System.out.println("开始第三轮");
        switcher3.subscribe(light1);
        switcher3.subscribe(light2);

    }
}
