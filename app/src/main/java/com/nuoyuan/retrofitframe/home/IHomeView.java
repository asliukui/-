package com.nuoyuan.retrofitframe.home;

import com.ly.frame.base.mvp.IView;

/**
 * @Author: lk
 * @Date: 2018/11/23
 * @Description:
 */
public interface IHomeView extends IView {

    void getResponseSuc(TranslationResponse response);

    void getErrorMsg(int code,String string);
}
