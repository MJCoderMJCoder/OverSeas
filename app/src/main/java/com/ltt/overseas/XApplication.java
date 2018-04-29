package com.ltt.overseas;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;


import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.ThemeConfig;


public class XApplication extends Application {

    private static Application app;

    public static Application getApplication() {
        return app;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        init();
//        initGalleryFinal();
    }

    private void initGalleryFinal() {
        ThemeConfig config = new ThemeConfig.Builder().setTitleBarBgColor(getResources().getColor(R.color.theme))
                .build();

        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(false)
                .setEnableEdit(false)
                .setEnableCrop(false)
                .setEnableRotate(false)


                .setCropSquare(true)
                .setEnablePreview(false)
                .setMutiSelectMaxSize(9)
                .build();
    }

    private void init() {

    }

}
