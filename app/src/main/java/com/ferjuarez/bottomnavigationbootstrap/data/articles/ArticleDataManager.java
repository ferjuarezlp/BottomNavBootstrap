package com.ferjuarez.bottomnavigationbootstrap.data.articles;

import android.util.Log;
import com.ferjuarez.bottomnavigationbootstrap.data.APIServices;
import com.ferjuarez.bottomnavigationbootstrap.data.BottomNavigationDB;
import com.ferjuarez.bottomnavigationbootstrap.data.DataManager;
import com.ferjuarez.bottomnavigationbootstrap.data.articles.shared.ArticlesSharedContract;
import com.ferjuarez.bottomnavigationbootstrap.data.shared.SharedContract;
import com.ferjuarez.bottomnavigationbootstrap.models.articles.Article;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class ArticleDataManager extends DataManager implements ArticlesDataContract {
    private final ArticlesSharedContract articlesSharedManager;

    public ArticleDataManager(APIServices apiServices, SharedContract sharedManager,
                              ArticlesSharedContract articlesSharedManager, BottomNavigationDB roomDatabase) {
        super(apiServices, sharedManager, roomDatabase);
        this.articlesSharedManager = articlesSharedManager;
    }

    private <T> Function<Throwable, ? extends Observable<? extends T>> refreshTokenAndRetryObservable(
            final Observable<T> toBeResumed) {
        return throwable -> {
            log(throwable);
            return Observable.error(throwable);
        };
    }

    private boolean isHttp401Error(Throwable throwable) {
        return throwable instanceof HttpException && ((HttpException) throwable).code() == 401;
    }

    private void log(Object throwable) {
        Log.e(ArticleDataManager.class.getSimpleName(), "ERROR: " + throwable.toString());
    }

    @Override
    public Observable clearAll() {
        return Observable.fromCallable(() -> {
            getRoomDatabase().getArticlesDao().deleteAll();
            return true;
        });
    }

    @Override
    public Observable<List<Article>> getArticles() {
        return getRoomDatabase().getArticlesDao().getArticles().toObservable();
    }

}