package com.ly.frame.http;

import java.io.Serializable;

/**
 * @Author: lk
 * @Date: 2018/11/22
 * @Description:
 */
public class BaseResponse implements Serializable {
    public int code;
    public String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
