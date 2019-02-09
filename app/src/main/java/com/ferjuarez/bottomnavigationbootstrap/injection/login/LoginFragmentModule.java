package com.ferjuarez.bottomnavigationbootstrap.injection.login;

import android.support.v4.app.Fragment;

import com.ferjuarez.bottomnavigationbootstrap.data.DataContract;
import com.ferjuarez.bottomnavigationbootstrap.injection.util.ScopeFragment;
import com.ferjuarez.bottomnavigationbootstrap.ui.login.LoginContract;
import com.ferjuarez.bottomnavigationbootstrap.ui.login.LoginFragment;
import com.ferjuarez.bottomnavigationbootstrap.ui.login.LoginPresenter;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class LoginFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(LoginFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindLoginFragmentInjectorFactory(
            LoginFragmentComponent.Builder builder);

    @Provides
    @ScopeFragment
    static LoginContract.Presenter provideLoginPresenter(LoginContract.View loginFragmentView,
                                                         DataContract dataManager) {
        return new LoginPresenter(loginFragmentView, dataManager);
    }
}