package com.ly.frame.base.mvp;

import com.ly.frame.http.BaseResponse;

import io.reactivex.disposables.Disposable;

/**
 * @Author: lk
 * @Date: 2018/11/23
 * @Description:
 * 1、初始化数据。
 * 2、获得响应数据。
 * 3、失败处理。
 */
public interface IPresenter<D> {

    /**
     * 初始化数据
     */
    void requestData();

    /**
     * 获取到的响应data
     * @param data
     * @return
     */
     void onSuccess(D data);

    /**
     * 服务器返回的及因网络状态导致请求失败的错误信息
     * @return
     */
    void onError(int code,String msg);

    void unSubscribe();

    Disposable subscribe(Disposable disposable);
}
