package com.ferjuarez.bottomnavigationbootstrap.data.articles;

import com.ferjuarez.bottomnavigationbootstrap.models.articles.Article;

import java.util.List;
import io.reactivex.Observable;

public interface ArticlesDataContract {
    Observable clearAll();
    Observable<List<Article>> getArticles();
}