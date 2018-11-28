package com.nuoyuan.retrofitframe.satin;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.ly.frame.base.BaseActivity;
import com.ly.frame.base.mvp.BasePresenter;
import com.nuoyuan.retrofitframe.R;

public class SatinActivity extends BaseActivity<SatinPresent> implements ISatinView {


    private TextView tv;

    @Override
    protected void initView() {
        tv = findViewById(R.id.tv);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter.requestSatin("2", "1");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_satin;
    }

    @Override
    public SatinPresent initPresenter() {
        return new SatinPresent(this);
    }

    @Override
    public void getResponseSuc(SatinResponse response) {
        tv.setText(response.toString());
    }

    @Override
    public void getErrorMsg(int code, String string) {

    }
}
