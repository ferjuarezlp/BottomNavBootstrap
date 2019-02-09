package com.ferjuarez.bottomnavigationbootstrap.injection.app;

import com.ferjuarez.bottomnavigationbootstrap.data.APIServices;
import com.ferjuarez.bottomnavigationbootstrap.data.network.utils.ErrorInterceptor;
import com.ferjuarez.bottomnavigationbootstrap.data.network.utils.TokenInterceptor;
import com.ferjuarez.bottomnavigationbootstrap.data.shared.SharedContract;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    private static final long DEFAULT_TIMEOUT = 1;

    @Provides
    @Singleton
    static APIServices provideContractAPI(Retrofit retrofit) {
        return retrofit.create(APIServices.class);
    }

    @Provides
    @Singleton
    static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(APIServices.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor, TokenInterceptor tokenInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(new ErrorInterceptor())
                .addInterceptor(loggingInterceptor)
                .addInterceptor(tokenInterceptor)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES)
                .build();
    }

    @Provides
    @Singleton
    static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Provides
    @Singleton
    static TokenInterceptor provideTokenInterceptor(SharedContract sharedManager) {
        return new TokenInterceptor(sharedManager);
    }

}