package com.ferjuarez.bottomnavigationbootstrap.injection.main;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import com.ferjuarez.bottomnavigationbootstrap.MainActivity;
import com.ferjuarez.bottomnavigationbootstrap.injection.main.home.HomeFragmentComponent;
import com.ferjuarez.bottomnavigationbootstrap.injection.main.settings.SettingsFragmentComponent;
import com.ferjuarez.bottomnavigationbootstrap.injection.main.sync.SyncFragmentComponent;
import com.ferjuarez.bottomnavigationbootstrap.injection.util.ScopeActivity;
import com.ferjuarez.bottomnavigationbootstrap.ui.adapter.CustomViewPagerAdapter;
import com.ferjuarez.bottomnavigationbootstrap.ui.home.HomeContract;
import com.ferjuarez.bottomnavigationbootstrap.ui.home.HomeFragment;
import com.ferjuarez.bottomnavigationbootstrap.ui.settings.SettingsContract;
import com.ferjuarez.bottomnavigationbootstrap.ui.settings.SettingsFragment;
import com.ferjuarez.bottomnavigationbootstrap.ui.sync.SyncContract;
import com.ferjuarez.bottomnavigationbootstrap.ui.sync.SyncFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {HomeFragmentComponent.class, SyncFragmentComponent.class, SettingsFragmentComponent.class})
public abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivityInjectorFactory(
            MainActivityComponent.Builder builder);

    @Provides
    @ScopeActivity
    static FragmentManager provideFragmentManager(MainActivity mainActivity) {
        return mainActivity.getSupportFragmentManager();
    }

    @Provides
    @ScopeActivity
    static CustomViewPagerAdapter providerViewPagerAdapter(FragmentManager fragmentManager) {
        return new CustomViewPagerAdapter(fragmentManager);
    }

    @Provides
    @ScopeActivity
    static HomeContract.View provideHomeFragment() {
        return new HomeFragment();
    }

    @Provides
    @ScopeActivity
    static SyncContract.View provideSyncFragment() {
        return new SyncFragment();
    }

    @Provides
    @ScopeActivity
    static SettingsContract.View provideSettingsFragment() {
        return new SettingsFragment();
    }

}