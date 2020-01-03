package com.ibnux.platscanner;

import android.app.Application;

import com.ibnux.platscanner.data.ObjectBox;

public class Aplikasi extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ObjectBox.init(this);
    }
}
