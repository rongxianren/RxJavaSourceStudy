package com.rongxianren.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.rongxianren.rxjava.backpressure.BackPressure;
import com.rongxianren.rxjava.backpressure.FlowableBackPressure;
import com.rongxianren.rxjava.operators.MergeOperators;
import com.rongxianren.rxjava.subscribeFlow.MyEmitter;
import com.rongxianren.rxjava.subscribeFlow.MyObservable;
import com.rongxianren.rxjava.subscribeFlow.MyObservableOSubscribe;
import com.rongxianren.rxjava.subscribeFlow.MyObserver;
import com.rongxianren.rxjava.threadswitch.ThreadSwitch;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Random;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ////hahha
    }

    public void mergeTest(View view) {
        MergeOperators.merge();

    }

    public void switcherSubscribeLight(View view) {
        LightAndSwitcher lightAndSwitcher = new LightAndSwitcher();
        lightAndSwitcher.lightSubscribeSwitcher();
    }


    public void threadSwitch(View view) {
        ThreadSwitch threadSwitch = new ThreadSwitch();
        threadSwitch.threadSwitch();
    }

    public void flatMap(View view) {

    }


    public void simulateRxJava(View view) {
        MyObservable<String> observable = MyObservable.create(new MyObservableOSubscribe<String>() {
            @Override
            public void subscribe(MyEmitter<String> emitter) {
                emitter.onext("手动模仿rxJava的订阅触发流程");
            }
        });

        observable
                .opreator()
                .opreator()
                .opreator()
                .subscribe(
                        new MyObserver<String>() {
                            @Override
                            public void next(String item) {
                                System.out.println(item);
                            }
                        }
                );
    }

    public void backPressure(View view) {
//        BackPressure backPressure = new BackPressure();
//        backPressure.backPressure();
        backPressure();
    }

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
                for (int i = 0; i < 169; i++) {
                    emitter.onNext(i);
                    System.out.println("发送完成" + i);
                }
                emitter.onComplete();
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "接收到了事件" + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError: " + t);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }

    public void flowableBackPressure(View view) {
        FlowableBackPressure flowableBackPressure = new FlowableBackPressure();
        flowableBackPressure.backPressure();
    }
}
