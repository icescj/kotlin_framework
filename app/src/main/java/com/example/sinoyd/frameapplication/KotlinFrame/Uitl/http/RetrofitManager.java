package com.example.sinoyd.frameapplication.KotlinFrame.Uitl.http;


import com.example.sinoyd.frameapplication.KotlinFrame.MyApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 *
 */

public class RetrofitManager {

    private static volatile OkHttpClient mOkHttpClient;
    private static Cache cache = new Cache(MyApplication.getInstance().getCacheDir(), 1024 * 1024 * 10);//缓存10mib

    private static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (mOkHttpClient == null) {
                    mOkHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(new OkHttpInterceptor())
                            .connectTimeout(60, TimeUnit.SECONDS)//连接超时时间
                            .readTimeout(10, TimeUnit.SECONDS)//读取超时时间
                            .writeTimeout(10, TimeUnit.SECONDS)//写入超时时间
                            .cache(cache)
                            .build();
                }
            }
        }
        return mOkHttpClient;
    }


    public static <T> T create(Class<T> clazz) {

        Retrofit retrofit = new Retrofit.Builder()
                .client(getOkHttpClient())
                .baseUrl(ApiService.Companion.getBASEURL())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(clazz);

    }

}
