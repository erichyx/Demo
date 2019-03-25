package cn.eric.myapplication.utils;

import android.content.Context;

import cn.eric.myapplication.repository.remote.DolphinRepo;
import cn.eric.myapplication.viewmodel.MainViewModelFactory;

/**
 * Created by eric on 2019/3/24
 */
public final class InjectorUtils {

    public static MainViewModelFactory provideMainViewModelFactory() {
        return new MainViewModelFactory(DolphinRepo.getInstance());
    }

}
