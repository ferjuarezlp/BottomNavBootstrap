package com.ferjuarez.bottomnavigationbootstrap.data;

import com.ferjuarez.bottomnavigationbootstrap.data.shared.SharedContract;
import com.ferjuarez.bottomnavigationbootstrap.models.account.Account;
import com.ferjuarez.bottomnavigationbootstrap.models.account.AccountParser;
import com.ferjuarez.bottomnavigationbootstrap.utils.Utils;
import java.util.HashMap;
import java.util.Map;
import io.reactivex.Observable;

public class DataManager implements DataContract {

    protected final APIServices apiServices;
    private final BottomNavigationDatabase roomDatabase;
    protected final SharedContract sharedManager;

    public DataManager(APIServices apiServices, SharedContract sharedManager, BottomNavigationDatabase roomDatabase) {
        this.apiServices = apiServices;
        this.sharedManager = sharedManager;
        this.roomDatabase = roomDatabase;
    }

    @Override
    public Observable<Account> login(String username, String password) {
        return apiServices.login(createParams(username, password)).toObservable()
                .map(accountParser -> saveResponse(accountParser));
    }

    @Override
    public Observable<Account> loginMock(String email, String password) {
        Account account = new Account(email, password, "AAA");
        saveAccount(account);
        return Observable.just(account);
    }

    @Override
    public BottomNavigationDatabase getRoomDatabase() {
        return roomDatabase;
    }

    private Account saveResponse(AccountParser response) {
        Account account = response.getItem();
        saveAccount(account);
        saveToken(response.getAccessToken());
        return account;
    }

    private void saveAccount(Account account) {
        sharedManager.setLoggedIn(true);
        sharedManager.saveAccount(account);
    }

    private void saveToken(String accessToken) {
        sharedManager.saveAccessToken(accessToken);
    }

    private Map<String, String> createParams(String username, String password) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", username);
        requestBody.put("password", Utils.md5(password));
        return requestBody;
    }

    private Map<String, String> createParamsForRestorePass(String password, String code) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("authtoken", code);
        requestBody.put("newAuthtoken", password);
        return requestBody;
    }

    private Map<String, String> createParamsForFirebase(String token){
        Map<String, String> params = new HashMap<>();
        params.put("gcmRegistrationId", token);
        return params;
    }


}