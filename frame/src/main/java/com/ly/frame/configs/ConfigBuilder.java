package com.ly.frame.configs;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.DecodeFormat;
import com.ly.frame.base.FrameApplication;
import com.ly.frame.glide.GlideManager;
import com.ly.frame.http.HttpManager;
import com.ly.frame.logger.Logger;
import com.ly.frame.sptool.SpEncodeDecodeCallback;
import com.ly.frame.sptool.SpManager;
import com.ly.frame.utils.FileUtils;
import com.ly.frame.utils.code.EncodeDecode;

import java.io.File;
import java.io.IOException;

/**
 * @author lk
 * @detail 构建配置
 */
public final class ConfigBuilder {
    // 配置详细参数类
    private static FrameConfig sConfigInfo;

    /**
     * 创建配置详情
     */
    public static void createConfig(@NonNull Class<? extends FrameConfig> pConfig, @NonNull boolean isDebug) {
        try {
            sConfigInfo = pConfig.newInstance();
            sConfigInfo.init(isDebug);
        } catch (InstantiationException pE) {
            pE.printStackTrace();
        } catch (IllegalAccessException pE) {
            pE.printStackTrace();
        }
    }

    /**
     * 获取配置详情
     */
    public static <T extends FrameConfig> T getConfigInfo() {
        if (sConfigInfo != null) {
            return (T) sConfigInfo;
        }
        return null;
    }

    /**
     * 初始化 -> BaseApplication中调用
     * * 基础组件配置
     */
    public static void init() {
        if (sConfigInfo == null) {
            throw new NullPointerException("ConfigInfo is null");
        }
        Context context = FrameApplication.instance().getApplicationContext();

        //------------------------------------Logger配置---------------------------------
        Logger.APP_PKG_NAME = context.getPackageName();
        Logger.LOG_OPEN = sConfigInfo.LOG_OPEN;
        Logger.LOG_LEVEL = sConfigInfo.LOG_LEVEL;
        Logger.LOGFILE_PATH = sConfigInfo.LOG_DIR;
        //------------------------------------SpTool配置---------------------------------
        // 设置全局Sp实例,项目启动时创建,并通过getCommonSp拿到,项目中只有一份实例
        SpManager.CommonSp = sConfigInfo.spAll;
        // 加解密回调 - 不设置或null表示不进行加解密处理
        SpManager.setEncodeDecodeCallback(new SpEncodeDecodeCallback() {
            @Override
            public String encode(String pStr) {
                String result = null;
                try {
                    result = EncodeDecode.encode(pStr);
                } catch (IOException pE) {
                    pE.printStackTrace();
                }
                return result;
            }

            @Override
            public String decode(String pStr) {
                String result = null;
                try {
                    result = EncodeDecode.decode(pStr);
                } catch (IOException pE) {
                    pE.printStackTrace();
                } catch (ClassNotFoundException pE) {
                    pE.printStackTrace();
                }
                return result;
            }
        });
        // * 必须设置,否则无法使用
        SpManager.setContext(context);
        //------------------------------------Retrofit配置---------------------------------
        // * 必须设置,否则无法使用
        HttpManager.INSTANCE.context = context;
        // * 必须设置,否则无法使用
        HttpManager.INSTANCE.base_url = sConfigInfo.BASE_URL;
        HttpManager.INSTANCE.net_log_tag = sConfigInfo.NET_LOG_TAG;
        HttpManager.INSTANCE.net_log_details = sConfigInfo.NET_LOG_DETAILS;
        HttpManager.INSTANCE.net_log_details_all = sConfigInfo.NET_LOG_DETAILS_All;
        HttpManager.INSTANCE.noformbody_canaddbody = sConfigInfo.NOFORMBODY_CANADDBODY;
        HttpManager.INSTANCE.net_cache_dir = new File(FrameApplication.instance().getCacheDir(), "NetCache");
        HttpManager.INSTANCE.net_cache_type = sConfigInfo.NET_CACHE_TYPE;
        HttpManager.INSTANCE.net_cache_size = sConfigInfo.NET_CACHE_SIZE;
        HttpManager.INSTANCE.mConnectTimeOut = sConfigInfo.CONNECT_TIMEOUT;
        HttpManager.INSTANCE.mReadTimeOut = sConfigInfo.READ_TIMEOUT;
        HttpManager.INSTANCE.mWriteTimeOut = sConfigInfo.WRITE_TIMEOUT;
        HttpManager.INSTANCE.initRetrofit();
        //------------------------------------Glide配置---------------------------------
        GlideManager.DECODEFORMAT = DecodeFormat.PREFER_ARGB_8888;
        GlideManager.IMAGE_CACHE_PATH = FileUtils.imgCacheDirPath;
        GlideManager.MAIN_CACHE_DIR = "MainCache";
        GlideManager.MAX_DISK_CACHE_SIZE = 50;
    }
}
