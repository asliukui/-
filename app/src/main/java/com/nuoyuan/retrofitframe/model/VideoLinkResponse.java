package com.nuoyuan.retrofitframe.model;

import com.ly.frame.http.BaseResponse;

/**
 * @Author: lk
 * @Date: 2018/11/9
 * @Description:
 */
public class VideoLinkResponse extends BaseResponse {
    public Data data;

    public static class Data {
        public String name;
        public String title;
    }
}
