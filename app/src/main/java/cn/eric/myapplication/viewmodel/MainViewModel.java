package cn.eric.myapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import cn.eric.myapplication.api.response.EncryptKeyResp;
import cn.eric.myapplication.api.response.Response;
import cn.eric.myapplication.api.response.ScreenAdResp;
import cn.eric.myapplication.repository.remote.DolphinRepo;

/**
 * Created by eric on 2019/3/24
 */
public class MainViewModel extends ViewModel {
    private DolphinRepo remoteRepo;
    public MainViewModel(DolphinRepo repo) {
        remoteRepo = repo;
    }

    private MutableLiveData<Response<EncryptKeyResp>> authKeyResult = new MutableLiveData<>();
    public LiveData<Response<EncryptKeyResp>> getEncryptKeyResult() { return authKeyResult; }

    private MutableLiveData<Response<ScreenAdResp>> screenAdResult = new MutableLiveData<>();
    public LiveData<Response<ScreenAdResp>> getScreenAdResult() {
        return screenAdResult;
    }

    public void fetchAuthKey() {
        remoteRepo.fetchEncryptKey(authKeyResult);
    }

    public void fetchScreenAd() {
        remoteRepo.fetchScreenAd(screenAdResult);
    }
}
