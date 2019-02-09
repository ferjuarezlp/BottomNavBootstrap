package com.ferjuarez.bottomnavigationbootstrap.ui.sync;

import android.annotation.SuppressLint;
import android.util.Log;
import com.ferjuarez.bottomnavigationbootstrap.data.DataContract;
import com.ferjuarez.bottomnavigationbootstrap.data.articles.ArticlesDataContract;
import com.ferjuarez.bottomnavigationbootstrap.data.network.errors.ErrorResponse;
import com.ferjuarez.bottomnavigationbootstrap.data.shared.SharedContract;
import com.ferjuarez.bottomnavigationbootstrap.utils.UtilDate;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")
public class SyncPresenter implements SyncContract.Presenter {

    private static final String TAG = SyncPresenter.class.getSimpleName();

    private final SyncContract.View syncView;
    private final SharedContract sharedManager;
    private final ArticlesDataContract articlesDataManager;
    private final DataContract dataManager;
    private final UtilDate utilDate;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public SyncPresenter(SyncContract.View syncView,
                         ArticlesDataContract articlesDataManager, SharedContract sharedManager, DataContract dataManager, UtilDate utilDate) {
        this.syncView = syncView;
        this.articlesDataManager = articlesDataManager;
        this.sharedManager = sharedManager;
        this.dataManager = dataManager;
        this.utilDate = utilDate;
    }

    public void error(){
        Log.e("","");
    }

    private void error(ErrorResponse errorResponse) {
        if(errorResponse.getCode() == 401) {
            articlesDataManager.clearAll()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((Boolean)->{
                        sharedManager.clearAll();
                        syncView.showExpirationError();
                    }, (Error)->error());
        }
        Log.e(TAG, "ERROR: " + errorResponse.toString());
    }

    @Override
    public void doDispose() {
        compositeDisposable.dispose();
    }

    @Override
    public void cancelAll() {
    }

}
