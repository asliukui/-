package com.nuoyuan.retrofitframe;

import android.view.View;
import android.widget.TextView;

import com.ly.frame.base.BaseActivity;
import com.nuoyuan.retrofitframe.home.HomePresent;
import com.nuoyuan.retrofitframe.home.IHomeView;
import com.nuoyuan.retrofitframe.home.TranslationResponse;


public class MainActivity extends BaseActivity<HomePresent> implements IHomeView {

    private TextView mTv;

    @Override
    protected void initView() {
        mTv = findViewById(R.id.tv);
    }

    @Override
    protected void initListener() {
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.requestData();
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.requestData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public HomePresent initPresenter() {
        return new HomePresent(this);
    }

    @Override
    public void getResponseSuc(TranslationResponse response) {
        mTv.setText(response.toString());
    }

    @Override
    public void getErrorMsg(int code, String string) {

    }
}
