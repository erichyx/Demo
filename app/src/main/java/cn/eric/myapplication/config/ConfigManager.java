package cn.eric.myapplication.config;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.htjsq.www.DQAccelerator.DQAcceleratorJni;

import java.io.File;
import java.util.List;
import java.util.concurrent.Executor;

import cn.eric.basiclib.global.AppExecutors;
import cn.eric.basiclib.utils.GsonContext;
import cn.eric.myapplication.utils.FileHelper;

/**
 * Created by eric on 2019/3/24
 */
public class ConfigManager {

    private static final String CONFIG_FILE_NAME = "Configs.mejf";
    private static final String SERVER_FILE_NAME = "Servers.mejf";

    private Context appContext;
    private ConfigEntity mConfigEntity;
    private ServersEntity mServersEntity;
    private DataState mDataState = DataState.STATE_CREATED;
    private MutableLiveData<Boolean> mDataReady = new MutableLiveData<>();

    private ConfigManager() {}

    /**
     * 初始化配置文件，主要是Assert文件到本地的拷贝，配置文件解密以及反序列化。
     *
     * @param context
     */
    public void initConfig(Context context) {
        appContext = context.getApplicationContext();
        Executor diskIO = AppExecutors.getInstance().diskIO();
        diskIO.execute(new Runnable() {
            @Override
            public void run() {
                // 设置初始化数据状态
                setDataState(DataState.STATE_INITIALIZING);

                try {
                    // 如果Configs.mejf文件不存在，拷贝到本地文件目录下
                    String localFilePath = appContext.getFilesDir().getAbsolutePath() + File.separator;
                    String configFile = localFilePath + CONFIG_FILE_NAME;
                    boolean result = copyConfigFileToLocal(configFile);
                    if (!result) {
                        setDataState(DataState.STATE_ERROR);
                        return;
                    }

                    // 进行鉴权认证
                    if (!checkClient()) {
                        Log.d("dolphin","鉴权认证失败");
                        setDataState(DataState.STATE_ERROR);
                        return;
                    }

                    // Configs.mejf文件解码，反序列化
                    String configJson = DQAcceleratorJni.DecodeMjf(configFile);
                    mConfigEntity = GsonContext.getGson().fromJson(configJson, ConfigEntity.class);

                    // 拷贝Components指定的所有Asset文件
                    List<ConfigEntity.ComponentsBean> components = mConfigEntity.getComponents();
                    for (ConfigEntity.ComponentsBean component : components) {
                        // 拷贝指定文件到本地，如果本地存在则覆盖
                        copyAssetFileToLocal(component.getName(), localFilePath + component.getName());
                    }

                    // Servers.mejf文件解码，反序列化
                    // 该文件是上一步通过拷贝Components下的文件生成到本地的
                    String serversJson = DQAcceleratorJni.DecodeMjf(localFilePath + SERVER_FILE_NAME);
                    mServersEntity = GsonContext.getGson().fromJson(serversJson, ServersEntity.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    setDataState(DataState.STATE_ERROR);
                    return;
                }

                // 数据初始化完成
                setDataState(DataState.STATE_INITIALIZED);
            }
        });
    }

    public LiveData<Boolean> getDataReady() {
        return mDataReady;
    }

    public ConfigEntity getConfigEntity() {
        return mConfigEntity;
    }

    private void setDataState(DataState dataState) {
        mDataState = dataState;
        if (mDataState == DataState.STATE_INITIALIZED || mDataState == DataState.STATE_ERROR) {
            mDataReady.postValue(mDataState == DataState.STATE_INITIALIZED);
        }
    }

    private boolean copyConfigFileToLocal(String filePath) {
        if (!FileHelper.fileExists(filePath)) {
            return FileHelper.copyFileInPackageToFile(CONFIG_FILE_NAME, filePath, false);
        }
        return true;
    }

    private boolean copyAssetFileToLocal(String assetFileName, String filePath) {
        if (!FileHelper.fileExists(filePath)) {
            return FileHelper.copyFileInPackageToFile(assetFileName, filePath, false);
        } else {
            return FileHelper.copyFileInPackageToFile(assetFileName, filePath, true);
        }
    }

    private boolean checkClient() {
        int code1 = 0xc4;
        int code2 = 0x6a;
        long time = System.currentTimeMillis() / 1000;
        int ime = ~((byte) time ^ code1) & code2;
        // fixme 版本号通过方法读取
        int checkResult = DQAcceleratorJni.CheckClient(ime, "1.5.328");
        return checkResult >= 0;
    }

    public static ConfigManager getInstance() {
        return ConfigManagerHolder.sInstance;
    }

    private static class ConfigManagerHolder {
        private static final ConfigManager sInstance = new ConfigManager();
    }
}
