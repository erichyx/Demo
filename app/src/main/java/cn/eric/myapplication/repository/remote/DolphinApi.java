package cn.eric.myapplication.repository.remote;

import cn.eric.myapplication.api.request.ScreenAdReq;
import cn.eric.myapplication.api.request.base.CommonRequest;
import cn.eric.myapplication.api.request.base.EncryptKeyRequest;
import cn.eric.myapplication.api.response.EncryptKeyResp;
import cn.eric.myapplication.api.response.Response;
import cn.eric.myapplication.api.response.ScreenAdResp;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by eric on 2019/3/24
 */
public interface DolphinApi {
    @POST(".")
    Single<Response<EncryptKeyResp>> fetchEncryptKey(@Body EncryptKeyRequest req);

    @POST(".")
    Single<Response<ScreenAdResp>> fetchScreeAd(@Body CommonRequest<ScreenAdReq> req);
}
