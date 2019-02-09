package com.ferjuarez.bottomnavigationbootstrap.injection.splash;

import com.ferjuarez.bottomnavigationbootstrap.injection.util.ScopeActivity;
import com.ferjuarez.bottomnavigationbootstrap.ui.splash.SplashActivity;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScopeActivity
@Subcomponent(modules = { SplashActivityModule.class })
public interface SplashActivityComponent extends AndroidInjector<SplashActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SplashActivity> {}
}