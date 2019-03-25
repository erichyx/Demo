package cn.eric.myapplication.repository.remote;

import android.arch.lifecycle.MutableLiveData;

import cn.eric.myapplication.api.BaseSingleObserver;
import cn.eric.myapplication.api.request.RequestProvider;
import cn.eric.myapplication.api.request.ScreenAdReq;
import cn.eric.myapplication.api.request.base.CommonRequest;
import cn.eric.myapplication.api.response.Response;
import cn.eric.myapplication.utils.ServiceGenerator;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by eric on 2019/3/24
 */
public final class DolphinRepo {

    private DolphinApi api;

    private DolphinRepo() {
        api = ServiceGenerator.createService(DolphinApi.class);
    }

    public void fetchAd(final MutableLiveData<Response<Object>> result) {
        CommonRequest<ScreenAdReq> screenAdReq = RequestProvider.getInstance().getScreenAdReq();
        api.fetchAd(screenAdReq).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSingleObserver<Response<Object>>() {
                    @Override
                    public void onSuccess(Response<Object> response) {
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
