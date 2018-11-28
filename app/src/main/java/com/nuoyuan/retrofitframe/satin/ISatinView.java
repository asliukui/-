package com.nuoyuan.retrofitframe.satin;

import com.ly.frame.base.mvp.IView;
import com.nuoyuan.retrofitframe.home.MeiTuResponse;

/**
 * @Author: lk
 * @Date: 2018/11/23
 * @Description:
 */
public interface ISatinView extends IView {

    void getResponseSuc(SatinResponse response);

    void getErrorMsg(int code, String string);
}
