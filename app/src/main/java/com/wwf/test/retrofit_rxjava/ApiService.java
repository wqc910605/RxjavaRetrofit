package com.wwf.test.retrofit_rxjava;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Dell on 2017/12/21.
 */

public interface ApiService {
    /**
     * @param url 全路径, 和baseURL没有关系, baseUrl随便设置, 但是要有意义, 是一个真实的网址
     * @return
     */
    @GET
    Observable<BaseBean<String>> get(@Url String url);

    /**
     * 例如: https://snail-stg1.zysnail.com/snail/queryMyHomePage.do?sid=1&userId=123456&homepageId=SY00000001&os=IOS&versionNumber=100
     * @param path1  代表snail
     * @param path2  代表selectLiveStatus.do
     * @return
     */
    @GET("{path1}/{path2}")
    Observable<BaseBean<String>> get(@Path("path1") String path1, @Path("path2") String path2);

    /**
     * 例如: https://snail-stg1.zysnail.com/snail?sid=1&userId=123456&homepageId=SY00000001&os=IOS&versionNumber=100
     * @param path1  代表snail
     * @param parmas ?后的请求参数 是一个map集合
     * @return
     */
    @GET("{path1}")
    Observable<BaseBean<String>> get(@Path("path1") String path1, @QueryMap Map<String, String> parmas);
    /**
     * 例如: https://snail-stg1.zysnail.com/snail/queryMyHomePage.do?sid=1&userId=123456&homepageId=SY00000001&os=IOS&versionNumber=100
     * @param path1  代表snail
     * @param path2  代表selectLiveStatus.do
     * @param parmas ?后的请求参数 是一个map集合
     * @return
     */
    @GET("{path1}/{path2}")
    Observable<BaseBean<String>> get(@Path("path1") String path1, @Path("path2") String path2, @QueryMap Map<String, String> parmas);

    //post请求

    /**
     * 参数含义详情, 请看对应的get请求
     * @param path1
     * @param path2
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("{path1}/{path2}")
    Observable<BaseBean<String>> post(@Path("path1") String path1, @Path("path2") String path2, @FieldMap Map<String, String> params);
    @FormUrlEncoded
    @POST("{path1}")
    Observable<BaseBean<String>> post(@Path("path1") String path1, @FieldMap Map<String, String> params);
    ///snail/autoLogin.do?sid=1&userId=123456

    /**
     * 参数含义详情, 请看对应的get请求
     * @param path1
     * @param path2
     * @param loginBean 用来封装请求参数的bean
     * @return
     */
    @POST("{path1}/{path2}")
    Observable<BaseBean<String>> post(@Path("path1") String path1, @Path("path2") String path2, @Body LoginBean loginBean);
}
