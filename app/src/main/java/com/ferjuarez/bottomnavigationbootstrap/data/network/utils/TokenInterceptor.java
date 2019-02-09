package com.ferjuarez.bottomnavigationbootstrap.data.network.utils;

import com.ferjuarez.bottomnavigationbootstrap.data.shared.SharedContract;
import com.ferjuarez.bottomnavigationbootstrap.data.shared.SharedException;
import java.io.IOException;
import javax.inject.Inject;
import io.reactivex.annotations.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";

    private final SharedContract sharedManager;

    @Inject
    public TokenInterceptor(SharedContract sharedManager) {
        this.sharedManager = sharedManager;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();
        Request request = addTokenHeaders(original);
        return chain.proceed(request);
    }


    private Request addTokenHeaders(Request original) {
        return original.newBuilder()
                    .addHeader(HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON)
                    .addHeader(HEADER_AUTHORIZATION, getBasicAuthToken())
                    .build();
    }

    private String getBasicAuthToken() {
        try {
            return "Bearer " + sharedManager.getAccessToken();
        } catch (SharedException e) {
            e.printStackTrace();
        }
        return "";
    }

}