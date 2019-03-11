package com.rongxianren.rxjava.backpressure;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tangyun on 2019/3/10.
 */

public class BackPressure {


    public void backpressure() {
        //被观察者每过1ms发射一个事件
        Observable.interval(1, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.newThread())
                .subscribe((aLong) -> {
                    //观察者每过800ms处理一个事件
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.e("rx_test", "back_pressure：" + aLong);
                });
    }
}
