package com.ferjuarez.bottomnavigationbootstrap.injection.login;

import android.app.Activity;
import com.ferjuarez.bottomnavigationbootstrap.injection.util.ScopeActivity;
import com.ferjuarez.bottomnavigationbootstrap.ui.login.LoginActivity;
import com.ferjuarez.bottomnavigationbootstrap.ui.login.LoginContract;
import com.ferjuarez.bottomnavigationbootstrap.ui.login.LoginFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = { LoginFragmentComponent.class })
public abstract class LoginActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(LoginActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindLoginActivityInjectorFactory(
            LoginActivityComponent.Builder builder);

    @Provides
    @ScopeActivity
    static LoginContract.View provideLoginFragment(){
        return new LoginFragment();
    }

}