package com.ly.frame.http.exception;

import android.support.annotation.NonNull;

import java.io.IOException;

/**
 * @author lk
 * @detail Net响应异常
 */
public final class HttpNoWorkException extends IOException {
    public HttpNoWorkException(@NonNull String errerStr) {
        super(errerStr);
    }
}
