package com.nuoyuan.retrofitframe.home;

import com.ly.frame.base.mvp.BasePresenter;

/**
 * @Author: lk
 * @Date: 2018/11/23
 * @Description:
 */
public class HomePresent extends BasePresenter<IHomeView,TranslationResponse> {


    private final HomeModel homeModel;

    public HomePresent(IHomeView view) {
        super(view);
        homeModel = new HomeModel(this);
    }

    @Override
    public void requestData() {
        mView.showLoading();
        homeModel.requestData();
    }

    @Override
    public void onSuccess(TranslationResponse data) {
        mView.hideLoading();
        mView.getResponseSuc(data);
    }

    @Override
    public void onError(int code,String msg) {

    }
}
