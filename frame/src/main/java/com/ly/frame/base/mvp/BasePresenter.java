package com.ly.frame.base.mvp;

/**
 * @Author: lk
 * @Date: 2018/11/23
 * @Description:
 */
public abstract class BasePresenter<V extends IView, D> implements IPresenter<D> {
    public V mView;

    public BasePresenter(V view) {
        attach(view);
    }

    public void attach(V view) {
        this.mView = view;
    }

    public void dettach() {
        mView = null;
    }


}
