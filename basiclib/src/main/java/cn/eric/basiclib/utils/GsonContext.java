package cn.eric.basiclib.utils;

import com.google.gson.Gson;

/**
 * Created by eric on 2019/3/24
 */
public class GsonContext {
    private GsonContext() {}

    private static final Gson gson = new Gson();

    public static Gson getGson() {
        return gson;
    }
}
