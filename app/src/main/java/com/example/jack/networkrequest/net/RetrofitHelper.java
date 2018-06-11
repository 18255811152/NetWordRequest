package com.example.jack.networkrequest.net;


import com.example.jack.view.BuildConfig;
import com.example.jack.view.util.NullOnEmptyConverterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitHelper {
    private static final int DEFAULT_TIMEOUT_SECONDS = 7;
    private static final int DEFAULT_READ_TIMEOUT_SECONDS = 20;
    private static final int DEFAULT_WRITE_TIMEOUT_SECONDS = 20;
    private Retrofit mRetrofit;

    private RetrofitHelper() {
        /**
         * 获取到的域名地址
         */
//        String baseUrl = DataCenter.getInstance().getIp();
        String baseUrl = "http://ip.taobao.com/";
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);//失败重连
//.addInterceptor(new LoggingInterceptor() );
        /**
         * 获取到的domain
         */
//        String domain = DataCenter.getInstance().getDomain();
//        builder.sslSocketFactory(new TlsSniSocketFactory(domain), new SSLUtil.TrustAllManager())
//                .hostnameVerifier(new TrueHostnameVerifier(domain));
        /**
         *
         * 添加日志拦截器
         */
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        builder.addInterceptor(interceptor);  // 添加httplog
        if (mRetrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create(gson)) //添加Gson支持
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //添加RxJava支持
                    .client(builder.build()) //关联okhttp
                    .build();
        }
    }

    private static class SingletonHolder {
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }

    public static RetrofitHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }


    /**
     * 获取服务对象   Rxjava+Retrofit建立在接口对象的基础上的
     * 泛型避免强制转换
     */
    public static <T> T getService(Class<T> classz) {
        return RetrofitHelper.getInstance().mRetrofit.create(classz);
    }

//
//    private class LoggingInterceptor implements Interceptor {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Context context = BaseApplication.getContext();
//            String cookie = NetUtil.setHeaders().get("Cookie");
//            Request request = chain.request();
//            Request.Builder builder = request.newBuilder()
//                    .addHeader("Host", DataCenter.getInstance().getDomain())
//                    .addHeader("X-Requested-With", "XMLHttpRequest")
//                    .addHeader("User-Agent", NetUtil.getUserAgent(context))
//                    .addHeader("Cookie", cookie)
//                    .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
//
//
//            Map<String, String> paramsMap = getParamMap(context);
//            FormBody.Builder formBodyBuilder = new FormBody.Builder();
//            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
//                formBodyBuilder.add((String) entry.getKey(), (String) entry.getValue());
//            }
//            RequestBody formBody = formBodyBuilder.build();
//            String postBodyString = bodyToString(request.body());
//            postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(formBody);
//            builder.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), postBodyString));
//
//
//            request = builder.build();
//            //Headers header = request.headers();
////            if (BuildConfig.DEBUG) {
////                LogUtils.i(String.format("发送请 求 %s on %s%n%s", request.url(), chain.connection(), request.headers()));
////            }
//
//
//            return processResponse(chain.proceed(request));
//        }
//
//        //访问网络之后，处理Response(这里没有做特别处理)
//        private Response processResponse(Response response) {
//            int statusCode = response.code();
//            if (statusCode != 200) {
//                throw new CustomHttpException(statusCode);
//            }
//            return response;
//        }
//
//        private Map<String, String> getParamMap(Context context) {
//            Map paraMap = new HashMap();
//
//            SysInfo sysInfo = DataCenter.getInstance().getSysInfo();
//            paraMap.put("terminal", sysInfo.getTerminal());
//            paraMap.put("version", sysInfo.getVersionName());
//            paraMap.put("theme", sysInfo.getTheme());
//            paraMap.put("resolution", sysInfo.getResolution());
//            paraMap.put("is_native", sysInfo.getIs_native());
//            paraMap.put("locale", sysInfo.getLocale());
//
//            return paraMap;
//        }
//
//        private String bodyToString(final RequestBody request) {
//            try {
//                final RequestBody copy = request;
//                final Buffer buffer = new Buffer();
//                if (copy != null)
//                    copy.writeTo(buffer);
//                else
//                    return "";
//                return buffer.readUtf8();
//            } catch (final IOException e) {
//                return "did not work";
//            }
//        }
//    }


}
