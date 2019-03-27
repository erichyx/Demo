package cn.eric.myapplication.app;

import android.app.Application;

import cn.eric.basiclib.global.Configurator;
import cn.eric.basiclib.utils.GsonContext;
import cn.eric.myapplication.api.converter.MyConverterFactory;
import cn.eric.myapplication.config.ConfigManager;

/**
 * Created by eric on 2019/3/24
 */
public class DolphinApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Configurator.get().appContext(this)
                .baseUrl("http://api.htjsq.com/cgi/android/request/")
                .convertFactory(MyConverterFactory.create(GsonContext.getGson()))
                .configure();

//        ConfigManager.getInstance().initConfig();
    }
}
