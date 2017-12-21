package com.wwf.test.retrofit_rxjava;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wwf on 2017/12/21.
 */
public class RetrofitUtil {
    public static RetrofitUtil sRetrofitUtil;
    public static RetrofitUtil getInstance() {
        if (sRetrofitUtil == null) {
            sRetrofitUtil = new RetrofitUtil();
        }
        return sRetrofitUtil;
    }
    private Retrofit createRetrofit(String baseUrl) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(9, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        return new Retrofit.Builder().baseUrl(baseUrl)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    //动态设置baseUrl
    public ApiService getApiService(String baseUrl) {
        return createRetrofit(baseUrl).create(ApiService.class);
    }
    //使用相同的baseUrl, Constant.baseUrl
    public ApiService getApiService() {
        return createRetrofit(Constant.baseUrl).create(ApiService.class);
    }
}
