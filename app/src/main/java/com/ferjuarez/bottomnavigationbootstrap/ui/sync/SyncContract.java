package com.ferjuarez.bottomnavigationbootstrap.ui.sync;

import android.support.v4.app.Fragment;
import com.ferjuarez.bottomnavigationbootstrap.ui.base.ContractPresenter;
import com.ferjuarez.bottomnavigationbootstrap.ui.base.ContractView;
import java.util.List;

public interface SyncContract {

    interface View extends ContractView {
        Fragment getFragment();
    }

    interface Presenter extends ContractPresenter {
        void cancelAll();
    }
}
