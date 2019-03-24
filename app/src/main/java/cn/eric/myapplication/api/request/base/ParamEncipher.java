package cn.eric.myapplication.api.request.base;

import android.text.TextUtils;
import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// 负责对参数进行加解密
public class ParamEncipher {
    private static final String SALT = "Htjsq%@)";

    public String decryptKey(String str, String key) {
        if (TextUtils.isEmpty(key)) return "";

        int keyLen = key.length();
        byte[] bytes;
        try {
            bytes = Base64.decode(str.getBytes(), Base64.NO_WRAP);
            for (int i = 0; i < bytes.length; i += keyLen) {
                for (int j = i; j < Math.min(i + keyLen, bytes.length); j++) {
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
        for (int i = 0; i < bytes.length; i += keyLen) {
            for (int j = i; j < Math.min(i + keyLen, bytes.length); j++) {
                bytes[j] ^= key.charAt(j - i);
            }
        }
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    public String encryptAuthKey(String authKey) {
        return encodeString("MD5",
                encodeString("MD5", authKey).toLowerCase() + SALT)
                .toUpperCase();
    }

    private String encodeString(String algorithm, String source) {
        try {
            MessageDigest message_digest = MessageDigest.getInstance(algorithm);
            message_digest.update(source.getBytes());

            return byteArrayToHex(message_digest.digest());
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    private String byteArrayToHex(byte[] byteArray) {
        char[] hex_digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] char_array = new char[byteArray.length * 2];

        int index = 0;
        for (byte b : byteArray) {
            char_array[index++] = hex_digits[b >>> 4 & 0xf];
            char_array[index++] = hex_digits[b & 0xf];
        }

        return new String(char_array);
    }
}
