package com.ly.frame.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.ly.frame.base.mvp.BasePresenter;
import com.ly.frame.base.mvp.IView;
import com.ly.frame.utils.net.IMonitorListener;
import com.ly.frame.utils.net.Monitor;
import com.ly.frame.utils.net.NetStatus;

/**
 * @Author: lk
 * @Date: 2018/11/23
 * @Description:
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IView {
    protected P mPresenter;
    public Context mContext;
    private ProgressDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        this.mContext = this;
        initView();
        initListener();
        mPresenter = initPresenter();
        initData();
    }

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();

    public abstract int getLayoutId();

    public abstract P initPresenter();

    @Override
    protected void onStart() {
        super.onStart();
        // 网络监控注册
        Monitor.registListener(mNetListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 网络监控反注册
        Monitor.unRegistListener(mNetListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.dettach();
        }
    }

    /**
     * 显示LoadingDialog
     *
     * @param setting 数组下标 ->
     *                0.isCancelable(是否可以通过点击Back键取消)(默认true)
     *                1.isCanceledOnTouchOutside(是否在点击Dialog外部时取消Dialog)(默认false)
     */
    @Override
    public ProgressDialog showLoading(final boolean... setting) {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) return mLoadingDialog;
        boolean isCancelable = true;
        boolean isCanceledOnTouchOutside = false;
        if (setting != null) {
            if (setting.length >= 1) {
                isCancelable = setting[0];
            }
            if (setting.length >= 2) {
                isCanceledOnTouchOutside = setting[1];
            }
        }
        mLoadingDialog = new ProgressDialog(this);
        mLoadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mLoadingDialog.setCancelable(isCancelable);
        mLoadingDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
        mLoadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mLoadingDialog.setMessage("请求网络中...");
        mLoadingDialog.show();
        return mLoadingDialog;
    }
    /**
     * 隐藏LoadingDialog
     */
    @Override
    public void hideLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    // 网络状态监控
    IMonitorListener mNetListener = new IMonitorListener() {

        @Override
        public void onConnectionChange(@NonNull NetStatus status) {
            onNetStatusChange(status);
        }
    };
    /**
     * 网络状态变换调用
     */
    @CallSuper
    protected void onNetStatusChange(@NonNull NetStatus pNetStatus) {
    }
}
