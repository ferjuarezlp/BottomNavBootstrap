package com.ferjuarez.bottomnavigationbootstrap.ui.home;

import android.annotation.SuppressLint;
import android.util.Log;
import com.ferjuarez.bottomnavigationbootstrap.data.articles.ArticlesDataContract;
import com.ferjuarez.bottomnavigationbootstrap.data.shared.SharedContract;
import com.ferjuarez.bottomnavigationbootstrap.models.articles.Article;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")
public class HomePresenter implements HomeContract.Presenter {

    private static final String TAG = HomePresenter.class.getSimpleName();

    private final HomeContract.View homeView;
    private final SharedContract sharedManager;
    private final ArticlesDataContract articlesDataManager;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public HomePresenter(HomeContract.View homeView,
                         ArticlesDataContract articlesDataManager, SharedContract sharedManager) {
        this.homeView = homeView;
        this.articlesDataManager = articlesDataManager;
        this.sharedManager = sharedManager;
    }
    /*@Override
    public void getArticles() {
        homeView.showLoadingInfo();
        try {
            String projectId = sharedManager.getAccount().getClienteId();
            if(projectId != null){
                articlesDataManager.getArticles()
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(compositeDisposable::add)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::showInfo, this::error);
            } else error();

        } catch (SharedException e) {
            e.printStackTrace();
            error();
        }

    }*/

    public void error(){
        Log.e("","");
        homeView.hideLoadingInfo();
    }


    private void error(Throwable throwable) {
        homeView.hideLoadingInfo();
        homeView.showError();
        Log.e(TAG, "ERROR: " + throwable.toString());
    }

    @Override
    public void doDispose() {
        compositeDisposable.dispose();
    }

    private void showInfo(List<Article> articles) {
        homeView.hideLoadingInfo();
        homeView.showArticles(articles);
    }
}
