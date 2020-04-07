package com.lantian.lib_network.retrofit2;

import com.lantian.lib_network.common.IdeaApi;

/**
 * Created by Sherlock·Holmes on 2020-03-06
 */
public class RetrofitHelper {

    private static IdeaApiService mIdeaApiService;
    public static IdeaApiService getApiService() {
        if (mIdeaApiService == null) {
            mIdeaApiService = IdeaApi.getApiService(IdeaApiService.class, ServerConfig.BASE_URL);
        }
        return mIdeaApiService;
    }
}
