package com.ly.frame.http.exception;

import android.support.annotation.NonNull;

/**
 * @author lk
 * @detail 网络总异常类
 */
public class HttpException extends Exception {

    // 异常状态码
    private int code;
    // 异常消息
    private String msg;
    // 提示用户信息
    private String toastMsg;

    public HttpException(@NonNull Throwable pThrowable, int pCode, @NonNull String pMsg, @NonNull String pToastMsg) {
        super(pThrowable);
        this.code = pCode;
        this.msg = pMsg;
        this.toastMsg = pToastMsg;
    }

    public HttpException(int pCode, @NonNull String pMsg, @NonNull String pToastMsg) {
        super(pMsg);
        this.code = pCode;
        this.msg = pMsg;
        this.toastMsg = pToastMsg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getToastMsg() {
        return toastMsg;
    }
}
