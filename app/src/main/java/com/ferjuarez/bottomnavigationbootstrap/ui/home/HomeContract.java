package com.ferjuarez.bottomnavigationbootstrap.ui.home;

import android.support.v4.app.Fragment;
import com.ferjuarez.bottomnavigationbootstrap.models.articles.Article;
import com.ferjuarez.bottomnavigationbootstrap.ui.base.ContractPresenter;
import com.ferjuarez.bottomnavigationbootstrap.ui.base.ContractView;
import java.util.List;

public interface HomeContract {

    interface View extends ContractView {
        void showLoadingInfo();

        void hideLoadingInfo();

        void showError();

        void showArticles(List<Article> articles);

        void updateArticleList(int position);

        Fragment getFragment();
    }

    interface Presenter extends ContractPresenter {
    }
}
