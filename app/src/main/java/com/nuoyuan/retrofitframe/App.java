package com.nuoyuan.retrofitframe;

import com.ly.frame.base.FrameApplication;
import com.nuoyuan.retrofitframe.config.CustemConfig;

/**
 * @Author: lk
 * @Date: 2018/11/9
 * @Description:
 */
public class App extends FrameApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        initFrameSetting(CustemConfig.class,BuildConfig.DEBUG);
    }
}
