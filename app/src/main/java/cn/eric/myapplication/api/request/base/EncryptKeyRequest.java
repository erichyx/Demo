package cn.eric.myapplication.api.request.base;

import com.google.gson.annotations.SerializedName;

public final class EncryptKeyRequest extends BaseRequest {
    private @SerializedName("auth_key") String authKey;
    private @SerializedName("role_id") String roleId;
    private @SerializedName("role_version") String roleVersion;

    public EncryptKeyRequest() {
        super("program_init");

        CommonRequest.Auth auth = new CommonRequest.Auth(timestamp);
        authKey = auth.getAuthKey();
        roleId = auth.getRoleId();
        roleVersion = auth.getRoleVersion();
    }
}
