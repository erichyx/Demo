package cn.eric.myapplication.network.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eric on 2019/3/21
 */
public class Response<T> {

    private @SerializedName("version")
    String version;
    private @SerializedName("method")
    String method;
    private @SerializedName("error_id")
    int errorId;
    private @SerializedName("error_msg")
    String errorMsg;
    private @SerializedName("timestamp")
    long timestamp;
    private @SerializedName("response")
    T data;

    private Response(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    Response() {
    }

    public boolean success() {
        // TODO  判断响应成功条件
        return false;
    }

    public static <T> Response<T> error() {
        return new Response<T>("请求失败");
    }

    public static <T> Response<T> error(String msg) {
        return new Response<T>(msg);
    }
}
