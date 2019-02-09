package com.ferjuarez.bottomnavigationbootstrap.injection.app;

import android.app.Application;
import android.arch.persistence.room.RoomDatabase;
import com.ferjuarez.bottomnavigationbootstrap.data.BottomNavigationDB;
import com.ferjuarez.bottomnavigationbootstrap.data.daos.ArticlesDao;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private BottomNavigationDB roomDatabase;

    public RoomModule(Application mApplication) {
        roomDatabase = BottomNavigationDB.getInstance(mApplication);
    }

    @Singleton
    @Provides
    BottomNavigationDB providesRoomDatabase() {
        return roomDatabase;
    }

    @Singleton
    @Provides
    ArticlesDao providesArticlesDao() {
        return roomDatabase.getArticlesDao();
    }
}


