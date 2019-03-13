package com.rongxianren.rxjava.map_and_flatmap;

import android.animation.ObjectAnimator;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


/**
 * Created by tangyun on 2019/3/12.
 */

public class MapFlatmap {


    public void flatMap() {
        Observable.create((ObservableOnSubscribe<String>) emitter -> {

        }).flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String o) throws Exception {
                return Observable.just(o);
            }
        }).subscribe(
                new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                    }
                }
        );
    }
}
