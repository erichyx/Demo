package cn.eric.myapplication.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import cn.eric.myapplication.repository.remote.DolphinRepo;

/**
 * Created by eric on 2019/3/24
 */
public class MainViewModelFactory implements ViewModelProvider.Factory {

    private DolphinRepo remoteRepo;
    public MainViewModelFactory(DolphinRepo repo) {
        remoteRepo = repo;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(remoteRepo);
    }
}
