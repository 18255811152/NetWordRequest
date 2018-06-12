package com.example.jack.networkrequest.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.support.v4.net.ConnectivityManagerCompat;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 网络连接工具
 */
public final class NetTool {

    /**
     * 检查当前WIFI是否连接，两层意思——是否连接，连接是不是WIFI
     *
     * @return true表示当前网络处于连接状态，且是WIFI，否则返回false
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnected() && ConnectivityManager.TYPE_WIFI == info.getType();
    }

    /**
     * 检查当前移动网络是否连接，两层意思——是否连接，连接是不是GPRS
     *
     * @return true表示当前网络处于连接状态，且是GPRS，否则返回false
     */
    public static boolean isMobileConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnected() && ConnectivityManager.TYPE_MOBILE == info.getType();
    }

    /**
     * 检查当前是否连接
     *
     * @return true表示当前网络处于连接状态，否则返回false
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    /**
     * 对大数据传输时，需要调用该方法做出判断，如果流量敏感，应该提示用户
     *
     * @return true表示流量敏感，false表示不敏感
     */
    public static boolean isActiveNetworkMetered(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return ConnectivityManagerCompat.isActiveNetworkMetered(cm);
    }

    public static Intent registerReceiver(Context context, ConnectivityChangeReceiver receiver) {
        return context.registerReceiver(receiver,
                ConnectivityChangeReceiver.FILTER);
    }

    public static void unregisterReceiver(Context context, ConnectivityChangeReceiver receiver) {
        context.unregisterReceiver(receiver);
    }

    public static abstract class ConnectivityChangeReceiver extends BroadcastReceiver {
        public static final IntentFilter FILTER = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);

        @Override
        public final void onReceive(Context context, Intent intent) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo gprsInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            // 判断是否是Connected事件
            boolean wifiConnected = false;
            boolean gprsConnected = false;
            if (wifiInfo != null && wifiInfo.isConnected()) {
                wifiConnected = true;
            }
            if (gprsInfo != null && gprsInfo.isConnected()) {
                gprsConnected = true;
            }
            if (wifiConnected || gprsConnected) {
                onConnected();
                return;
            }

            // 判断是否是Disconnected事件，注意：处于中间状态的事件不上报给应用！上报会影响体验
            boolean wifiDisconnected = false;
            boolean gprsDisconnected = false;
            if (wifiInfo == null || wifiInfo.getState() == State.DISCONNECTED) {
                wifiDisconnected = true;
            }
            if (gprsInfo == null || gprsInfo.getState() == State.DISCONNECTED) {
                gprsDisconnected = true;
            }
            if (wifiDisconnected && gprsDisconnected) {
                onDisconnected();
            }
        }

        protected abstract void onDisconnected();

        protected abstract void onConnected();
    }

    /**
     * 功能：检测当前URL是否可连接或是否有效
     *
     * @param domain 指定URL网络地址
     * @return URL
     */
    public static boolean isStop;

    public static boolean isConnect(String domain) {
        long t1 = System.currentTimeMillis();
        if (!TextUtils.isEmpty(domain)) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(httpUrl(domain) + "/__check").get().build();
                Log.i("------>", domain);
                if (!isStop) {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        isStop = true;
                        Log.d("TIME", domain + "：检测成功" + response.message());
                        return true;
                    } else {
                        Log.d("TIME", domain + "：检测失败");
                    }
                } else {
                    Log.d("TIME", "取消检测");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public static String httpUrl(String url) {
        if (!TextUtils.isEmpty(url) && !url.contains("http")) {
            url = String.format("http://%s", url);
        }
        return url;
    }

    public static Map<String, String> setHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Requested-With", "XMLHttpRequest");
        headers.put("User-Agent", "app_android;Android");
        return headers;
    }


}
