package cn.eric.myapplication.api.request;

import com.google.gson.annotations.SerializedName;

import cn.eric.myapplication.utils.ApkTool;

/**
 * Created by eric on 2019/3/24
 */
public class ScreenAdReq {
    @SerializedName("version")
    private String version;
    @SerializedName("imei")
    private String imei;

    public ScreenAdReq() {
        version = ApkTool.getCurrentAppVersion();
        imei = "d7e4facf-39fc-32f2-975d-c00cb66121a1";
    }
}
