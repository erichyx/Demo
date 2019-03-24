package cn.eric.myapplication.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by eric on 2019/3/24
 */
public class ToastUtil {
    public static void showToast(Context context, CharSequence text) {
        if (context != null) {
            if (!TextUtils.isEmpty(text)) {
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static void showToast(Context context, @StringRes int resId) {
        if (context != null) {
            Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
        }
    }
}
