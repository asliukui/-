package com.ly.frame.base.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @Author: lk
 * @Date: 2018/11/23
 * @Description:
 */
public abstract class BasePresenter<V extends IView, D> implements IPresenter<D> {
    public CompositeDisposable mCompositeDisposable;
    public V mView;

    public BasePresenter(V view) {
        attach(view);
    }

    public void attach(V view) {
        this.mView = view;
    }

    public void dettach() {
        mView = null;
        unSubscribe();
    }

    public void showLoading(final boolean... setting) {
        if (mView != null) mView.showLoading();
    }

    public void hideLoading() {
        if (mView != null) mView.hideLoading();
    }

    @Override
    public void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public Disposable subscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
        return disposable;
    }
}
