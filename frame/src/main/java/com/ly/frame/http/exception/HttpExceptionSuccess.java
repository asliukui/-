package com.ly.frame.http.exception;

import android.support.annotation.NonNull;

/**
 * @author lk
 * @detail Net数据正常返回
 */
public class HttpExceptionSuccess extends HttpException {
    public HttpExceptionSuccess(int pCode, @NonNull String pMsg, @NonNull String pToastMsg) {
        super(pCode, pMsg, pToastMsg);
    }
}
