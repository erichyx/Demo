package cn.eric.myapplication.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eric on 2019/3/21
 */
public abstract class BaseRequest<T> {
    private @SerializedName("method") String method;
    private @SerializedName("version") int version;
    private @SerializedName("platform") String platform = "Android";
    private @SerializedName("os") String os;
    private @SerializedName("timestamp") long timestamp;
    private @SerializedName("identifier") String identifier;
    private @SerializedName("auth") Auth auth;

    BaseRequest(String method) {
        this.method = method;
        timestamp = System.currentTimeMillis();
    }

    abstract T fetchRequest();

    static class Auth {
        private @SerializedName("auth_key") String authKey;
        private @SerializedName("role_id") String roleId;
        private @SerializedName("role_version") String roleVersion;
    }
}
