package com.ly.frame.base;

import android.app.Application;
import android.os.Build;
import android.os.Debug;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.ly.frame.configs.ConfigBuilder;
import com.ly.frame.configs.FrameConfig;
import com.ly.frame.logger.LogTag;
import com.ly.frame.logger.Logger;
import com.ly.frame.utils.ActivityStackManager;
import com.ly.frame.utils.CrashUtils;
import com.ly.frame.utils.DateUtils;
import com.ly.frame.utils.FileUtils;
import com.ly.frame.utils.net.Monitor;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @Author: lk
 * @Description:
 */
public class FrameApplication extends Application {

    // 单例实例
    private static FrameApplication sInstance = null;

    public FrameApplication() {
        sInstance = this;
    }

    public static FrameApplication instance() {
        if (sInstance == null) {
            new RuntimeException("FrameApplication == null ?? you should extends FrameApplication in you app");
        }
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new MyActivityLifeCallBackImpl());
    }

    public void initFrameSetting(@NonNull Class<? extends FrameConfig> pConfig, @NonNull boolean isDebug){
        // 创建配置Info
        ConfigBuilder.createConfig(pConfig, isDebug);
        // 初始化模块配置
        ConfigBuilder.init();
        // 初始化File配置
        FileUtils.init();
        // 初始化网络监听配置
        Monitor.init();
        // 崩溃异常捕获
        CrashUtils.CrashManager.getInstance(instance());
    }

    /**
     * 获取配置详情
     */
    public final <T extends FrameConfig> T getConfig() {
        return ConfigBuilder.getConfigInfo();
    }

    /**
     * 退出应用程序
     */
    public final void exit() {
        ActivityStackManager.INSTANCE.exit();
    }

    /**
     * 程序发生崩溃异常时回调
     */
    @CallSuper
    public void crashException(@NonNull String projectInformation, @NonNull Thread pThread, @NonNull Throwable pE) {
        String fileName = "crash-" + DateUtils.nowDate(DateUtils.yMdHms2) + ".txt";
        StringBuffer sb = new StringBuffer();
        sb.append("项目信息============================================\n");
        sb.append(projectInformation);
        sb.append("\n机型信息============================================\n");
        Field[] fields = Build.class.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                sb.append(field.getName()).append(" = ").append(field.get("").toString()).append("\n");
            }
        } catch (IllegalAccessException pE1) {
            Logger.e("crashException:" + pE1.getMessage());
        }
        Logger.file(LogTag.mk("crashException"), fileName, pE, sb.toString());
        // 内存溢出类型崩溃,生成.hprof文件
        // crashOutOfMemory(pE, fileName.replace(".txt", ".hprof"));
    }

    /**
     * 保存内存溢出日志文件 - 由于文件较大,会造成app崩溃时卡顿,所以暂时关闭此功能
     */
    private final void crashOutOfMemory(@NonNull Throwable pE, @NonNull String fileName) {
        boolean result = false;
        if (OutOfMemoryError.class.equals(pE.getClass())) {
            result = true;
        } else {
            Throwable cause = pE.getCause();
            while (null != cause) {
                if (OutOfMemoryError.class.equals(cause.getClass())) {
                    result = true;
                }
                cause = cause.getCause();
            }
        }
        if (result) {
            try {
                Debug.dumpHprofData(getConfig().LOG_DIR + fileName);
            } catch (IOException pE1) {
                Logger.e("crashException:" + pE1.getMessage());
            }
        }
    }

    @CallSuper
    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        super.onTerminate();
        // 终止网络监听
        Monitor.release();
    }
}
