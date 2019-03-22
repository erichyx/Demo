package cn.eric.myapplication.network.utils;


import android.content.Context;

import cn.eric.myapplication.network.global.Configurator;

/**
 * Created by hyx on 2017/5/15.
 */

public class ServiceGenerator {
    private ServiceGenerator() {
    }

    public static <T> T createService(Class<T> serviceClass) {
        Context appContext = Configurator.get().getAppContext();
        return Retrofit2Util.getInstance(appContext).create(serviceClass);
    }
}
