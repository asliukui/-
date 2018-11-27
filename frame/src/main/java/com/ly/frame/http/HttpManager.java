package com.ly.frame.http;

import android.content.Context;
import android.os.Environment;

import com.ly.frame.http.cookie.HttpCookieJar;
import com.ly.frame.http.interceptor.LoggerInterceptor;
import com.ly.frame.logger.LogTag;

import java.io.File;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @Author: lk
 * @Date: 2018/11/21
 * @Description:配置okhttp及retrofit
 */
public enum HttpManager {
    INSTANCE;
    /**
     * 初始化配置
     */
    // 上下文对象
    public Context context;
    // 基础URL地址
    public String base_url = "www.baidu.com";
    // Net Log 的日志Tag
    public LogTag net_log_tag = LogTag.mk("NetLog");
    // Net Log 的日志显示形式 -> 是否显示 "请求头 请求体 响应头 错误日志" 等详情
    public boolean net_log_details = true;
    // Net Log 的日志显示形式 -> 是否显示请求过程中的日志,包含详细的请求头日志
    public boolean net_log_details_all = false;
    // 非Form表单形式的请求体,是否加入公共Body
    public boolean noformbody_canaddbody = false;
    // 网络缓存默认存储路径
    public File net_cache_dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/NetCache/");
    // 网络缓存策略: 0->不启用缓存  1->遵从服务器缓存配置
    public int net_cache_type = 1;
    // 网络缓存大小(MB)
    public int net_cache_size = 10;
    // 网络连接超时时间(秒)
    public int mConnectTimeOut = 30;
    // 读取超时时间(秒)
    public int mReadTimeOut = 30;
    // 写入超时时间(秒)
    public int mWriteTimeOut = 30;

    private static Retrofit mRetrofit;
    /**
     * 请求失败重连次数
     */
    private int RETRY_COUNT = 0;
    /**
     * 初始化网络
     */
    public void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .client(createOkhttpClient())
                .baseUrl(base_url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    /**
     * 创建OkHttpClient实例
     */
    private OkHttpClient createOkhttpClient() {
        // 初始化okhttp
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 设置Log日志 -> 需在Gzip前面,否则输出信息因为Gzip压缩导致乱码
        if (net_log_details_all) {
            // 如启用此日志方式,Gzip也开启的情况下,输入日志会有乱码
            builder.addNetworkInterceptor(new LoggerInterceptor());
        } else {
            builder.addInterceptor(new LoggerInterceptor());
        }
        // 启用Gzip压缩
        // mClientBuilder.addInterceptor(new GzipInterceptor());
        // 设置缓存
        if (net_cache_type != 0) {
            // mBuilder.addNetworkInterceptor(new NetCacheInterceptor3()); // 功能尚未完成,无法使用
            builder.cache(new Cache(net_cache_dir, net_cache_size * 1024 * 1024));
        }
        // 启用cookie -> 参考http://www.jianshu.com/p/1a5f14b63f47
        // mClientBuilder.cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context)));
        builder.cookieJar(HttpCookieJar.create());
        // 失败重试
        builder.retryOnConnectionFailure(true);
        // 设置超时时间
        builder.connectTimeout(mConnectTimeOut, TimeUnit.SECONDS);
        builder.writeTimeout(mWriteTimeOut, TimeUnit.SECONDS);
        builder.readTimeout(mReadTimeOut, TimeUnit.SECONDS);
        /**
         * 忽略所有https
         */
        SSLContext sslContext = null;
        try {
//            sslContext = SSLContext.getInstance("TLS");
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }
            }}, new SecureRandom());
        } catch (KeyManagementException pE) {
            pE.printStackTrace();
        } catch (NoSuchAlgorithmException pE) {
            pE.printStackTrace();
        }
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });
        builder.sslSocketFactory(sslContext.getSocketFactory());
        /**
         * 添加https证书 - http://blog.csdn.net/sk719887916/article/details/51597816
         */
//        /**
//         * 添加证书Pinning
//         */
//        mBuilder.certificatePinner(new CertificatePinner.Builder()
//                .add("YOU API.com", "sha1/DmxUShsZuNiqPQsX2Oi9uv2sCnw=")
//                .add("YOU API..com", "sha1/SXxoaOSEzPC6BgGmxAt/EAcsajw=")
//                .add("YOU API..com", "sha1/blhOM3W9V/bVQhsWAcLYwPU6n24=")
//                .add("YOU API..com", "sha1/T5x9IXmcrQ7YuQxXnxoCmeeQ84c=")
//                .build());
//        /**
//         * 设置代理
//         */
//        mBuilder.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)));
        return builder.build();
    }

    /**
     * 获取API_SERVICE,接口服务
     * @param apiClazz
     * @param <T>
     * @return
     */
    public static <T> T request(Class<T> apiClazz) {
        if (mRetrofit==null){
            throw new NullPointerException("Retrofit is null,please init it!");
        }
        return mRetrofit.create(apiClazz);
    }

}
