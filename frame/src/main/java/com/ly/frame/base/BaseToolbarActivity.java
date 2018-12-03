package com.ly.frame.base;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ly.frame.R;
import com.ly.frame.base.mvp.BasePresenter;

/**
 * @Author: lk
 * @Date: 2018/12/3
 * @Description:
 */
public abstract class BaseToolbarActivity<P extends BasePresenter> extends BaseActivity {
    protected Toolbar mToolbar;
    private TextView mLeftTv;
    private TextView mTitleTv;
    private TextView mRightTv;


    @Override
    protected void initToolbar() {
        if (mToolbar == null) {
            mToolbar = findViewById(R.id.toolbar);
        }
        if (mToolbar == null) {
            throw new RuntimeException("can not find toolbar, did you include in xml(toolbar_layout.xml)?");
        }
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() == null) {
            throw new RuntimeException(
                    "actionbar not found, have you called setSupportActionBar method");
        } else {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayShowCustomEnabled(true);
        }
        mLeftTv = findViewById(R.id.toolbar_leftTv);
        mTitleTv = findViewById(R.id.toolbar_title);
        mRightTv = findViewById(R.id.toolbar_rightTv);
        initBackListener();
    }

    private void initBackListener() {
        mLeftTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setBackImg(int resId) {
        mLeftTv.setCompoundDrawables(getResources().getDrawable(resId), null, null, null);
        mLeftTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
