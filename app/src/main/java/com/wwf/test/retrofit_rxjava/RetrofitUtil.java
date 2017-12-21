package com.wwf.test.retrofit_rxjava;

import android.text.TextUtils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.wwf.test.retrofit_rxjava.Constant.baseUrl;

/**
 * Created by wwf on 2017/12/21.
 */
public class RetrofitUtil {
    private static RetrofitUtil sRetrofitUtil;
    private static String mBaseUrl;

    private RetrofitUtil() {
    }

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
        return createRetrofit(baseUrl).create(ApiService.class);
    }

    public static class Builder {

        public Builder setBaseUrl(String baseUrl) {
            RetrofitUtil.mBaseUrl = baseUrl;
            return this;
        }

        public Builder() {
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

        public ApiService getApiService() {
            if (!TextUtils.isEmpty(mBaseUrl)) {
                return createRetrofit(mBaseUrl).create(ApiService.class);
            } else {
                return createRetrofit(Constant.baseUrl).create(ApiService.class);
            }
        }

        public RetrofitUtil build() {
            sRetrofitUtil = new RetrofitUtil();
            return sRetrofitUtil;
        }
    }
}
