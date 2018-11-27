package com.ly.frame.http.exception;

import android.support.annotation.NonNull;

/**
 * @author lk
 * @detail Net数据无匹配类型异常
 */
public final class HttpNoDataTypeException extends Exception {
    public HttpNoDataTypeException(@NonNull String errerStr) {
        super(errerStr);
    }
}
