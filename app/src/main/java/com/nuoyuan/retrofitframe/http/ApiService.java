package com.nuoyuan.retrofitframe.http;

import com.nuoyuan.retrofitframe.home.MeiTuResponse;
import com.nuoyuan.retrofitframe.satin.SatinResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Author: lk
 * @Date: 2018/11/9
 * @Description:
 *
 */
public interface ApiService {
//    @Url
//    替换url
//    @QueryMap 替换url中查询参数
//    @Header
//    替换header
//    @FieldMap 替换post请求body中参数
//    @FormUrlEncoded
//    post请求需要加的方法注解
//    @POST() 标示该方法为post请求
//    @GET() 标示该方法为get请求

    String BASE_URL ="https://www.apiopen.top/";

    /**
     * 美图
     */
    @GET("meituApi")
    Observable<MeiTuResponse> getPic(@Query("page") String page);

    /**
     * https://www.apiopen.top/satinApi?type=1&page=1
     * type=1 : 全部
     * type=2 : 文字
     * type=3 : 图片
     * type=4 : 视频
     * page=1:页码
     * type=41 : 视频
     * type=10 : 图片
     * type=29 : 段子
     * type=31 : 声音
     */
    @GET("satinApi")
    Observable<SatinResponse> getSatin(@Query("type") String type,@Query("page") String page);
}
