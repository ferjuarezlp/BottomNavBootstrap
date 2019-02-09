package com.ferjuarez.bottomnavigationbootstrap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.ferjuarez.bottomnavigationbootstrap.ui.adapter.CustomViewPagerAdapter;
import com.ferjuarez.bottomnavigationbootstrap.ui.base.BaseActivity;
import com.ferjuarez.bottomnavigationbootstrap.ui.customviews.CustomViewPager;
import com.ferjuarez.bottomnavigationbootstrap.ui.home.HomeContract;
import com.ferjuarez.bottomnavigationbootstrap.ui.login.LoginActivity;
import com.ferjuarez.bottomnavigationbootstrap.ui.settings.SettingsContract;
import com.ferjuarez.bottomnavigationbootstrap.ui.sync.SyncContract;
import com.ferjuarez.bottomnavigationbootstrap.utils.BottomNavigationViewHelper;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseActivity implements HasSupportFragmentInjector, ViewPager.OnPageChangeListener,
        BottomNavigationView.OnNavigationItemSelectedListener, OnMainFragmentsListener {

    private static final int PAGE_HOME = 0;
    private static final int PAGE_SYNC = 1;
    private static final int PAGE_SETTINGS = 2;

    @Inject
    DispatchingAndroidInjector<Fragment> injector;
    @Inject
    CustomViewPagerAdapter viewPagerAdapter;
    @Inject
    HomeContract.View homeView;
    @Inject
    SyncContract.View syncView;
    @Inject
    SettingsContract.View settingsView;

    @BindView(R.id.view_pager)
    CustomViewPager viewPager;
    @BindView(R.id.nav_view)
    BottomNavigationView bottomNavView;
    private Unbinder unbinder;

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void showFragment(Fragment fragment) {
        replaceFragment(fragment, R.id.container_main, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        setUp();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.imageButtonSearch)
    public void onSearchClick() {
        showSearchView();
    }

    private void setUp() {
        bottomNavView.setOnNavigationItemSelectedListener(this);
        BottomNavigationViewHelper.removeShiftMode(bottomNavView);
        setUpFragments();
        addBackChangedListener();
    }

    private void setUpFragments() {
        viewPager.setOffscreenPageLimit(2);
        viewPagerAdapter.addFragment(homeView.getFragment());
        viewPagerAdapter.addFragment(syncView.getFragment());
        viewPagerAdapter.addFragment(settingsView.getFragment());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        viewPager.setPagingEnabled(true);
        switch (menuItem.getItemId()) {
            case R.id.action_home:
                viewPager.setCurrentItem(PAGE_HOME);
                return true;
            case R.id.action_sync:
                viewPager.setCurrentItem(PAGE_SYNC);
                return true;
            case R.id.action_settings:
                viewPager.setCurrentItem(PAGE_SETTINGS);
                return true;
        }
        return true;
    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case PAGE_HOME:
                bottomNavView.setSelectedItemId(R.id.action_home);
                break;
            case PAGE_SYNC:
                bottomNavView.setSelectedItemId(R.id.action_sync);
                break;
            case PAGE_SETTINGS:
                bottomNavView.setSelectedItemId(R.id.action_settings);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void showHomeView() {

    }

    @Override
    public void showSearchView() {

    }

    @Override
    public void showLoginView() {
        LoginActivity.start(this);
        finish();
    }

    @Override
    public void goBack() {
        hideKeyboard();
        popBackStack();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return injector;
    }

    public void addBackChangedListener(){
        getSupportFragmentManager().addOnBackStackChangedListener(
                () -> {
                    FragmentManager fm = getSupportFragmentManager();

                    if (fm != null) {
                        int backStackCount = fm.getBackStackEntryCount();
                        if (backStackCount == 0) {
                            Log.e("","");
                        } else {
                            Log.e("","");
                        }
                    }
                });
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
