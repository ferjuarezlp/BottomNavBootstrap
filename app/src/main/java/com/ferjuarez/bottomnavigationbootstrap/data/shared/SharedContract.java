package com.ferjuarez.bottomnavigationbootstrap.data.shared;

import com.ferjuarez.bottomnavigationbootstrap.models.account.Account;

public interface SharedContract {

    void saveAccount(Account account);

    void saveAccessToken(String accessToken);

    Account getAccount() throws SharedException;

    String getAccessToken() throws SharedException;

    boolean isLoggedIn();

    void setLoggedIn(boolean loggedIn);

    void setSynchronized(boolean value);

    boolean isSynchronized() throws SharedException;

    void setLookupSynchronized(boolean value);

    boolean isLookupSynchronized() throws SharedException;

    void setCatalogSyncDate(String value);

    String getCatalogSyncDate();

    void saveCurrentTravel(int currentTravelPosition);

    int getCurrentTravel();

    void clearAll();

}