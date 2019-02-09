package com.ferjuarez.bottomnavigationbootstrap.injection.app;

import com.ferjuarez.bottomnavigationbootstrap.BottomNavigationApp;
import com.ferjuarez.bottomnavigationbootstrap.injection.login.LoginActivityModule;
import com.ferjuarez.bottomnavigationbootstrap.injection.main.MainActivityModule;
import com.ferjuarez.bottomnavigationbootstrap.injection.splash.SplashActivityModule;
import com.ferjuarez.bottomnavigationbootstrap.ui.customviews.inputText.InputTextView;
import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class, SplashActivityModule.class, LoginActivityModule.class, MainActivityModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder app(BottomNavigationApp bottomNavigationApp);
        AppComponent build();
    }

    void inject(BottomNavigationApp bottomNavigationApp);


    void inject(InputTextView inputTextView);
}