package cn.eric.myapplication.config;

/**
 * Created by eric on 2019/3/24
 */
public class DQAcceleratorJni {
    static {
        System.loadLibrary("DQAccelerator");
    }

    public static native String decodeMjf(String filePath);
}
