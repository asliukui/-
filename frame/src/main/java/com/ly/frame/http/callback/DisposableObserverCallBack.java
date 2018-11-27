package com.ly.frame.http.callback;

import io.reactivex.observers.DisposableObserver;

/**
 * @Author: lk
 * @Date: 2018/11/23
 * @Description:
 */
public class DisposableObserverCallBack<T> extends DisposableObserver<T> {
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
