package cn.eric.myapplication.api.converter;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import cn.eric.myapplication.api.request.base.EncryptKeyRequest;
import cn.eric.myapplication.api.request.base.ParamEncipher;
import cn.eric.myapplication.api.request.base.RequestParamFetcher;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by eric on 2019/3/21
 */
public class MyRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType TEXT_MARKDOWN = MediaType.get("text/x-markdown; charset=utf-8");
//    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;
    private boolean isEncryptKeyReq;

    MyRequestBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        isEncryptKeyReq = type.getClass().isAssignableFrom(EncryptKeyRequest.class);
    }

    @Override
    public RequestBody convert(T value) {
        String jsonData = gson.toJson(value);

        ParamEncipher encipher = RequestParamFetcher.get().getParamEncipher();
        String decryptKey = RequestParamFetcher.get().fetchDecryptKey();
        String encryptData = encipher.encryptData(jsonData, decryptKey);
        if (isEncryptKeyReq) {
            encryptData = "BFEACC1C" + encryptData;
        }
        return RequestBody.create(TEXT_MARKDOWN, encryptData);
    }
}
