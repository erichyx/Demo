package cn.eric.myapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import cn.eric.myapplication.api.response.Response;
import cn.eric.myapplication.repository.remote.DolphinRepo;
import okhttp3.ResponseBody;

/**
 * Created by eric on 2019/3/24
 */
public class MainViewModel extends ViewModel {
    private DolphinRepo remoteRepo;
    public MainViewModel(DolphinRepo repo) {
        remoteRepo = repo;
    }

    private MutableLiveData<Response<Object>> adResult = new MutableLiveData<Response<Object>>();

    public LiveData<Response<Object>> getAdResult() {
        return adResult;
    }

    public void fetchAd() {
        remoteRepo.fetchAd(adResult);
    }
}
