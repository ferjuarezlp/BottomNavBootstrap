package com.ferjuarez.bottomnavigationbootstrap.models.account;

import com.ferjuarez.bottomnavigationbootstrap.models.base.ParserContract;

public class AccountParser implements ParserContract<Account> {
    public String access_token;
    public Account results;

    @Override
    public Account getItem() {
        return results;
    }

    public String getAccessToken(){
        return access_token;
    }

}