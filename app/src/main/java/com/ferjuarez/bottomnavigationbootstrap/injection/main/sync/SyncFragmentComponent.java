package com.ferjuarez.bottomnavigationbootstrap.injection.main.sync;

import com.ferjuarez.bottomnavigationbootstrap.injection.util.ScopeFragment;
import com.ferjuarez.bottomnavigationbootstrap.ui.sync.SyncFragment;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScopeFragment
@Subcomponent(modules = { SyncFragmentModule.class })
public interface SyncFragmentComponent extends AndroidInjector<SyncFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SyncFragment>{}
}