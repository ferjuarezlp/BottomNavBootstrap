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
import android.widget.Toast;
import com.ferjuarez.bottomnavigationbootstrap.R;
import com.ferjuarez.bottomnavigationbootstrap.ui.base.BaseFragment;
import com.ferjuarez.bottomnavigationbootstrap.ui.customviews.inputText.InputTextView;
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

    @BindView(R.id.editTextUser)
    InputTextView editTextUser;
    @BindView(R.id.editTextPass)
    InputTextView editTextPass;

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
        editTextUser.setOnFocusChangeListener((v, hasFocus) -> validationQuickEmail(hasFocus));
        editTextPass.setOnEditorActionListener((v, actionId, event) -> startLoginWithKeyboard(actionId));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.doDispose();
        disposeInputTextViews();
        unbinder.unbind();
    }

    private void validationQuickEmail(boolean hasFocus) {
        if (!hasFocus)
            editTextUser.isEmailValid();
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
            presenter.startLogin(editTextUser.getText(), editTextPass.getText());
    }

    private boolean verifyEmailAndPassword() {
        return editTextUser.isEmailValid() && editTextPass.isPasswordValid();
    }

    private void disposeInputTextViews() {
        editTextUser.setOnFocusChangeListener(null);
        editTextUser.doDispose();
        editTextPass.doDispose();
    }

    /**
     * Implementation LoginContract.View
     */
    @Override
    public void changeVisibilityLoading(boolean visibility) {
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showExpirationError() {

    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public void startMainActivity() {
        listener.goToMainActivity();
    }

    @Override
    public void showError(int errorMessage) {

    }
}