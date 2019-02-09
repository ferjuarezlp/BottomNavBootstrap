package com.ferjuarez.bottomnavigationbootstrap.ui.login;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ferjuarez.bottomnavigationbootstrap.R;
import com.ferjuarez.bottomnavigationbootstrap.ui.base.BaseFragment;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

public class LoginFragment extends BaseFragment implements LoginContract.View {

    @Inject
    LoginContract.Presenter presenter;

    private Unbinder unbinder;
    private OnLoginFragmentsListener listener;

    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.linearLoading)
    LinearLayout linearLoading;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if (!(context instanceof Activity))
            throw new RuntimeException("ERROR: you should implement OnLoginFragmentsListener");
        listener = (OnLoginFragmentsListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, root);
        setUp();
        return root;
    }

    @Override
    public void setUp() {
        //editTextUser.setOnFocusChangeListener((v, hasFocus) -> validationText(hasFocus));
        //editTextPass.setOnEditorActionListener((v, actionId, event) -> startLoginWithKeyboard(actionId));
    }

    private void showLoading(){
        linearLoading.setVisibility(View.VISIBLE);
    }

    private void hideLoading(){
        linearLoading.setVisibility(View.GONE);
    }

    private void validationQuickEmail(boolean hasFocus) {
        //if (!hasFocus)
        //    editTextUser.isEmailValid();
    }

    private void validationText(boolean hasFocus) {
        //if (!hasFocus)
            //editTextUser.isTextValid();
    }

    private boolean startLoginWithKeyboard(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_DONE)
            startLogin();
        hideKeyBoard();
        return true;
    }

    @OnClick(R.id.btnLogin)
    public void onLoginClick() {
        startLogin();
    }

    private void startLogin() {
        if (verifyEmailAndPassword())
            hideKeyBoard();
            showLoading();
            //presenter.startLogin(editTextUser.getText(), editTextPass.getText());
    }

    private boolean verifyEmailAndPassword() {
        return true; //editTextUser.isTextValid() && editTextPass.isPasswordValid();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.doDispose();
        disposeInputTextViews();
        unbinder.unbind();
    }

    private void disposeInputTextViews() {
        /*editTextUser.setOnFocusChangeListener(null);
        editTextUser.doDispose();
        editTextPass.doDispose();*/
    }

    /**
     * Implementation LoginContract.View
     */
    @Override
    public void changeVisibilityLoading(boolean visibility) {
        if (visibility) {
            //this.showLoading();
            return;
        }
    }

    @Override
    public void showError(String error) {
        hideLoading();
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showExpirationError() {

    }


    @Override
    public void showError(int errorMessage) {
        hideLoading();
        Toast.makeText(getContext(), getString(errorMessage), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startMainActivity(){
        listener.goToMainActivity();
    }

    @Override
    public Fragment getFragment() {
        return this;
    }


}