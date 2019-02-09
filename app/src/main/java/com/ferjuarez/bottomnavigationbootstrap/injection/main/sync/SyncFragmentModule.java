package com.ferjuarez.bottomnavigationbootstrap.injection.main.sync;

import android.support.v4.app.Fragment;
import com.ferjuarez.bottomnavigationbootstrap.data.DataContract;
import com.ferjuarez.bottomnavigationbootstrap.data.articles.ArticlesDataContract;
import com.ferjuarez.bottomnavigationbootstrap.data.shared.SharedContract;
import com.ferjuarez.bottomnavigationbootstrap.injection.util.ScopeFragment;
import com.ferjuarez.bottomnavigationbootstrap.ui.sync.SyncContract;
import com.ferjuarez.bottomnavigationbootstrap.ui.sync.SyncFragment;
import com.ferjuarez.bottomnavigationbootstrap.ui.sync.SyncPresenter;
import com.ferjuarez.bottomnavigationbootstrap.utils.UtilDate;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class SyncFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(SyncFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindSyncFragmentInjectorFactory(
            SyncFragmentComponent.Builder builder);

    @Provides
    @ScopeFragment
    static SyncContract.Presenter provideSyncPresenter(SyncContract.View syncView,
                                                       ArticlesDataContract articlessDataManager, SharedContract
                                                                   shared, DataContract dataManager, UtilDate utilDate){
        return new SyncPresenter(syncView, articlessDataManager, shared, dataManager, utilDate);
    }
}
