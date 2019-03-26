package cn.eric.myapplication.config;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.eric.basiclib.global.Configurator;
import cn.eric.basiclib.utils.GsonContext;

/**
 * Created by eric on 2019/3/24
 */
public class ConfigManager {

    private static final String CONFIG_FILE_NAME = "Configs.mejf";
    private Context appContext = Configurator.get().getAppContext();
    private ConfigEntity mConfigEntity;


    public void initConfig() {
        File localConfigFile = new File(appContext.getFilesDir(), CONFIG_FILE_NAME);

        if (!localConfigFile.exists()) {
            boolean result = copyConfigToLocal(localConfigFile);
            if (!result) {
                return;
            }
        }

        String configJson = DQAcceleratorJni.decodeMjf(localConfigFile.getPath());
        mConfigEntity = GsonContext.getGson().fromJson(configJson, ConfigEntity.class);
    }

    private boolean copyConfigToLocal(File localConfigFile) {
        InputStream inputStream = null;
        FileOutputStream fos = null;
        boolean result = false;
        try {
            inputStream = appContext.getAssets().open(CONFIG_FILE_NAME);
            fos = new FileOutputStream(localConfigFile);
            byte[] content = new byte[inputStream.available()];
            inputStream.read(content);
            fos.write(content);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static ConfigManager getInstance() {
        return ConfigManagerHolder.sInstance;
    }

    private static class ConfigManagerHolder {
        private static final ConfigManager sInstance = new ConfigManager();
    }
}
