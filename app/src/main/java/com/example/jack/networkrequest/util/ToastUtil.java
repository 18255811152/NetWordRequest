package com.example.jack.networkrequest.util;

import android.content.Context;
import android.widget.Toast;

/**
 * 使用单利模式进行Toast
 * Created by jack on 18-5-3.
 */

public class ToastUtil {
    public static ToastUtil mToastUtil;
    public static Toast mToast;

    public ToastUtil(Context context) {
        if (null == mToast) {
            mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
    }

    public static ToastUtil getInstance(Context context) {
        if (null == mToastUtil) {
            //使用application防止內存泄露
            mToastUtil = new ToastUtil(context.getApplicationContext());
        }
        return mToastUtil;
    }

    /**
     * 彈出时间短的toast
     *
     * @param msg
     */
    public static void showShortToast(String msg) {
        if (null == mToast) {
            return;
        }
        mToast.setText(msg);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }

    /**
     * 弹出时间长的toast
     *
     * @param msg
     */
    public static void showLongToast(String msg) {
        if (null == mToast) {
            return;
        }
        mToast.setText(msg);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.show();
    }
}
