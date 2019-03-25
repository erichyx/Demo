package cn.eric.myapplication.api.request.base;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eric on 2019/3/21
 */
public class CommonRequest<T> extends BaseRequest {
    private @SerializedName("auth") Auth auth;
    private @SerializedName("request") T request;

    private CommonRequest(Builder<T> builder) {
        super(builder.method);
        request = builder.data;
        auth = new Auth(timestamp);
    }

    static class Auth {
        private @SerializedName("auth_key") String authKey;
        private @SerializedName("role_id") String roleId;
        private @SerializedName("role_version") String roleVersion;

        private static ParamEncipher encipher = RequestParamFetcher.get().getParamEncipher();

        Auth(long timeStamp) {
            // TODO 从配置文件读取
            roleId = "DolphinQ_Mobile_Android";
            roleVersion = "1.5.316";

            String originKey = roleId + "-" + roleVersion + "|[" + timeStamp + "]";
            authKey = encipher.encryptAuthKey(originKey);
        }

        public String getAuthKey() {
            return authKey;
        }

        public void setAuthKey(String authKey) {
            this.authKey = authKey;
        }

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

        public String getRoleVersion() {
            return roleVersion;
        }

        public void setRoleVersion(String roleVersion) {
            this.roleVersion = roleVersion;
        }
    }

    public static class Builder<T> {
        private String method;
        private T data;

        public Builder<T> setMethod(String method) {
            this.method = method;
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
