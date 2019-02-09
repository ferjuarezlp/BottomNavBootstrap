package com.ferjuarez.bottomnavigationbootstrap.models.login;


import com.ferjuarez.bottomnavigationbootstrap.models.account.Account;

public class SignInResponse {
    private Account account;
    private String accessToken;

    private SignInResponse(Builder builder) {
        account = builder.account;
        accessToken = builder.accessToken;
    }

    public Account getAccount() {
        return account;
    }

    public String getAccessToken() { return accessToken; }

    public static final class Builder {
        private Account account;
        private String accessToken;

        public Builder() {
        }

        public Builder account(Account val) {
            account = val;
            return this;
        }

        public Builder accessToken(String val) {
            accessToken = val;
            return this;
        }


        public SignInResponse build() {
            return new SignInResponse(this);
        }
    }
}