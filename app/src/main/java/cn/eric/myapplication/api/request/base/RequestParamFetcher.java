package cn.eric.myapplication.api.request.base;

import android.text.TextUtils;

public final class RequestParamFetcher {
    private RequestParamFetcher() {}

    private static final String DEFAULT_ENCRYPT_KEY = "elFYTUVfcAEBAh8AEw==";
    private String encryptKey;
    private ParamEncipher encipher = new ParamEncipher();

    public String fetchDecryptKey() {
        String decryptKey = encipher.decrypt(DEFAULT_ENCRYPT_KEY,"201901031022");
        if (TextUtils.isEmpty(encryptKey)) {
            return decryptKey;
        }
        return encipher.decrypt(encryptKey, decryptKey);
    }

    public void storeEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
    }

    public ParamEncipher getParamEncipher() {
        return encipher;
    }

    public static RequestParamFetcher get() {
        return RequestParamFetcherHolder.sInstance;
    }

    // 静态内部类单例模式
    private static class RequestParamFetcherHolder {
        private static final RequestParamFetcher sInstance = new RequestParamFetcher();
    }
}
