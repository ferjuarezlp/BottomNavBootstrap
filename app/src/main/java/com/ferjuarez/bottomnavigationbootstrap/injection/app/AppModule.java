package com.ferjuarez.bottomnavigationbootstrap.injection.app;

import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.ferjuarez.bottomnavigationbootstrap.BottomNavigationApp;
import com.ferjuarez.bottomnavigationbootstrap.data.APIServices;
import com.ferjuarez.bottomnavigationbootstrap.data.BottomNavigationDB;
import com.ferjuarez.bottomnavigationbootstrap.data.DataContract;
import com.ferjuarez.bottomnavigationbootstrap.data.DataManager;
import com.ferjuarez.bottomnavigationbootstrap.data.articles.ArticleDataManager;
import com.ferjuarez.bottomnavigationbootstrap.data.articles.ArticlesDataContract;
import com.ferjuarez.bottomnavigationbootstrap.data.articles.shared.ArticlesSharedContract;
import com.ferjuarez.bottomnavigationbootstrap.data.articles.shared.ArticlesSharedManager;
import com.ferjuarez.bottomnavigationbootstrap.data.shared.SharedContract;
import com.ferjuarez.bottomnavigationbootstrap.data.shared.SharedManager;
import com.ferjuarez.bottomnavigationbootstrap.injection.login.LoginActivityComponent;
import com.ferjuarez.bottomnavigationbootstrap.injection.main.MainActivityComponent;
import com.ferjuarez.bottomnavigationbootstrap.injection.splash.SplashActivityComponent;
import com.ferjuarez.bottomnavigationbootstrap.utils.UtilDate;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module(subcomponents = {LoginActivityComponent.class, MainActivityComponent.class, SplashActivityComponent.class})
public class AppModule {

    @Singleton
    @Provides
    Context provideContext(BottomNavigationApp app) {
        return app.getApplicationContext();
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    static DataContract provideDataManager(APIServices apiServices,
                                           SharedContract sharedManager, BottomNavigationDB roomDatabase) {
        return new DataManager(apiServices, sharedManager,roomDatabase);
    }

    @Provides
    @Singleton
    static ArticlesDataContract provideArticleDataManager(APIServices apiServices,
                                                          SharedContract sharedManager, ArticlesSharedContract articlesShared, BottomNavigationDB roomDatabase) {
        return new ArticleDataManager(apiServices, sharedManager, articlesShared, roomDatabase);
    }

    @Provides
    @Singleton
    static ArticlesSharedContract provideArticlesSharedManager(SharedPreferences sharedPreferences) {
        return new ArticlesSharedManager(sharedPreferences);
    }

    @Provides
    @Singleton
    static UtilDate provideUtilDate() {
        return new UtilDate();
    }

    @Provides
    @Singleton
    static SharedContract provideSharedManager(SharedPreferences sharedPreferences) {
        return new SharedManager(sharedPreferences);
    }

    @Provides
    @Singleton
    static BottomNavigationDB provideModaxRoomDatabase(Context context) {
        return BottomNavigationDB.getInstance(context);
    }

}