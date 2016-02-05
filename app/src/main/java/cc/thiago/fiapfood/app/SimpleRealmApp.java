package cc.thiago.fiapfood.app;

import android.app.Application;

import cc.thiago.fiapfood.realm.module.SimpleRealmModule;
import io.realm.RealmConfiguration;

/**
 * Created by thiagotn on 05/02/2016.
 */
public class SimpleRealmApp extends Application {

    private static SimpleRealmApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RealmConfiguration config = new RealmConfiguration
                .Builder(getApplicationContext())
                .setModules(new SimpleRealmModule())
                .build();
    }

    public static SimpleRealmApp getInstance() {
        return instance;
    }
}
