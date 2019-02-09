package com.ferjuarez.bottomnavigationbootstrap.ui.settings;

import android.annotation.SuppressLint;
import android.util.Log;
import com.ferjuarez.bottomnavigationbootstrap.data.DataContract;
import com.ferjuarez.bottomnavigationbootstrap.data.articles.ArticlesDataContract;
import com.ferjuarez.bottomnavigationbootstrap.data.shared.SharedContract;
import com.ferjuarez.bottomnavigationbootstrap.data.shared.SharedException;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")
public class SettingsPresenter implements SettingsContract.Presenter {

    private static final String TAG = SettingsPresenter.class.getSimpleName();

    private final SettingsContract.View settingsView;
    private final SharedContract sharedManager;
    private final ArticlesDataContract articlesDataManager;
    private final DataContract dataManager;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public SettingsPresenter(SettingsContract.View settingsView,
                             ArticlesDataContract articlesDataManager, DataContract dataManager, SharedContract sharedManager) {
        this.settingsView = settingsView;
        this.articlesDataManager = articlesDataManager;
        this.dataManager = dataManager;
        this.sharedManager = sharedManager;
    }

    public void error(){
        Log.e("","");
    }

    private void error(Throwable throwable) {
        Log.e(TAG, "ERROR: " + throwable.toString());
    }

    @Override
    public void doDispose() {
        compositeDisposable.dispose();
    }

    @Override
    public String getRelevador() {
        try {
            return sharedManager.getAccount().getClientePrefijo();
        } catch (SharedException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void getTravels() {
        /*dataManager.getTravels()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable(v -> v)
                .distinct()
                .toList()
                .subscribe((travels)-> {
                    settingsView.showTravels(travels);
                });*/
    }

    @Override
    public int getCurrentTravel() {
        return sharedManager.getCurrentTravel();
    }

    @Override
    public void saveCurrentTravel(int currentTravelPosition) {
        sharedManager.saveCurrentTravel(currentTravelPosition);
    }

    @Override
    public void closeSession() {
        articlesDataManager.clearAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Boolean)->{
                    sharedManager.clearAll();
                    settingsView.onCloseSession();
                }, (Error)->error());
    }


}
