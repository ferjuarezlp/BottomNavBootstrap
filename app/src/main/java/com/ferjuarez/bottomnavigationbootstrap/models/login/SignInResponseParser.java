package com.ferjuarez.bottomnavigationbootstrap.models.login;

import com.ferjuarez.bottomnavigationbootstrap.models.account.AccountParser;
import com.ferjuarez.bottomnavigationbootstrap.models.base.ParserContract;

public class SignInResponseParser implements ParserContract<SignInResponse> {
    public AccountParser account;

    @Override
    public SignInResponse getItem() {
        return new SignInResponse.Builder()
                .account(account.getItem())
                .accessToken(account.getAccessToken())
                .build();
    }
}
