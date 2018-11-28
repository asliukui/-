package com.nuoyuan.retrofitframe.home;

import com.ly.frame.base.mvp.BasePresenter;

/**
 * @Author: lk
 * @Date: 2018/11/23
 * @Description:
 */
public class HomePresent extends BasePresenter<IHomeView,MeiTuResponse> {


    private final HomeModel homeModel;

    public HomePresent(IHomeView view) {
        super(view);
        homeModel = new HomeModel(this);
    }

    @Override
    public void requestData() {
        showLoading();
        homeModel.requestData("1");
    }

    @Override
    public void onSuccess(MeiTuResponse data) {
        hideLoading();
        mView.getResponseSuc(data);
    }

    @Override
    public void onError(int code,String msg) {

    }
}
