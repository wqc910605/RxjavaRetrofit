package com.wwf.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wwf.test.retrofit_rxjava.BaseBean;
import com.wwf.test.retrofit_rxjava.RetrofitUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click(View view) {
//        setNotification();
//        toastDemo();
//        test1();
//        test2();
//        test3();
//        test4();
        test5();
    }

    private void test5() {
//        LoginBean loginBean = new LoginBean();
//        loginBean.sid = "1";
//        loginBean.userId = "123456";

        Map<String, String> params = new HashMap<>();
        params.put("sid", "1");
        params.put("userId", "123456");
        RetrofitUtil.getInstance().getApiService().post("snail", "queryIdentities.do", params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<String>>() {
                    @Override
                    public void accept(BaseBean<String> baseBean) throws Exception {
                        System.out.println("demo: "+baseBean.getCode()+" / "+baseBean.getMessage());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("demo: "+throwable.getMessage());
                    }
                });
    }

    private void test4() {
        Map<String, String> params = new HashMap<>();
        params.put("sid", "1");
        params.put("userId", "123456");
        RetrofitUtil.getInstance().getApiService().get("snail", "queryIdentities.do", params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<String>>() {
                    @Override
                    public void accept(BaseBean<String> baseBean) throws Exception {
                        System.out.println(baseBean.getCode()+" / "+baseBean.getMessage());
                    }
                });
    }

    private void test3() {
        Map<String, String> params = new HashMap<>();
        params.put("sid", "1");
        params.put("userId", "123456");
        RetrofitUtil.getInstance().getApiService().get("snail", "queryIdentities.do", params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<String>>() {
                    @Override
                    public void accept(BaseBean<String> baseBean) throws Exception {
                        System.out.println(baseBean.getCode()+" / "+baseBean.getMessage());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println(throwable.getMessage());
                    }
                });
    }

    private void test2() {
        Map<String, String> params = new HashMap<>();
        params.put("sid", "1");
        params.put("userId", "123456");
        RetrofitUtil.getInstance().getApiService().get("queryIdentities.do", params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<String>>() {
                    @Override
                    public void accept(BaseBean<String> baseBean) throws Exception {
                        System.out.println(baseBean.getCode()+" / "+baseBean.getMessage());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println(throwable.getMessage());
                    }
                });
    }

    private void test1() {
        RetrofitUtil.getInstance().getApiService().get("https://snail-stg1.zysnail.com/snail/queryIdentities.do?sid=1&userId=123456")
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求结果
                .subscribe(new Consumer<BaseBean<String>>() {
                    @Override
                    public void accept(BaseBean<String> baseBean) throws Exception {
                        System.out.println(baseBean.getCode()+" / "+baseBean.getMessage());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtil.show("网络不好");
                    }
                });
    }
    private void toastDemo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.show("你好啊冠福股份大");
            }
        }).start();
    }
}
