package cn.eric.myapplication.utils;

import android.content.Context;

import cn.eric.myapplication.api.response.Response;

/**
 * Created by eric on 2019/3/24
 */
public class ResultUtil {

    public interface SuccessBlock<T> {
        void onSuccess(T data);
    }

    public static <T> void showResult(Context context, Response<T> resp, SuccessBlock<T> block) {
        if (resp.success()) {
            block.onSuccess(resp.getData());
        } else {
            ToastUtil.showToast(context,resp.getErrorMsg());
        }
    }
}