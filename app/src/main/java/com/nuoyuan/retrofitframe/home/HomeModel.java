package com.nuoyuan.retrofitframe.home;

import com.ly.frame.base.mvp.BaseModel;
import com.ly.frame.http.HttpManager;
import com.ly.frame.logger.Logger;
import com.ly.frame.utils.RxJavaUtils;
import com.nuoyuan.retrofitframe.http.ApiService;

import io.reactivex.observers.DisposableObserver;

/**
 * @Author: lk
 * @Date: 2018/11/23
 * @Description:
 */
public class HomeModel extends BaseModel<HomePresent> {

    public HomeModel(HomePresent presenter) {
        super(presenter);
    }

    public void requestData() {
        HttpManager.request(ApiService.class)
                .getYDaoData()
                .compose(RxJavaUtils.<TranslationResponse>observableToMain())
                .subscribe(new DisposableObserver<TranslationResponse>() {
                    @Override
                    protected void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onNext(TranslationResponse translationResponse) {
                        mPresenter.onSuccess(translationResponse);
                        Logger.i("ddss-onNext:" + translationResponse.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        mPresenter.onError(0,e.toString());
                        Logger.i("ddss-onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Logger.i("ddss-onComplete:");
                    }
                });
    }
}
