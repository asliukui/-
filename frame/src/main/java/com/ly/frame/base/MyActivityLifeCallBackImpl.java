package com.ly.frame.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.ly.frame.utils.ActivityStackManager;

/**
 * @Author: lk
 * @Date: 2018/11/27
 * @Description:
 */
public class MyActivityLifeCallBackImpl implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        ActivityStackManager.INSTANCE.addActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        ActivityStackManager.INSTANCE.removeActivity(activity);
    }
}
