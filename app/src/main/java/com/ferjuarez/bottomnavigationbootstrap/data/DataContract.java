package com.ferjuarez.bottomnavigationbootstrap.data;

import com.ferjuarez.bottomnavigationbootstrap.models.account.Account;
import io.reactivex.Observable;

public interface DataContract {

    Observable<Account> login(String email, String password);

    Observable<Account> loginMock(String email, String password);

    BottomNavigationDatabase getRoomDatabase();
}
