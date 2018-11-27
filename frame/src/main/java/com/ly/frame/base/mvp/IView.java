package com.ly.frame.base.mvp;

import android.app.ProgressDialog;

/**
 * @Author: lk
 * @Date: 2018/11/23
 * @Description:
 */
public interface IView {

    /**
     * 显示LoadingDialog
     *
     * @param setting 数组下标 ->
     *                0.isCancelable(是否可以通过点击Back键取消)(默认true)
     *                1.isCanceledOnTouchOutside(是否在点击Dialog外部时取消Dialog)(默认false)
     */
    ProgressDialog showLoading(final boolean... setting);

    /**
     * 隐藏进度条
     */
    void hideLoading();

}
