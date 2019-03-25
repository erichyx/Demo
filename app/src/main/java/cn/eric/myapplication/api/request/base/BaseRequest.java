package cn.eric.myapplication.api.request.base;

import android.os.Build;

import com.google.gson.annotations.SerializedName;

public abstract class BaseRequest {
    @SerializedName("method")
    protected String method;
    @SerializedName("os")
    protected String os;
    @SerializedName("timestamp")
    protected long timestamp;
    @SerializedName("identifier")
    protected String identifier;
    protected @SerializedName("version")
    int version = 2;
    protected @SerializedName("platform")
    String platform = "android";

    protected BaseRequest(String method) {
        this.method = method;
        timestamp = System.currentTimeMillis() / 1000;
        os = "Android " + Build.VERSION.RELEASE;
        // TODO 调用工具类获取
        identifier = "d7e4facf-39fc-32f2-975d-c00cb66121a1";
    }
}
