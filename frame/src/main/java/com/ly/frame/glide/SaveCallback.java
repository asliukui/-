package com.ly.frame.glide;

import android.support.annotation.NonNull;

import java.io.File;

/**
 * @author lk
 * @detail 保存图片的回调接口
 */
public interface SaveCallback {

    void succeed(@NonNull final File file);

    void failed(@NonNull final Exception pE);
}
