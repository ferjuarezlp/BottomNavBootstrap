package com.ferjuarez.bottomnavigationbootstrap.ui.settings;

import android.support.v4.app.Fragment;
import com.ferjuarez.bottomnavigationbootstrap.ui.base.ContractPresenter;
import com.ferjuarez.bottomnavigationbootstrap.ui.base.ContractView;
import java.util.List;

public interface SettingsContract {

    interface View extends ContractView {
        void onCloseSession();
        Fragment getFragment();
    }

    interface Presenter extends ContractPresenter {
        String getRelevador();
        void getTravels();
        int getCurrentTravel();
        void saveCurrentTravel(int currentTravelPosition);
        void closeSession();
    }
}
