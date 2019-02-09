package com.ferjuarez.bottomnavigationbootstrap.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.ferjuarez.bottomnavigationbootstrap.MainActivity;
import com.ferjuarez.bottomnavigationbootstrap.R;
import com.ferjuarez.bottomnavigationbootstrap.ui.base.BaseActivity;
import javax.inject.Inject;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class LoginActivity extends BaseActivity implements HasSupportFragmentInjector, OnLoginFragmentsListener {

    private static final String LOGIN_ACTION = "login_action";
    private Unbinder unbinder;
    @Inject
    DispatchingAndroidInjector<Fragment> injector;
    @Inject
    LoginContract.View loginFragmentView;


    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
        setUp();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void setUp() {
        showFragment(getFragment(), false);
    }

    private void showFragment(Fragment fragment, boolean stack) {
        replaceFragment(fragment, R.id.content_login, stack);
    }

    private Fragment getFragment() {
        return loginFragmentView.getFragment();
    }

    private void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return injector;
    }

    @Override
    public void onBackPressed() {
        if (!popBackStack()) finish();
    }

    /**
     * Implementation OnLoginFragmentsListener
     */
    @Override
    public void goToMainActivity() {
        MainActivity.start(this);
    }

    @Override
    public void goBackToLogin() {
        if (!popBackStack()) finish();
    }


    public enum LoginAction {
        LOGIN, SIGN_UP, DEMO, PROFILE
    }


}