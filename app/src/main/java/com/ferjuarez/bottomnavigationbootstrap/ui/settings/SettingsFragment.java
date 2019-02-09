package com.ferjuarez.bottomnavigationbootstrap.ui.settings;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.ferjuarez.bottomnavigationbootstrap.OnMainFragmentsListener;
import com.ferjuarez.bottomnavigationbootstrap.R;
import com.ferjuarez.bottomnavigationbootstrap.ui.base.BaseFragment;
import com.ferjuarez.bottomnavigationbootstrap.utils.UtilDate;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

public class SettingsFragment extends BaseFragment implements SettingsContract.View {
    public static final String TAG = SettingsFragment.class.getSimpleName();

    @Inject
    SettingsContract.Presenter presenter;
    @Inject
    UtilDate utilDate;

    private Unbinder unbinder;
    private OnMainFragmentsListener listener;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if (!(context instanceof Activity))
            throw new RuntimeException("Error: you should implement OnMainFragmentsListener");
        listener = (OnMainFragmentsListener) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUp();
        return view;
    }


    @Override
    protected void setUp() {
        presenter.getTravels();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            if(presenter != null){
                setUp();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.doDispose();
        unbinder.unbind();
    }

    @OnClick(R.id.btnSave)
    public void onSaveClick() {
    }

    @OnClick(R.id.btnCloseSession)
    public void onCloseSessionClick() {
        presenter.closeSession();
    }

    @Override
    public void changeVisibilityLoading(boolean visibility) {
        // nothing to do here
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showExpirationError() {

    }

    @Override
    public void onCloseSession() {
        listener.showLoginView();
    }

    @Override
    public Fragment getFragment() {
        return this;
    }


}