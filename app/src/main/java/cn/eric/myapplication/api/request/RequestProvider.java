package cn.eric.myapplication.api.request;

import cn.eric.myapplication.api.request.base.CommonRequest;

/**
 * Created by eric on 2019/3/24
 */
public class RequestProvider {

    public CommonRequest<ScreenAdReq> getScreenAdReq() {
        return new CommonRequest.Builder<ScreenAdReq>()
                .setMethod("")
                .setReqData(new ScreenAdReq())
                .build();
    }


    public static RequestProvider getInstance() {
        return RequestProviderHolder.sInstance;
    }

    private static class RequestProviderHolder {
        private static final RequestProvider sInstance = new RequestProvider();
    }
}
