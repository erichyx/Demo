package cn.eric.myapplication.repository.remote;

import android.arch.lifecycle.MutableLiveData;

import cn.eric.basiclib.observer.BaseSingleObserver;
import cn.eric.myapplication.api.request.RequestProvider;
import cn.eric.myapplication.api.request.ScreenAdReq;
import cn.eric.myapplication.api.request.base.CommonRequest;
import cn.eric.myapplication.api.request.base.EncryptKeyRequest;
import cn.eric.myapplication.api.request.base.RequestParamFetcher;
import cn.eric.myapplication.api.response.EncryptKeyResp;
import cn.eric.myapplication.api.response.Response;
import cn.eric.myapplication.api.response.ScreenAdResp;
import cn.eric.basiclib.utils.ServiceGenerator;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by eric on 2019/3/24
 */
public final class DolphinRepo {

    private DolphinApi api;

    private DolphinRepo() {
        api = ServiceGenerator.createService(DolphinApi.class);
    }

    public void fetchEncryptKey(final MutableLiveData<Response<EncryptKeyResp>> result) {
        api.fetchEncryptKey(new EncryptKeyRequest())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSingleObserver<Response<EncryptKeyResp>>() {
                    @Override
                    public void onSuccess(Response<EncryptKeyResp> response) {
                        RequestParamFetcher.get().storeEncryptKey(response.getData().getAuthKey());
                        result.setValue(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        result.setValue(Response.error());
                    }
                });
    }

    public void fetchScreenAd(final MutableLiveData<Response<ScreenAdResp>> result) {
        CommonRequest<ScreenAdReq> screenAdReq = RequestProvider.getInstance().getScreenAdReq();
        api.fetchScreeAd(screenAdReq).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSingleObserver<Response<ScreenAdResp>>() {
                    @Override
                    public void onSuccess(Response<ScreenAdResp> response) {
                        result.setValue(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        result.setValue(Response.error());
                    }
                });
    }

    public static DolphinRepo getInstance() {
        return DolphinRepoHolder.sInstance;
    }

    // 静态内部类单例模式
    private static class DolphinRepoHolder {
        private static final DolphinRepo sInstance = new DolphinRepo();
    }
}
