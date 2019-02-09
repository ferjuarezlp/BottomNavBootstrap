package com.ferjuarez.bottomnavigationbootstrap.data.articles.shared;

import android.content.SharedPreferences;
import android.util.Log;
import com.ferjuarez.bottomnavigationbootstrap.data.shared.SharedManager;
import com.ferjuarez.bottomnavigationbootstrap.models.articles.Article;
import java.util.List;
import javax.inject.Inject;

public class ArticlesSharedManager extends SharedManager implements ArticlesSharedContract {

    private static final String TAG = ArticlesSharedManager.class.getSimpleName();
    private static final String PREF_LAST_UPDATE_PORT_USER = "last_update_port_ser";
    private static final String PREF_LAST_UPDATE_PREDICTIONS = "last_update_predictions";
    private static final long MAX_TIME_BEFORE_UPDATE_PORT_USER = 300000; // 5 min
    private static final long MAX_TIME_BEFORE_UPDATE_PREDICTIONS = 480000; // 8 min

    @Inject
    public ArticlesSharedManager(SharedPreferences sharedPreferences) {
        super(sharedPreferences);
    }

    @Override
    public boolean isNecessaryUpdateArticles() {
        return getTimeOfLastUpdate(PREF_LAST_UPDATE_PREDICTIONS) > MAX_TIME_BEFORE_UPDATE_PREDICTIONS;
    }

    private void saveTimeOfLastUpdate(String key) {
        if (key.isEmpty())
            return;
        shared.edit().putLong(key, System.currentTimeMillis()).apply();
    }

    private long getTimeOfLastUpdate(String key) {
        long time = shared.getLong(key, 0);
        return System.currentTimeMillis() - time;
    }

    private void log(String value){
        Log.i(TAG, value);
    }
}