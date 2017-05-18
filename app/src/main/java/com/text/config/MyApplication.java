package com.text.config;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by ZCYL on 2017/5/16.
 */

public class MyApplication extends Application {

    public static RefWatcher refWatcher;
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        refWatcher = LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(){
        return refWatcher;
    }


}
