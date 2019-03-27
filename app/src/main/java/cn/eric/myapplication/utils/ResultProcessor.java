package cn.eric.myapplication.utils;

import android.content.Context;

import cn.eric.myapplication.api.response.Response;

/**
 * Created by eric on 2019/3/24
 */
public class ResultProcessor {

    public interface SuccessCallback<T> {
        void onSuccess(T data);
    }

    public interface ErrorCallback<T> {
        void onError(String msg);
    }

    public interface ResultCallback<T> extends SuccessCallback<T>, ErrorCallback<T> {

    }

    public static <T> void showResult(Context context, Response<T> resp, ResultCallback<T> callback) {
        if (resp.success()) {
            callback.onSuccess(resp.getData());
        } else {
            callback.onError(resp.getErrorMsg());
        }
    }

    public static <T> void showResult(Context context, Response<T> resp, SuccessCallback<T> callback) {
        if (resp.success()) {
            callback.onSuccess(resp.getData());
        } else {
            ToastUtil.showToast(context,resp.getErrorMsg());
        }
    }
}
