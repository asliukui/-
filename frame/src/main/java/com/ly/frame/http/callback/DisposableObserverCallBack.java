package com.ly.frame.http.callback;

import io.reactivex.observers.DisposableObserver;

/**
 * @Author: lk
 * @Date: 2018/11/28
 * @Description:
 */
public abstract class DisposableObserverCallBack<T> extends DisposableObserver<T> {

    public DisposableObserverCallBack() {
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onFail(e.getMessage());
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);

    public abstract void onFail(String errorMsg);
}
