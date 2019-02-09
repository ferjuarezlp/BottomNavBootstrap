package com.ferjuarez.bottomnavigationbootstrap.injection.main.settings;

import com.ferjuarez.bottomnavigationbootstrap.injection.util.ScopeFragment;
import com.ferjuarez.bottomnavigationbootstrap.ui.settings.SettingsFragment;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScopeFragment
@Subcomponent(modules = { SettingsFragmentModule.class })
public interface SettingsFragmentComponent extends AndroidInjector<SettingsFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SettingsFragment>{}
}