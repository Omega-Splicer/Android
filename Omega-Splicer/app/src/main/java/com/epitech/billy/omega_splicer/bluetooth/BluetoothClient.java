package com.epitech.billy.omega_splicer.bluetooth;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by bichon_b on 3/11/16.
 * <p/>
 * This class is used to handled the bluetooth communication with the planes.
 * <p/>
 */
public class BluetoothClient {

    public BluetoothClient(Context context) {
        if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {

        }
    }

}
