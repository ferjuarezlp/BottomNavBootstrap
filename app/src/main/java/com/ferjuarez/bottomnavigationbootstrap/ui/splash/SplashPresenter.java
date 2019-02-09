package com.ferjuarez.bottomnavigationbootstrap.ui.splash;

import com.ferjuarez.bottomnavigationbootstrap.data.DataContract;
import com.ferjuarez.bottomnavigationbootstrap.data.shared.SharedContract;
import com.ferjuarez.bottomnavigationbootstrap.data.shared.SharedException;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SplashPresenter implements SplashContract.Presenter {
    private static final int SPLASH_DELAY_SECONDS = 3;

    private final SplashContract.View splashView;
    private final DataContract dataManager;
    private final SharedContract sharedManager;
    private Observable<Long> delayObservable;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public SplashPresenter(SplashContract.View splashView, DataContract dataManager, SharedContract sharedManager) {
        this.splashView = splashView;
        this.dataManager = dataManager;
        this.sharedManager = sharedManager;
        prepareDelayObservable();
    }

    private void prepareDelayObservable() {
        delayObservable = Observable.timer(SPLASH_DELAY_SECONDS, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(compositeDisposable::add)
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void getAllLookups() {
    }

    @Override
    public void checkIsLogged(boolean demoMode) {
        if(demoMode){
            splashView.startLoginActivity();
        } else {
            checkIsLogged();
        }
    }

    @Override
    public void checkIsLogged() {
        if (!isLoggedIn()) {
            splashView.startLoginActivity();
            return;
        }
        try {
            if(!sharedManager.isLookupSynchronized()){
                getAllLookups();
            } else {
                startWithDelay();
            }
        } catch (SharedException e) {
            e.printStackTrace();
        }

    }

    private void startWithDelay(){
        if (isLoggedIn()) {
            delayObservable.subscribe(
                    timer -> splashView.startMainActivity(),
                    throwable -> splashView.startMainActivity());
            return;
        }

        Observable.just(delayObservable)
                .subscribe(timer -> {
                    splashView.startMainActivity();
                }, this::error);
    }

    private void error(Throwable throwable) {
        splashView.startLoginActivity();
    }

    private boolean isLoggedIn() {
        return sharedManager.isLoggedIn();
    }

    private boolean tokenExists() {
        try {
            if(!sharedManager.getAccessToken().isEmpty()){
                return true;
            }
        } catch (SharedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void doDispose() {
        compositeDisposable.dispose();
    }
}