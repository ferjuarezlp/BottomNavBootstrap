package com.ferjuarez.bottomnavigationbootstrap;

import android.app.Activity;
import com.ferjuarez.bottomnavigationbootstrap.injection.app.AppComponent;
import com.ferjuarez.bottomnavigationbootstrap.injection.app.DaggerAppComponent;

import javax.inject.Inject;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import android.support.multidex.MultiDexApplication;

public class BottomNavigationApp extends MultiDexApplication implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> injector;
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setUpInjection();
    }

    private void setUpInjection(){
        appComponent = DaggerAppComponent
                .builder()
                .app(this)
                .build();
        appComponent.inject(this);
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return injector;
    }
}