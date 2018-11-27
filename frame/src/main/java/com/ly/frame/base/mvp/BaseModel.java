package com.ly.frame.base.mvp;

/**
 * @Author: lk
 * @Date: 2018/11/23
 * @Description:
 */
public abstract class BaseModel<P extends BasePresenter> implements IModel {
    public P mPresenter;

    public BaseModel(P presenter) {
        this.mPresenter = presenter;
    }
}
