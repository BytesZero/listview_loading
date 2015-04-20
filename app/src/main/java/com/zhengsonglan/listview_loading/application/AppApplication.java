package com.zhengsonglan.listview_loading.application;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Administrator on 2015/4/20.
 */
public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化图片加载框架
        ImageLoaderConfiguration imageLoaderConfiguration=new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);

    }
}
