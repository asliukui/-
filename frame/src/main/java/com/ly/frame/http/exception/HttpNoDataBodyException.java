package com.ly.frame.http.exception;

import android.support.annotation.NonNull;

/**
 * @author lk
 * @detail Net数据解析异常
 */
public final class HttpNoDataBodyException extends Exception {
    public HttpNoDataBodyException(@NonNull String errerStr) {
        super(errerStr);
    }
}
