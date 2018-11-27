package com.ly.frame.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author lk
 * @detail Activity堆栈管理器
 */
public enum ActivityStackManager {
    INSTANCE;

    private static Stack<Activity> activityStack = new Stack<>();

    /**
     * 获取当前Activity
     */
    public Activity currentActivity() {
        return activityStack.lastElement();
    }

    /**
     * 判断Activity是否在栈顶
     */
    public boolean isCurrentActivity(@NonNull Activity activity) {
        return activity == currentActivity();
    }

    /**
     * 判断Activity是否在栈顶 -> android系统Activity管理栈
     */
    public boolean isSysCurrentActivity(@NonNull Activity activity) {
        ActivityManager manager = (ActivityManager) activity.getSystemService(activity.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
        if (runningTaskInfos != null) {
            return runningTaskInfos.get(0).topActivity.getClassName().equals(activity.getClass().getName());
        }
        return false;
    }

    /**
     * 是否包含Activity
     */
    public boolean containsActivity(@NonNull Class<? extends Activity> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据class,获取Activity实例
     */
    public List<Activity> getActivitys(@NonNull Class<? extends Activity> cls) {
        List<Activity> activities = new ArrayList<>();
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                activities.add(activity);
            }
        }
        return activities;
    }

    /**
     * 获取前一个Activity
     */
    public Activity prevActivity(@NonNull Activity activity) {
        if (activity != null && activityStack.size() > 0 && activityStack.contains(activity) && activityStack.firstElement() != activity) {
            return activityStack.get(activityStack.lastIndexOf(activity) - 1);
        }
        return null;
    }

    /**
     * 获取下一个Activity
     */
    public Activity nextActivity(@NonNull Activity activity) {
        if (activity != null && activityStack.size() > 0 && activityStack.contains(activity) && activityStack.lastElement() != activity) {
            return activityStack.get(activityStack.lastIndexOf(activity) + 1);
        }
        return null;
    }

    /**
     * 结束当前Activity
     */
    public synchronized void finishActivity() {
        finishActivity(true);
    }

    public synchronized void finishActivity(boolean styleAnimat) {
        Activity activity = activityStack.lastElement();
        finishActivity(activity, styleAnimat);
    }

    /**
     * 结束一个Activity
     */
    public synchronized void finishActivity(@NonNull Activity activity) {
        finishActivity(activity, true);
    }

    public synchronized void finishActivity(@NonNull Activity activity, boolean styleAnimat) {
        if (activity != null) {
            removeActivity(activity);
            activity.finish();
            if (!styleAnimat) {
                activity.overridePendingTransition(0, 0);
            }
        }
    }

    /**
     * 结束一个Activity,根据class
     */
    public synchronized void finishActivity(@NonNull Class<? extends Activity> cls) {
        finishActivity(cls, true);
    }

    public synchronized void finishActivity(@NonNull Class<? extends Activity> cls, boolean styleAnimat) {
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            if (activityStack.get(i).getClass().equals(cls)) {
                finishActivity(activityStack.get(i), styleAnimat);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public synchronized void finishAllActivity() {
        finishAllActivity(true);
    }

    public synchronized void finishAllActivity(boolean styleAnimat) {
        for (Activity activity : activityStack) {
            if (null != activity) {
                activity.finish();
                if (!styleAnimat) {
                    activity.overridePendingTransition(0, 0);
                }
            }
        }
        activityStack.clear();
    }

    /**
     * 添加一个Activity
     */
    protected synchronized void addActivity(@NonNull Activity activity) {
        if (activity != null) {
            activityStack.add(activity);
        }
    }

    /**
     * 移除一个Activity
     */
    protected synchronized void removeActivity(@NonNull Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 退出应用程序 -> 杀进程
     */
    public synchronized void exit() {
        exit2();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 退出应用程序 -> 不杀进程,只是关掉所有Activity
     */
    public synchronized void exit2() {
        finishAllActivity(false);
    }
}
