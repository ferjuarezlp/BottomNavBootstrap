package com.ferjuarez.bottomnavigationbootstrap.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.ferjuarez.bottomnavigationbootstrap.data.daos.ArticlesDao;
import com.ferjuarez.bottomnavigationbootstrap.models.articles.Article;

@Database(entities = {Article.class}, version = 1, exportSchema=false)
public abstract class BottomNavigationDatabase extends RoomDatabase {
   private static BottomNavigationDatabase sInstance;
   private static final String DATABASE_NAME = "BottomNavigationDatabase";

   public abstract ArticlesDao getArticlesDao();

   public static synchronized BottomNavigationDatabase getInstance(Context context) {
      if (sInstance == null) {
         if(context != null && context.getApplicationContext() != null){
            sInstance = Room.databaseBuilder(context.getApplicationContext(), BottomNavigationDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    //.allowMainThreadQueries()
                    .setJournalMode(JournalMode.TRUNCATE)
                    .build();
         }
      }
      return sInstance;
   }
}
