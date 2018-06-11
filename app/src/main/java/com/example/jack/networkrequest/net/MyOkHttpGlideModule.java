package com.example.jack.networkrequest.net;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.example.jack.networkrequest.util.SSLUtil;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


@GlideModule
public class MyOkHttpGlideModule extends AppGlideModule {
    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(7, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .followRedirects(false)
            .sslSocketFactory(new TlsSniSocketFactory(), new SSLUtil.TrustAllManager())
            .hostnameVerifier(new TrueHostnameVerifier())
            .build();

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(client);
        registry.replace(GlideUrl.class, InputStream.class, factory);
    }

    //isManifestParsingEnabled 设置清单解析，设置为false，避免添加相同的modules两次
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
