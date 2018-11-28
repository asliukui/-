package com.ly.frame.http.callback;

import com.ly.frame.base.mvp.IPresenter;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;

/**
 * @Author: lk
 * @Date: 2018/11/23
 * @Description:
 */
public abstract class ObserverCallBack<T> implements Observer<T> {
    private IPresenter mPresenter;

    public ObserverCallBack(IPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mPresenter.subscribe(d);
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
