package com.ferjuarez.bottomnavigationbootstrap.injection.main;

import com.ferjuarez.bottomnavigationbootstrap.MainActivity;
import com.ferjuarez.bottomnavigationbootstrap.injection.main.home.HomeFragmentModule;
import com.ferjuarez.bottomnavigationbootstrap.injection.main.settings.SettingsFragmentModule;
import com.ferjuarez.bottomnavigationbootstrap.injection.main.sync.SyncFragmentModule;
import com.ferjuarez.bottomnavigationbootstrap.injection.util.ScopeActivity;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScopeActivity
@Subcomponent(modules = {MainActivityModule.class, HomeFragmentModule.class, SyncFragmentModule.class, SettingsFragmentModule.class})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {
    }
}