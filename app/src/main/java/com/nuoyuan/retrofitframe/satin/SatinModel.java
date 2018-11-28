package com.nuoyuan.retrofitframe.satin;

import com.ly.frame.base.mvp.BaseModel;
import com.ly.frame.http.HttpManager;
import com.ly.frame.http.callback.ObserverCallBack;
import com.ly.frame.logger.Logger;
import com.ly.frame.utils.RxJavaUtils;
import com.nuoyuan.retrofitframe.http.ApiService;

/**
 * @Author: lk
 * @Date: 2018/11/23
 * @Description:
 */
public class SatinModel extends BaseModel<SatinPresent> {

    public SatinModel(SatinPresent presenter) {
        super(presenter);
    }

    public void requestData(String type,String page) {
        HttpManager.request(ApiService.class)
                .getSatin(type,page)
                .compose(RxJavaUtils.<SatinResponse>observableToMain())
                .subscribe(new ObserverCallBack<SatinResponse>(mPresenter) {

                    @Override
                    public void onSuccess(SatinResponse response) {
                        mPresenter.onSuccess(response);
                        Logger.i("ddss-onNext:" + response.toString());
                    }

                    @Override
                    public void onFail(String errorMsg) {
                        mPresenter.onError(0,errorMsg);
                        Logger.i("ddss-onError:" + errorMsg);
                    }
                });
    }
}
