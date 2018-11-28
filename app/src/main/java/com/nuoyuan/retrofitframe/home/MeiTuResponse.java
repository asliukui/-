package com.nuoyuan.retrofitframe.home;

import com.ly.frame.http.BaseResponse;

import java.util.List;

/**
 * @Author: lk
 * @Date: 2018/11/15
 * @Description:
 * 美图接口
 * https://www.apiopen.top/meituApi?page=1
 */
public class MeiTuResponse extends BaseResponse {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createdAt : 2017-11-23T08:32:29.546Z
         * publishedAt : 2017-11-24T11:08:03.624Z
         * type : 美图
         * url : http://7xi8d6.com1.z0.glb.clouddn.com/20171123083218_5mhRLg_sakura.gun_23_11_2017_8_32_9_312.jpeg
         */

        private String createdAt;
        private String publishedAt;
        private String type;
        private String url;

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "createdAt='" + createdAt + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MeiTuResponse{" +
                "data=" + data +
                '}';
    }
}
