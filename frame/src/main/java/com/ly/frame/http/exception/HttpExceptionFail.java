package com.ly.frame.http.exception;

import android.support.annotation.NonNull;

/**
 * @author lk
 * @detail Net数据异常返回
 */
public class HttpExceptionFail extends HttpException {
    public HttpExceptionFail(int pCode, @NonNull String pMsg, @NonNull String pToastMsg) {
        super(pCode, pMsg, pToastMsg);
    }
}
