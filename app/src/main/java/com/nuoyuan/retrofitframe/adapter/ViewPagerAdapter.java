package com.nuoyuan.retrofitframe.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ly.frame.base.BaseFragment;

import java.util.List;

/**
 * @Author: lk
 * @Date: 2018/12/3
 * @Description:
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mFragments;

    public ViewPagerAdapter(FragmentManager pFragmentManager, List<BaseFragment> pFragments) {
        super(pFragmentManager);
        this.mFragments = pFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
