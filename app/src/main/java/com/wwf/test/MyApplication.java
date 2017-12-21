package com.wwf.test;

import android.app.Application;
import android.content.Context;

/**
 * Created by Dell on 2017/12/21.
 */

public class MyApplication extends Application {
    public static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }
}
