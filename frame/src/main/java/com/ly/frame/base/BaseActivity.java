package com.ly.frame.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ly.frame.R;
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
        init();
    }

    private void init() {
        initSystemBarTint();//设置状态栏颜色
        setContentView(getLayoutId());
        this.mContext = this;
        initToolbar();
        initView();
        initListener();
        mPresenter = initPresenter();
        initData();
    }

    protected void initToolbar() {

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

    /**
     * 设置状态栏颜色
     */
    protected void initSystemBarTint() {
        Window window = getWindow();
        if (translucentStatusBar()) {
            // 设置状态栏全透明
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            return;
        }
        // 沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上使用原生方法
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(setStatusBarColor());
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4-5.0使用三方工具类
          /*  getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(setStatusBarColor());*/
        }
    }

    /**
     * 子类可以重写决定是否使用透明状态栏
     */
    protected boolean translucentStatusBar() {
        return false;
    }

    /**
     * 子类可以重写改变状态栏颜色
     */
    protected int setStatusBarColor() {
        return getColorPrimary();
    }

    /**
     * 获取主题色
     */
    public int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }
}
