package com.epitech.billy.omega_splicer;

import android.app.Application;

/**
 * Created by Billy on 01/09/2015.
 *
 */
public class App extends Application {
    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized App getInstance() {
        return mInstance;
    }
}
