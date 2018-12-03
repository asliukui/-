package com.nuoyuan.retrofitframe;

import android.support.v4.view.ViewPager;

import com.ly.frame.base.BaseActivity;
import com.ly.frame.base.BaseFragment;
import com.ly.frame.base.mvp.IView;
import com.ly.frame.widget.NavigationBar;
import com.nuoyuan.retrofitframe.adapter.ViewPagerAdapter;
import com.nuoyuan.retrofitframe.home.HomeFragment;
import com.nuoyuan.retrofitframe.home.HomePresent;
import com.nuoyuan.retrofitframe.mine.DisFragment;
import com.nuoyuan.retrofitframe.mine.ListFragment;
import com.nuoyuan.retrofitframe.mine.MineFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements IView {
    private NavigationBar mNavigationBar;
    private ViewPager mViewPager;
    private ViewPagerAdapter mAdapter;

    private HomeFragment mHomeFragment;
    private MineFragment mMineFragment;
    private ListFragment mListFragment;
    private DisFragment mDisFragment;
    private List<BaseFragment> mFragments;
    public int currPageIndex = 0;

    @Override
    protected void initView() {
        initFragmentData();
        mViewPager = findViewById(R.id.activity_main_viewpager);
        mNavigationBar = findViewById(R.id.navigation_bar);
        // 设置导航栏
        setNavigationBar();
        // 设置ViewPager
        setViewPager();
    }

    private void setViewPager() {
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currPageIndex = position;
                mNavigationBar.setSelectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(currPageIndex);
    }

    private void setNavigationBar() {
        mNavigationBar.addItem("首页", R.drawable.widget_navigation_bar_home);
        mNavigationBar.addItem("列表", R.drawable.widget_navigation_bar_list);
        mNavigationBar.addItem("发现", R.drawable.widget_navigation_bar_dis);
        mNavigationBar.addItem("我的", R.drawable.widget_navigation_bar_mine);
        mNavigationBar.setListener(new NavigationBar.OnNavigationListener() {
            @Override
            public void onTabChange(int position) {
            }

            @Override
            public void onClick(int position) {
                mViewPager.setCurrentItem(position);
            }
        });
        mNavigationBar.setSelectTab(currPageIndex);
    }

    private void initFragmentData() {
        mFragments = new ArrayList<>();
        mHomeFragment = new HomeFragment();
        mListFragment = new ListFragment();
        mDisFragment = new DisFragment();
        mMineFragment = new MineFragment();
        mFragments.add(mHomeFragment);
        mFragments.add(mListFragment);
        mFragments.add(mDisFragment);
        mFragments.add(mMineFragment);
    }


    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public HomePresent initPresenter() {
        return null;
    }


}
