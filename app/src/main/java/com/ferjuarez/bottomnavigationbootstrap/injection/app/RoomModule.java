package com.ferjuarez.bottomnavigationbootstrap.injection.app;

import android.app.Application;
import com.ferjuarez.bottomnavigationbootstrap.data.BottomNavigationDatabase;
import com.ferjuarez.bottomnavigationbootstrap.data.daos.ArticlesDao;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private BottomNavigationDatabase roomDatabase;

    public RoomModule(Application mApplication) {
        roomDatabase = BottomNavigationDatabase.getInstance(mApplication);
    }

    @Singleton
    @Provides
    BottomNavigationDatabase providesRoomDatabase() {
        return roomDatabase;
    }

    @Singleton
    @Provides
    ArticlesDao providesArticlesDao() {
        return roomDatabase.getArticlesDao();
    }
}


