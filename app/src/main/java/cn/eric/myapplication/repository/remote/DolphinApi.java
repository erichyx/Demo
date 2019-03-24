package cn.eric.myapplication.repository.remote;

import cn.eric.myapplication.api.request.ScreenAdReq;
import cn.eric.myapplication.api.request.base.CommonRequest;
import cn.eric.myapplication.api.response.Response;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by eric on 2019/3/24
 */
public interface DolphinApi {
    @POST(".")
    Single<Response<Object>> fetchAd(@Body CommonRequest<ScreenAdReq> req);
}
