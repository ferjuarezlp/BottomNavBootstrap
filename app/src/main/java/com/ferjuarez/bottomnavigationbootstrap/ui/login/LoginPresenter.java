package com.ferjuarez.bottomnavigationbootstrap.ui.login;

import com.ferjuarez.bottomnavigationbootstrap.data.DataContract;
import com.ferjuarez.bottomnavigationbootstrap.ui.base.BasePresenter;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter implements LoginContract.Presenter {
    private static final String TAG = LoginPresenter.class.getSimpleName();

    private final LoginContract.View loginFragmentView;
    private final DataContract dataManager;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public LoginPresenter(LoginContract.View loginFragmentView, DataContract dataManager) {
        this.loginFragmentView = loginFragmentView;
        this.dataManager = dataManager;
    }

    @Override
    public void startLogin(String username, String password) {
        login(username, password);
    }

    @Override
    public void getAllLookups() {
        loginFragmentView.startMainActivity();
        /*dataManager.getAllLookups()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(str -> {
                    loginFragmentView.startMainActivity();
                }, (throwable) -> error((Throwable)throwable));*/
    }

    private void loginMock(String username, String password){
        if(username.equals("test@test.com") && password.equals("test")){
            changeVisibilityLoading(true);
            dataManager.loginMock(username, password)
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe(compositeDisposable::add)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete(() -> changeVisibilityLoading(false))
                    .subscribe((account) -> loginFragmentView.startMainActivity(), this::error);
        } else {
            error(new Throwable());
        }
    }

    private void login(String username, String password) {
        changeVisibilityLoading(true);
        dataManager.login(username, password)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(compositeDisposable::add)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> changeVisibilityLoading(false))
                .subscribe((account) -> getAllLookups(), this::error);
    }

    private void error(Throwable throwable) {
        loginFragmentView.changeVisibilityLoading(false);
        loginFragmentView.showError(throwable.getMessage());
    }

    @Override
    public void doDispose() {
        compositeDisposable.dispose();
    }

    private void changeVisibilityLoading(boolean visibility) {
        loginFragmentView.changeVisibilityLoading(visibility);
    }

}