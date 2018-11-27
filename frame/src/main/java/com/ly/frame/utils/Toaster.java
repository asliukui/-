package com.ly.frame.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ly.frame.R;
import com.ly.frame.base.FrameApplication;

/**
 * @Author: lk
 * @Date: 2018/11/21
 * @Description:
 */
public class Toaster {

    private static Toast toast;

    public static Toast show(String content) {
        LayoutInflater inflate = (LayoutInflater)
                FrameApplication.instance().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(R.layout.toast_main, null);
        TextView tv = (TextView) v.findViewById(R.id.toast_content);
        tv.setText(content);
        if (toast == null) {
            toast = new Toast(FrameApplication.instance());
        }
        toast.setView(v);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        return toast;
    }
}
