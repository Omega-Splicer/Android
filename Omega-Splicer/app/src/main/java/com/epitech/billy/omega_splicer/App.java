package com.epitech.billy.omega_splicer;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.widget.Toast;

/**
 * Created by Billy on 01/09/2015.
 *
 */
public class App extends Application {
    private static App mInstance;

    public static BluetoothAdapter BLUETOOTH_ADAPTER;

    public static Typeface ROBOTO_BOLD;
    public static Typeface ROBOTO_LIGHT;
    public static Typeface ROBOTO_REGULAR;

    @Override
    public void onCreate() {
        super.onCreate();

        BLUETOOTH_ADAPTER = ((BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE)).getAdapter();

        ROBOTO_BOLD = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        ROBOTO_LIGHT = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        ROBOTO_REGULAR = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        mInstance = this;
    }



    public static synchronized App getInstance() {
        return mInstance;
    }
}
