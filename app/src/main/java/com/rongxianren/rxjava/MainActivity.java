package com.rongxianren.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rongxianren.rxjava.backpressure.BackPressure;
import com.rongxianren.rxjava.operators.MergeOperators;
import com.rongxianren.rxjava.subscribeFlow.MyEmitter;
import com.rongxianren.rxjava.subscribeFlow.MyObservable;
import com.rongxianren.rxjava.subscribeFlow.MyObservableOSubscribe;
import com.rongxianren.rxjava.subscribeFlow.MyObserver;
import com.rongxianren.rxjava.threadswitch.ThreadSwitch;

import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
        BackPressure backPressure = new BackPressure();
        backPressure.backpressure();
    }
}
