package com.ferjuarez.bottomnavigationbootstrap.ui.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected void replaceFragment(Fragment fragment, int layoutId, boolean addToStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (addToStack)
            transaction.addToBackStack(fragment.getClass().getName());
        //transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(layoutId, fragment);
        transaction.commit();
    }

    protected boolean popBackStack() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            return true;
        }
        return false;
    }

}