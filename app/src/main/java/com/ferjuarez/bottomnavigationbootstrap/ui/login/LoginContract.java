package com.ferjuarez.bottomnavigationbootstrap.ui.login;

import com.ferjuarez.bottomnavigationbootstrap.ui.base.ContractPresenter;
import com.ferjuarez.bottomnavigationbootstrap.ui.base.ContractView;

public interface LoginContract {

    interface View extends ContractView {
        void startMainActivity();
        void showError(int errorMessage);
    }

    interface Presenter extends ContractPresenter {
        void startLogin(String email, String password);
        void getAllLookups();
    }
}