package cn.eric.myapplication.api.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import cn.eric.myapplication.api.request.base.ParamEncipher;
import cn.eric.myapplication.api.request.base.RequestParamFetcher;
import okhttp3.ResponseBody;
import retrofit2.Converter;

public class MyResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    MyResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String encryptData = value.string();
        ParamEncipher encipher = RequestParamFetcher.get().getParamEncipher();
        String decryptData = encipher.decrypt(encryptData, RequestParamFetcher.get().fetchDecryptKey());
        return adapter.fromJson(decryptData);
    }
}
