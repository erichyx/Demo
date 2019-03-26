package cn.eric.myapplication.utils;

import android.content.Context;

import cn.eric.basiclib.utils.GsonContext;
import cn.eric.basiclib.utils.OkHttp3Util;
import cn.eric.myapplication.api.converter.MyConverterFactory;
import cn.eric.basiclib.global.Configurator;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hyx on 2017/2/16.
 */

public class Retrofit2Util {
    private static Retrofit sRetrofit;

    public static Retrofit getInstance(Context context) {
        if (sRetrofit == null) {
            synchronized (Retrofit2Util.class) {
                if (sRetrofit == null) {
                    String baseUrl = Configurator.get().getBaseUrl();
                    sRetrofit = new Retrofit.Builder().baseUrl(baseUrl)
                            .client(OkHttp3Util.getOkHttpClient(context))
                            .addConverterFactory(MyConverterFactory.create(GsonContext.getGson()))
                            .addConverterFactory(GsonConverterFactory.create(GsonContext.getGson()))
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                            .build();
                }
            }
        }
        return sRetrofit;
    }
}
