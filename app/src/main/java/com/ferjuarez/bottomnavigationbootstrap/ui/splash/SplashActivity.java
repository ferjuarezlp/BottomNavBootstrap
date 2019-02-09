package com.ferjuarez.bottomnavigationbootstrap.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.ferjuarez.bottomnavigationbootstrap.MainActivity;
import com.ferjuarez.bottomnavigationbootstrap.R;
import com.ferjuarez.bottomnavigationbootstrap.ui.login.LoginActivity;
import javax.inject.Inject;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    @Inject
    SplashContract.Presenter presenter;
    private Unbinder unbinder;

    private static final String DEMO_MODE_KEY = "demo_mode";

    public static void start(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        unbinder = ButterKnife.bind(this);

        setUp();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.doDispose();
        unbinder.unbind();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void setUp() {
    }

    @Override
    public void startLoginActivity() {
        LoginActivity.start(this);
    }

    @Override
    public void startMainActivity() {
        MainActivity.start(this);
    }


}