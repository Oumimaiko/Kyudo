package jp.kyudodatabaseuniformresourceidentifier.kyudo;

import android.app.Application;

import io.realm.Realm;

public class DaysApp extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Realm.init(this);
    }
}
