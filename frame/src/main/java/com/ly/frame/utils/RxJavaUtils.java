package com.ly.frame.utils;


import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author: lk
 * @Date: 2018/11/23
 * @Description:
 */
public class RxJavaUtils {

    /**
     * 处理网络请求返回线程切换 不支持背压
     * Observable在2.0默认支持16个任务。
     */
    public static <T> ObservableTransformer<T, T> observableToMain() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }

        };
    }

    /**
     * 处理网络请求返回线程切换 支持背压
     */
    public static <T> FlowableTransformer<T, T> flowableToMain() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
