package com.nuoyuan.retrofitframe;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.ly.frame.base.BaseActivity;
import com.nuoyuan.retrofitframe.home.HomePresent;
import com.nuoyuan.retrofitframe.home.IHomeView;
import com.nuoyuan.retrofitframe.home.MeiTuResponse;
import com.nuoyuan.retrofitframe.satin.SatinActivity;


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
                startActivity(new Intent(mContext,SatinActivity.class));
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
    public void getResponseSuc(MeiTuResponse response) {
        mTv.setText(response.toString());
    }

    @Override
    public void getErrorMsg(int code, String string) {

    }
}
