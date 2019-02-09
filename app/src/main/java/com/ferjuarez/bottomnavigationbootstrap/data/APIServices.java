package com.ferjuarez.bottomnavigationbootstrap.data;

import com.ferjuarez.bottomnavigationbootstrap.models.account.AccountParser;
import java.util.List;
import java.util.Map;
import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIServices {

    //region URLs
    String BASE_URL = "http://50.19.122.69:3000/";
    String SESSION_CREATE = BASE_URL + "sessions/create";
    String LOOKUPS = BASE_URL + "api/lookup/";

    //endregion
    @POST(SESSION_CREATE)
    Single<AccountParser> login(@Body Map<String, String> body);


}