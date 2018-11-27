package com.ly.frame.configs;

import android.os.Environment;

import com.ly.frame.logger.LogTag;
import com.ly.frame.logger.LogType;

/**
 * @author lk
 * @detail 配置详细参数类
 */
public abstract class FrameConfig {
    public boolean IS_DEBUG = true;

    /**
     * 初始化
     */
    public abstract void init(boolean isDebug);

    /**
     * BASE_URL
     */
    public String BASE_URL = "";
    /**
     * 数据库 配置
     */
    // 数据库名称
    public String DB_NAME = "app_db";

    /**
     * File 存储配置
     * BASE_DIR 路径下的自定义文件夹会自动创建
     * * 详情参考FileUtils.init();
     */
    public String SDCARD_DIR = Environment.getExternalStorageDirectory().getAbsolutePath();
    // 项目主路径
    public String BASE_DIR = SDCARD_DIR + "/AppDir/";
    // Log日志默认保存路径
    public String LOG_DIR = BASE_DIR + "Logger/";

    /**
     * SharedPreferences 配置
     */
    public String SP_Cookie = "spCookie";
    protected String[] spAll = new String[]{SP_Cookie};
    /**
     * Log 配置
     */
    // Log开关
    protected boolean LOG_OPEN = IS_DEBUG;
    // Log显示等级, >= LOG_LEVEL的log显示
    protected LogType LOG_LEVEL = LogType.V;
    // Net Log 的日志Tag
    protected LogTag NET_LOG_TAG = LogTag.mk("NetLog");
    // Net Log 的日志显示形式 -> 是否显示 "请求头 请求体 响应头 错误日志" 等详情
    protected boolean NET_LOG_DETAILS = true;
    // Net Log 的日志显示形式 -> 是否显示请求过程中的日志,包含详细的请求头日志
    protected boolean NET_LOG_DETAILS_All = false;

    // 非Form表单形式的请求体,是否加入公共Body
    protected boolean NOFORMBODY_CANADDBODY = true;
    // 网络缓存策略: 0->不启用缓存  1->遵从服务器缓存配置
    protected int NET_CACHE_TYPE = 0;
    // 网络缓存大小(MB)
    protected int NET_CACHE_SIZE = 0;
    // 网络连接超时时间(秒)
    protected int CONNECT_TIMEOUT = 10;
    // 写入超时时间(秒)
    protected int WRITE_TIMEOUT = 30;
    // 读取超时时间(秒)
    protected int READ_TIMEOUT = 30;

    public abstract void initNetURL(byte urlType);
}
