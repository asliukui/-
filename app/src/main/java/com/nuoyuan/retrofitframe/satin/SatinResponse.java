package com.nuoyuan.retrofitframe.satin;

import com.ly.frame.http.BaseResponse;

import java.util.List;

/**
 * @Author: lk
 * @Date: 2018/11/27
 * @Description:
 * https://www.apiopen.top/satinGodApi?type=1&page=1
 * type=1 : 全部
 *
 * type=2 : 文字
 *
 * type=3 : 图片
 *
 * type=4 : gif
 *
 * type=5 : 视频
 *
 * page=1:页码
 */
public class SatinResponse extends BaseResponse {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * type : image
         * text : 在校友群看到这个脑筋急转弯，有人可以把我拉出困局吗？
         * username : 梁煊
         * uid : 22615830
         * header : http://qzapp.qlogo.cn/qzapp/100336987/0FF000F368B69AFC0DA33B1B6B5CA9E7/100
         * comment : 374
         * top_commentsVoiceuri : null
         * top_commentsContent : 14
         * top_commentsHeader : http://qzapp.qlogo.cn/qzapp/100336987/526D59E8CD7845869920CCA6E14CB811/100
         * top_commentsName : 小寶SJS
         * passtime : 2018-05-13 11:36:02
         * soureid : 27892813
         * up : 195
         * down : 64
         * forward : 8
         * image : http://wimg.spriteapp.cn/ugc/2018/05/11/5af57d005ed02_1.jpg
         * gif : null
         * thumbnail : http://wimg.spriteapp.cn/ugc/2018/05/11/5af57d005ed02_1.jpg
         * video : null
         */

        private String type;
        private String text;
        private String username;
        private String uid;
        private String header;
        private int comment;
        private Object top_commentsVoiceuri;
        private String top_commentsContent;
        private String top_commentsHeader;
        private String top_commentsName;
        private String passtime;
        private int soureid;
        private int up;
        private int down;
        private int forward;
        private String image;
        private Object gif;
        private String thumbnail;
        private Object video;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public Object getTop_commentsVoiceuri() {
            return top_commentsVoiceuri;
        }

        public void setTop_commentsVoiceuri(Object top_commentsVoiceuri) {
            this.top_commentsVoiceuri = top_commentsVoiceuri;
        }

        public String getTop_commentsContent() {
            return top_commentsContent;
        }

        public void setTop_commentsContent(String top_commentsContent) {
            this.top_commentsContent = top_commentsContent;
        }

        public String getTop_commentsHeader() {
            return top_commentsHeader;
        }

        public void setTop_commentsHeader(String top_commentsHeader) {
            this.top_commentsHeader = top_commentsHeader;
        }

        public String getTop_commentsName() {
            return top_commentsName;
        }

        public void setTop_commentsName(String top_commentsName) {
            this.top_commentsName = top_commentsName;
        }

        public String getPasstime() {
            return passtime;
        }

        public void setPasstime(String passtime) {
            this.passtime = passtime;
        }

        public int getSoureid() {
            return soureid;
        }

        public void setSoureid(int soureid) {
            this.soureid = soureid;
        }

        public int getUp() {
            return up;
        }

        public void setUp(int up) {
            this.up = up;
        }

        public int getDown() {
            return down;
        }

        public void setDown(int down) {
            this.down = down;
        }

        public int getForward() {
            return forward;
        }

        public void setForward(int forward) {
            this.forward = forward;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Object getGif() {
            return gif;
        }

        public void setGif(Object gif) {
            this.gif = gif;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public Object getVideo() {
            return video;
        }

        public void setVideo(Object video) {
            this.video = video;
        }
    }
}
