package com.ly.frame.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.ly.frame.R;
import com.ly.frame.base.mvp.BasePresenter;
import com.ly.frame.base.mvp.IView;
import com.ly.frame.logger.Logger;
import com.ly.frame.utils.net.IMonitorListener;
import com.ly.frame.utils.net.Monitor;
import com.ly.frame.utils.net.NetStatus;

/**
 * @Author: lk
 * @Date: 2018/12/3
 * @Description:
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IView {
    protected Context mContext;
    protected BaseActivity mActivity;
    protected P mPresenter;
    /**
     * view
     */
    private ProgressDialog mLoadingDialog;
    protected View mRootView;
    /**
     * toolbar
     */
    protected Toolbar mToolbar;
    private TextView mLeftTv;
    private TextView mTitleTv;
    private TextView mRightTv;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        Logger.i("onAttach(Context context)");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Logger.i("onAttach(Activity activity)");
        mActivity = (BaseActivity) activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(initContentView(), container, false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initListener();
        initData();
        mPresenter = initPresenter();
    }

    @Override
    public void onStart() {
        super.onStart();
        // 网络监控注册
        Monitor.registListener(mNetListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        // 网络监控反注册
        Monitor.unRegistListener(mNetListener);
    }

    @Override
    public void onDestroy() {
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
        mLoadingDialog = new ProgressDialog(mContext);
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

    protected <SubView extends View> SubView findView(int resId) {
        return (SubView) mRootView.findViewById(resId);
    }

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract void initView();

    protected abstract int initContentView();

    protected abstract P initPresenter();

    protected void setToolbar() {
        if (mToolbar == null) {
            mToolbar = mRootView.findViewById(R.id.toolbar);
        }
        if (mToolbar == null) {
            throw new RuntimeException("can not find toolbar, did you include in xml(toolbar_layout.xml)?");
        }
        mActivity.setSupportActionBar(mToolbar);

        if (mActivity.getSupportActionBar() == null) {
            throw new RuntimeException(
                    "actionbar not found, have you called setSupportActionBar method");
        } else {
            mActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
            mActivity.getSupportActionBar().setDisplayShowCustomEnabled(true);
        }
        mLeftTv = mActivity.findViewById(R.id.toolbar_leftTv);
        mTitleTv = mActivity.findViewById(R.id.toolbar_title);
        mRightTv = mActivity.findViewById(R.id.toolbar_rightTv);
        initBackListener();
    }

    private void initBackListener() {
        mLeftTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
    }

    public void setBackImg(int resId) {
        mLeftTv.setCompoundDrawables(getResources().getDrawable(resId), null, null, null);
        mLeftTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
    }

    public void setTitle(String title) {
        mTitleTv.setText(title);
    }

    public void setTitle(String centerTitle, String rightTitle) {
        mTitleTv.setText(centerTitle);
        mRightTv.setText(rightTitle);
    }

    protected void hideBackBtn(){
        mLeftTv.setVisibility(View.GONE);
    }
}
