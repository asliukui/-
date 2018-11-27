package com.nuoyuan.retrofitframe.http;

import com.nuoyuan.retrofitframe.home.TranslationResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;

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

//    String BASE_URL ="http://www.izaodao.com/Api/";
    String BASE_URL ="http://fanyi.youdao.com/";
    @POST("AppFiftyToneGraph/videoLink")
    void postData();

    /**
     * get方法，有道基础url
     */
    @GET("openapi.do?keyfrom=Yanzhikai&key=2032414398&type=data&doctype=json&version=1.1&q=car")
    Observable<TranslationResponse> getYDaoData();
}
