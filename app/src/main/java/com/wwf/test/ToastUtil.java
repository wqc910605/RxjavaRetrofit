package com.wwf.test;

import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by Dell on 2017/12/21.
 */

public class ToastUtil {
    private static Toast sToast;
    public static void show(final String msg) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext(msg);
            }
        }) .subscribeOn(AndroidSchedulers.mainThread())
          .subscribe(new Consumer<String>() {
              @Override
              public void accept(String s) throws Exception {
                  if (sToast == null) {
                      sToast = Toast.makeText(MyApplication.sContext, s, Toast.LENGTH_SHORT);
                  }
                  sToast.show();
              }
          });
    }
}
