package com.ferjuarez.bottomnavigationbootstrap.ui.base;

import android.support.v4.app.Fragment;

public interface ContractView {

    void changeVisibilityLoading(boolean visibility);

    void showError(String error);

    void showExpirationError();

    Fragment getFragment();
}