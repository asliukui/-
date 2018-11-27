package com.ly.frame.http.exception;

import android.support.annotation.NonNull;

/**
 * @author lk
 * @detail Net数据异常 -> code无匹配
 */
public final class HttpExceptionUnknownCode extends HttpException {
    public HttpExceptionUnknownCode(int pCode, @NonNull String pMsg, @NonNull String pToastMsg) {
        super(pCode, pMsg, pToastMsg);
    }
}