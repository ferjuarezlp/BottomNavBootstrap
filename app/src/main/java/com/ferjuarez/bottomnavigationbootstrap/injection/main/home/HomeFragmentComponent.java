package com.ferjuarez.bottomnavigationbootstrap.injection.main.home;

import com.ferjuarez.bottomnavigationbootstrap.injection.util.ScopeFragment;
import com.ferjuarez.bottomnavigationbootstrap.ui.home.HomeFragment;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScopeFragment
@Subcomponent(modules = { HomeFragmentModule.class })
public interface HomeFragmentComponent extends AndroidInjector<HomeFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<HomeFragment>{}
}