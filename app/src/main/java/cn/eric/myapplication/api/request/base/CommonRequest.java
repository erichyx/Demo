package cn.eric.myapplication.api.request.base;

import android.os.Build;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eric on 2019/3/21
 */
public class CommonRequest<T> {
    private @SerializedName("method") String method;
    private @SerializedName("version") int version = 2;
    private @SerializedName("platform") String platform = "android";
    private @SerializedName("os") String os;
    private @SerializedName("timestamp") long timestamp;
    private @SerializedName("identifier") String identifier;
    private @SerializedName("auth") Auth auth;
    private @SerializedName("request") T request;

    private CommonRequest(Builder<T> builder) {
        this.method = builder.method;
        request = builder.data;
        init(builder.authIndependent);
    }

    private void init(boolean authIndependent) {
        timestamp = System.currentTimeMillis() / 1000;
        os = "Android " + Build.VERSION.RELEASE;
        // TODO 调用工具类获取
        identifier = "d7e4facf-39fc-32f2-975d-c00cb66121a1";
        if (authIndependent) {
            auth = new Auth(timestamp);
        }
    }

    private static class Auth {
        private @SerializedName("auth_key") String authKey;
        private @SerializedName("role_id") String roleId;
        private @SerializedName("role_version") String roleVersion;

        private static ParamEncipher encipher = RequestParamFetcher.get().getParamEncipher();

        public Auth(long timeStamp) {
            // TODO 从配置文件读取
            roleId = "DolphinQ_Mobile_Android";
            roleVersion = "1.0.1227";

            String originKey = roleId + "-" + roleVersion + "|[" + timeStamp + "]";
            authKey = encipher.encryptAuthKey(originKey);
        }
    }

    public static class Builder<T> {
        private String method;
        private T data;
        private boolean authIndependent = true;

        public Builder<T> setMethod(String method) {
            this.method = method;
            return this;
        }

        public Builder<T> setAuthIndependent(boolean authIndependent) {
            this.authIndependent = authIndependent;
            return this;
        }

        public Builder<T> setReqData(T data) {
            this.data = data;
            return this;
        }

        public CommonRequest<T> build() {
            return new CommonRequest<>(this);
        }
    }
}
