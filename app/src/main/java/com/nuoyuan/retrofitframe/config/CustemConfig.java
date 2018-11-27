package com.nuoyuan.retrofitframe.config;

import com.ly.frame.configs.FrameConfig;
import com.nuoyuan.retrofitframe.http.ApiService;

/**
 * @author lk
 * @detail 根据自己的项目，来配置文件
 */
public final class CustemConfig extends FrameConfig {

    public String SP_Device = "spDevice";
    public String SP_Config = "spConfig";
    public String SP_User = "spUser";

    @Override
    public void init(boolean isDebug) {
        IS_DEBUG = isDebug;
        BASE_DIR = SDCARD_DIR + "/AppDir/";
        LOG_DIR = BASE_DIR + "Logger/";
        spAll = new String[]{SP_Cookie, SP_Device, SP_Config, SP_User};
        //TODO urlType:本地缓存的运行环境，可通过sp完成功能
        byte urlType = -1;
        initNetURL(isDebug ? urlType : -1);
    }

    @Override
    public void initNetURL(byte urlType) {
        switch (urlType) {
            case -1:
                //正式版
                BASE_URL = ApiService.BASE_URL;
                break;
            case 0:
                //预发布
                BASE_URL = "http://www.baidu.com";
                break;
            case 1:
                //测试1
                BASE_URL = "http://www.baidu.com";
                break;
            default:
                //正式版
                BASE_URL = "http://www.baidu.com";
                break;
        }
    }
}
