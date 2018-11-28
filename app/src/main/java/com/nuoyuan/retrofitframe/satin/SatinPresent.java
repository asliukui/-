package com.nuoyuan.retrofitframe.satin;


import com.ly.frame.base.mvp.BasePresenter;
import com.ly.frame.logger.Logger;

/**
 * @Author: lk
 * @Date: 2018/11/23
 * @Description:
 */
public class SatinPresent extends BasePresenter<ISatinView, SatinResponse> {


    private final SatinModel satinModel;

    public SatinPresent(ISatinView view) {
        super(view);
        satinModel = new SatinModel(this);
    }

    @Override
    public void requestData() {
    }

    public void requestSatin(String type, String page) {
        showLoading();
        satinModel.requestData(type, page);
    }

    @Override
    public void onSuccess(SatinResponse data) {
        hideLoading();
        Logger.i("onSuccess="+data.toString());
        mView.getResponseSuc(data);
    }

    @Override
    public void onError(int code, String msg) {

    }
}
