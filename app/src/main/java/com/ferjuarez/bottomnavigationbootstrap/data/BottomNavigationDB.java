package com.ferjuarez.bottomnavigationbootstrap.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.ferjuarez.bottomnavigationbootstrap.data.daos.ArticlesDao;
import com.ferjuarez.bottomnavigationbootstrap.models.articles.Article;

@Database(entities = {Article.class}, version = 1, exportSchema=false)
public abstract class BottomNavigationDB extends RoomDatabase {
   private static BottomNavigationDB sInstance;
   private static final String DATABASE_NAME = "BottomNavigationDB";

   public abstract ArticlesDao getArticlesDao();

   public static synchronized BottomNavigationDB getInstance(Context context) {
      if (sInstance == null) {
         if(context != null && context.getApplicationContext() != null){
            sInstance = Room.databaseBuilder(context.getApplicationContext(), BottomNavigationDB.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    //.allowMainThreadQueries()
                    .setJournalMode(JournalMode.TRUNCATE)
                    .build();
         }
      }
      return sInstance;
   }
}
