package cn.eric.myapplication.api.response;

import com.google.gson.annotations.SerializedName;

public class EncryptKeyResp {
    @SerializedName("kc")
    private String authKey;

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }
}
