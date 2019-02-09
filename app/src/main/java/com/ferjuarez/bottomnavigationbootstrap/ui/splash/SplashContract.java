package com.ferjuarez.bottomnavigationbootstrap.ui.splash;

public interface SplashContract {
    interface View {

        void setUp();

        void startLoginActivity();

        void startMainActivity();

    }

    interface Presenter {

        void getAllLookups();

        void checkIsLogged(boolean demoMode);

        void checkIsLogged();

        void doDispose();
    }
}