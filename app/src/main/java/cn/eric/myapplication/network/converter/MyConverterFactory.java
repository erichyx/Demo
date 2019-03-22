package cn.eric.myapplication.network.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import cn.eric.myapplication.network.request.BaseRequest;
import okhttp3.RequestBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by eric on 2019/3/21
 */
public final class MyConverterFactory extends Converter.Factory {

    public static MyConverterFactory create() {
        return create(new Gson());
    }

    @SuppressWarnings("ConstantConditions")
    public static MyConverterFactory create(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        return new MyConverterFactory(gson);
    }

    private final Gson gson;

    private MyConverterFactory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        // 如果是BaseRequest的子类，则进行处理
        if (BaseRequest.class.isAssignableFrom(getRawType(type))) {
            return new MyRequestBodyConverter<>(gson);
        }

        return null;
    }
}
