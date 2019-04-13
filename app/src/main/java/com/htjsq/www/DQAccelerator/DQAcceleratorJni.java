package com.htjsq.www.DQAccelerator;

/**
 * Created by eric on 2019/3/24
 */
public class DQAcceleratorJni {
    static {
        System.loadLibrary("DQAccelerator");
    }

    public static native int CheckClient(int checkCode, String version);
    public static native String DecodeMjf(String filePath);
}
