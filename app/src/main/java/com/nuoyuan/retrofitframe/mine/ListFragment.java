package com.nuoyuan.retrofitframe.mine;

import com.ly.frame.base.BaseFragment;
import com.nuoyuan.retrofitframe.R;
import com.nuoyuan.retrofitframe.home.HomePresent;
import com.nuoyuan.retrofitframe.home.IHomeView;
import com.nuoyuan.retrofitframe.home.MeiTuResponse;

/**
 * @Author: lk
 * @Date: 2018/12/3
 * @Description:
 */
public class ListFragment extends BaseFragment<HomePresent> implements IHomeView {
    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int initContentView() {
        return R.layout.fragment_home_layout;
    }

    @Override
    protected HomePresent initPresenter() {
        return new HomePresent(this);
    }

    @Override
    public void getResponseSuc(MeiTuResponse response) {

    }

    @Override
    public void getErrorMsg(int code, String string) {

    }
}
