package com.caiomcg.es.Controllers;

import android.app.Application;
import android.util.Log;

public class AgroTecApplication extends Application {
    private static AgroTecApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static AgroTecApplication getContext() {
        return mContext;
    }
}
