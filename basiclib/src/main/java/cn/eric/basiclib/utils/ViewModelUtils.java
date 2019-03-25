package cn.eric.basiclib.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by eric on 2019/3/24
 */
public final class ViewModelUtils {
    public static <T extends ViewModel> T provideViewModel(FragmentActivity activity, ViewModelProvider.Factory factory, Class<T> modelClass) {
        return ViewModelProviders.of(activity, factory).get(modelClass);
    }

    public static <T extends ViewModel> T provideViewModel(Fragment fragment, ViewModelProvider.Factory factory, Class<T> modelClass) {
        return ViewModelProviders.of(fragment, factory).get(modelClass);
    }
}
