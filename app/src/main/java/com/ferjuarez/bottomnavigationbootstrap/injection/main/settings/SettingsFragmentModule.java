package com.ferjuarez.bottomnavigationbootstrap.injection.main.settings;

import android.support.v4.app.Fragment;

import com.ferjuarez.bottomnavigationbootstrap.data.DataContract;
import com.ferjuarez.bottomnavigationbootstrap.data.articles.ArticlesDataContract;
import com.ferjuarez.bottomnavigationbootstrap.data.shared.SharedContract;
import com.ferjuarez.bottomnavigationbootstrap.injection.util.ScopeFragment;
import com.ferjuarez.bottomnavigationbootstrap.ui.settings.SettingsContract;
import com.ferjuarez.bottomnavigationbootstrap.ui.settings.SettingsFragment;
import com.ferjuarez.bottomnavigationbootstrap.ui.settings.SettingsPresenter;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class SettingsFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(SettingsFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindSettingsFragmentInjectorFactory(
            SettingsFragmentComponent.Builder builder);

    @Provides
    @ScopeFragment
    static SettingsContract.Presenter provideSettingsPresenter(SettingsContract.View settingsView,
                                                               ArticlesDataContract articlesDataManager, DataContract dataManager, SharedContract
                                                                   shared){
        return new SettingsPresenter(settingsView, articlesDataManager, dataManager, shared);
    }
}
