package com.ferjuarez.bottomnavigationbootstrap.ui.sync;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ferjuarez.bottomnavigationbootstrap.OnMainFragmentsListener;
import com.ferjuarez.bottomnavigationbootstrap.R;
import com.ferjuarez.bottomnavigationbootstrap.ui.base.BaseFragment;
import com.ferjuarez.bottomnavigationbootstrap.utils.UtilDate;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SyncFragment extends BaseFragment implements SyncContract.View {
    public static final String TAG = SyncFragment.class.getSimpleName();

    @Inject
    SyncContract.Presenter presenter;
    @Inject
    UtilDate utilDate;


    @BindView(R.id.linearState)
    LinearLayout linearState;
    @BindView(R.id.textViewState)
    TextView textViewState;
    @BindView(R.id.progressView)
    ProgressBar progressView;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Observable<Long> delayObservable;

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
        View view = inflater.inflate(R.layout.fragment_sync, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUp();
        return view;
    }


    @Override
    protected void setUp() {
        prepareDelayObservable();
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

    @Override
    public void changeVisibilityLoading(boolean visibility) {
        // nothing to do here
    }

    @OnClick(R.id.btnSync)
    public void onSyncClick() {
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showExpirationError() {

    }

    private void prepareDelayObservable() {
        delayObservable = Observable.timer(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(compositeDisposable::add)
                .observeOn(AndroidSchedulers.mainThread());
    }

    private void error(Throwable throwable){
        Log.e("","");
    }


    @Override
    public Fragment getFragment() {
        return this;
    }


}