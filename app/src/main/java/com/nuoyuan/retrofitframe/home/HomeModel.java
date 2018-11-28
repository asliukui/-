package com.nuoyuan.retrofitframe.home;

import com.ly.frame.base.mvp.BaseModel;
import com.ly.frame.http.HttpManager;
import com.ly.frame.http.callback.DisposableObserverCallBack;
import com.ly.frame.utils.RxJavaUtils;
import com.nuoyuan.retrofitframe.http.ApiService;

/**
 * @Author: lk
 * @Date: 2018/11/23
 * @Description:
 */
public class HomeModel extends BaseModel<HomePresent> {

    public HomeModel(HomePresent presenter) {
        super(presenter);
    }

    public void requestData(String page) {
        DisposableObserverCallBack<MeiTuResponse> ds = HttpManager.request(ApiService.class)
                .getPic(page)
                .compose(RxJavaUtils.<MeiTuResponse>observableToMain())
                .subscribeWith(new DisposableObserverCallBack<MeiTuResponse>() {

                    @Override
                    public void onSuccess(MeiTuResponse response) {
                        mPresenter.onSuccess(response);
                    }

                    @Override
                    public void onFail(String errorMsg) {
                        mPresenter.onError(0, errorMsg);
                    }
                });
        //添加订阅者管理，防止内存泄露。
        mPresenter.subscribe(ds);
    }
}
