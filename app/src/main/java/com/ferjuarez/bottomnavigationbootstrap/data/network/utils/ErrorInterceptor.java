package com.ferjuarez.bottomnavigationbootstrap.data.network.utils;

import android.support.annotation.NonNull;
import com.ferjuarez.bottomnavigationbootstrap.data.network.errors.ErrorResponse;
import java.io.IOException;
import java.net.SocketException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ErrorInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Response response;
        try{
            response = chain.proceed(originalRequest);
            if (response.code() >= 400){
                if(response.code() == 401){
                    String error = "";
                    if(response.body() != null){
                        error = response.body().string();
                    }
                    throw new ErrorResponse(response.code(), error);
                } else {
                    throw ErrorResponse.generateError(response.code(), response.body().string());
                }
            }
        } catch (SocketException e){
            throw new ErrorResponse(404, "Connection Closed");
        }

        return response;
    }
}