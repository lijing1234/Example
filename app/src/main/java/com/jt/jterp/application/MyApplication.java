package com.jt.jterp.application;

import android.app.Application;

import com.jt.jterp.crash.CustomCrash;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by admin on 2016/6/16.
 */
public class MyApplication extends Application {

    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance=this;

        //初始化崩溃日志收集器
//        CustomCrash mCustomCrash= CustomCrash.getInstance();
//        mCustomCrash.setCustomCrashInfo(getApplicationContext());

        //使用COOKIE
//        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                        //其他配置
//                .cookieJar(cookieJar)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

}
