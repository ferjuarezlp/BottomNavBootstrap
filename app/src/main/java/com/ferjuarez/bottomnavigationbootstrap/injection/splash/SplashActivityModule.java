package com.ferjuarez.bottomnavigationbootstrap.injection.splash;

import android.app.Activity;
import com.ferjuarez.bottomnavigationbootstrap.data.DataContract;
import com.ferjuarez.bottomnavigationbootstrap.data.shared.SharedContract;
import com.ferjuarez.bottomnavigationbootstrap.injection.util.ScopeActivity;
import com.ferjuarez.bottomnavigationbootstrap.ui.splash.SplashActivity;
import com.ferjuarez.bottomnavigationbootstrap.ui.splash.SplashContract;
import com.ferjuarez.bottomnavigationbootstrap.ui.splash.SplashPresenter;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module
public abstract class SplashActivityModule {

    @Binds
    abstract SplashContract.View provideSplashActivity(SplashActivity activity);

    @Binds
    @IntoMap
    @ActivityKey(SplashActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindActivitySplashInjectorFactory(
            SplashActivityComponent.Builder builder);

    @Provides
    @ScopeActivity
    static SplashContract.Presenter provideSplashPresenter(SplashContract.View activityView,
                                                           DataContract dataManager,
                                                           SharedContract sharedManager) {
        return new SplashPresenter(activityView, dataManager, sharedManager);
    }
}