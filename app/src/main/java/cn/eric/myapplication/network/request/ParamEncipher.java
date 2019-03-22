package cn.eric.myapplication.network.request;

import android.text.TextUtils;
import android.util.Base64;

// 负责对参数进行加解密
public class ParamEncipher {

    public String decryptKey(String str, String key) {
        if (TextUtils.isEmpty(key)) return "";

        int keyLen = key.length();
        byte[] bytes;
        try {
            bytes = Base64.decode(str.getBytes(), Base64.NO_WRAP);
            for(int i = 0; i < bytes.length; i += keyLen){
                for(int j = i; j < Math.min(i + keyLen, bytes.length); j ++){
                    bytes[j] ^= key.charAt(j - i);
                }
            }
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String encryptData(String data, String key) {
        int keyLen = key.length();
        byte[] bytes = data.getBytes();
        for(int i = 0; i < bytes.length; i += keyLen){
            for(int j = i; j < Math.min(i + keyLen, bytes.length); j ++){
                bytes[j] ^= key.charAt(j - i);
            }
        }
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }
}
