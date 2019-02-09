package com.ferjuarez.bottomnavigationbootstrap.injection.login;

import com.ferjuarez.bottomnavigationbootstrap.injection.util.ScopeActivity;
import com.ferjuarez.bottomnavigationbootstrap.ui.login.LoginActivity;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScopeActivity
@Subcomponent(modules = { LoginActivityModule.class, LoginFragmentModule.class})
public interface LoginActivityComponent extends AndroidInjector<LoginActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<LoginActivity>{}
}