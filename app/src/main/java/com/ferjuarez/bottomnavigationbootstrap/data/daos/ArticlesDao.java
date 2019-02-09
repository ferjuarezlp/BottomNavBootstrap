package com.ferjuarez.bottomnavigationbootstrap.data.daos;

import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RawQuery;
import android.arch.persistence.room.Update;
import com.ferjuarez.bottomnavigationbootstrap.models.articles.Article;
import java.util.List;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface ArticlesDao {

   @Insert
   long insert(Article article);

   @Update
   void update(Article article);

   @Query("DELETE FROM article")
   void deleteAll();

   @Query("DELETE FROM article WHERE id = :articleId")
   int delete(int articleId);

   @Query("DELETE FROM article")
   int deleteAllSync();

   @Query("SELECT * FROM article WHERE id = :id LIMIT 1")
   Maybe<Article> getArticle(long id);

   @Query("SELECT * FROM article")
   Maybe<List<Article>> getArticles();

   @Query("SELECT * FROM article WHERE code LIKE '%' || :code || '%' ")
   Maybe<List<Article>> getArticlesByRelevationCode(String code);

   @Query("SELECT incrementalCode from article ORDER BY incrementalCode DESC")
   Single<Long> getLastIncrementalCode();
}
