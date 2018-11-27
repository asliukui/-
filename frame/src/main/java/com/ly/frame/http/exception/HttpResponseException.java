package com.ly.frame.http.exception;

import android.support.annotation.NonNull;

/**
 * @author lk
 * @detail Net响应异常
 */
public final class HttpResponseException extends Exception {
    // 异常状态码
    private int code;

    public HttpResponseException(int pCode, @NonNull String errerStr) {
        super(errerStr);
        this.code = pCode;
    }

    public int getCode() {
        return code;
    }
}
