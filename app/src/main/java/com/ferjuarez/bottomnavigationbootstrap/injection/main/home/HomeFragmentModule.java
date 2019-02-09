package com.ferjuarez.bottomnavigationbootstrap.injection.main.home;

import android.support.v4.app.Fragment;

import com.ferjuarez.bottomnavigationbootstrap.data.articles.ArticlesDataContract;
import com.ferjuarez.bottomnavigationbootstrap.data.shared.SharedContract;
import com.ferjuarez.bottomnavigationbootstrap.injection.util.ScopeFragment;
import com.ferjuarez.bottomnavigationbootstrap.ui.home.HomeContract;
import com.ferjuarez.bottomnavigationbootstrap.ui.home.HomeFragment;
import com.ferjuarez.bottomnavigationbootstrap.ui.home.HomePresenter;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class HomeFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(HomeFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindHomeFragmentInjectorFactory(
            HomeFragmentComponent.Builder builder);

    @Provides
    @ScopeFragment
    static HomeContract.Presenter provideHomePresenter(HomeContract.View homeView,
                                                       ArticlesDataContract articlessDataManager, SharedContract
                                                                   shared){
        return new HomePresenter(homeView, articlessDataManager, shared);
    }
}
