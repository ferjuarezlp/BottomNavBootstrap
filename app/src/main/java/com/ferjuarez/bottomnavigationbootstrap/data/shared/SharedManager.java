package com.ferjuarez.bottomnavigationbootstrap.data.shared;

import android.content.SharedPreferences;
import android.util.Log;
import com.ferjuarez.bottomnavigationbootstrap.models.account.Account;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import javax.inject.Inject;

public class SharedManager implements SharedContract {

    private static final String TAG = SharedManager.class.getSimpleName();
    private static final String KEY_ACCOUNT = "profile";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_TYPES = "types";
    private static final String KEY_SYNCHRONIZED = "synchronized";
    private static final String KEY_LOGGED_IN = "logged_in";
    private static final String KEY_CURRENT_TRAVEL = "current_travel";
    private static final String KEY_FILTER = "filter";
    private static final String KEY_SYNCHRONIZED_LOOKUPS = "synchronized_lookups";
    private static final String KEY_CATALOG_SYNC_DATE = "catalog_sync_date";

    protected final SharedPreferences shared;

    @Inject
    public SharedManager(SharedPreferences sharedPreferences) {
        this.shared = sharedPreferences;
    }

    @Override
    public void saveAccount(Account account) {
        shared.edit().putString(KEY_ACCOUNT, account.toString()).apply();
        log("Account saved " + account.toString());
    }

    @Override
    public void saveAccessToken(String accessToken) {
        shared.edit().putString(KEY_ACCESS_TOKEN, accessToken).apply();
        log("AccessToken saved " + accessToken);
    }

    @Override
    public Account getAccount() throws SharedException {
        String json = shared.getString(KEY_ACCOUNT, "");
        if (json.isEmpty())
            throw new SharedException("Account not found");
        return new Gson().fromJson(json, Account.class);
    }

    @Override
    public String getAccessToken() throws SharedException {
        String json = shared.getString(KEY_ACCESS_TOKEN, "");
        if (json.isEmpty())
            throw new SharedException("AccessToken not found");
        return json;
    }

    @Override
    public boolean isLoggedIn() {
        return shared.getBoolean(KEY_LOGGED_IN, false);
    }

    @Override
    public void setLoggedIn(boolean loggedIn) {
        shared.edit().putBoolean(KEY_LOGGED_IN, loggedIn).apply();
    }

    @Override
    public boolean isSynchronized() throws SharedException {
        return shared.getBoolean(KEY_SYNCHRONIZED, false);
    }

    @Override
    public void setSynchronized(boolean value) {
        shared.edit().putBoolean(KEY_SYNCHRONIZED, value).apply();
    }

    @Override
    public void saveCurrentTravel(int currentTravelPosition) {
        shared.edit().putInt(KEY_CURRENT_TRAVEL, currentTravelPosition).apply();
        log("Lookups saved " + currentTravelPosition);
    }

    @Override
    public int getCurrentTravel(){
        return shared.getInt(KEY_CURRENT_TRAVEL, 0);
    }

    @Override
    public boolean isLookupSynchronized() throws SharedException {
        return shared.getBoolean(KEY_SYNCHRONIZED_LOOKUPS, false);
    }

    @Override
    public void setCatalogSyncDate(String value) {
        shared.edit().putString(KEY_CATALOG_SYNC_DATE, value).apply();
        log("Sync date saved " + value);
    }

    @Override
    public String getCatalogSyncDate() {
        return shared.getString(KEY_CATALOG_SYNC_DATE, "");
    }

    @Override
    public void setLookupSynchronized(boolean value) {
        shared.edit().putBoolean(KEY_SYNCHRONIZED_LOOKUPS, value).apply();
    }

    @Override
    public void clearAll() {
        shared.edit().clear().apply();
    }

    private void log(String value) {
        Log.i(TAG, value);
    }
}
