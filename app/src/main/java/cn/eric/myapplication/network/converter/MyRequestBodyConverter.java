package cn.eric.myapplication.network.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import cn.eric.myapplication.network.request.ParamEncipher;
import cn.eric.myapplication.network.request.RequestParamFetcher;
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

    MyRequestBodyConverter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public RequestBody convert(T value) {
        String jsonData = gson.toJson(value);

        ParamEncipher encipher = RequestParamFetcher.get().getParamEncipher();
        String decryptKey = RequestParamFetcher.get().fetchDecryptKey();
        String encryptData = encipher.encryptData(jsonData, decryptKey);
        return RequestBody.create(TEXT_MARKDOWN, encryptData);
    }
}
