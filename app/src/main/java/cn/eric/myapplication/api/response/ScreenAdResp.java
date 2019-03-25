package cn.eric.myapplication.api.response;

import com.google.gson.annotations.SerializedName;

public class ScreenAdResp {
    @SerializedName("type")
    private String type;
    @SerializedName("data")
    private String data;
    @SerializedName("ad_url")
    private String adUrl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }
}
