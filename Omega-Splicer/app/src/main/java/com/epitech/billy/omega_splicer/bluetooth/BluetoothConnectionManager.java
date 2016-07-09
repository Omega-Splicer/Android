package com.epitech.billy.omega_splicer.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;

import com.epitech.billy.omega_splicer.App;
import com.epitech.billy.omega_splicer.domain.data.IBluetoothConnectionManager;

/**
 * Created by bichon_b on 3/11/16.
 * <p/>
 * This class is used to handled the bluetooth connection and availability on the phone.
 * <p/>
 */
public class BluetoothConnectionManager implements IBluetoothConnectionManager {

    private Context mContext;
    private BroadcastReceiver mBluetoothReceiver;

    public BluetoothConnectionManager(Context context) {
        mContext = context;
    }

    public void start(final Callback callback) {
        if (!mContext.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            callback.onUnsupported();
            return;
        }

        if (App.BLUETOOTH_ADAPTER == null || !App.BLUETOOTH_ADAPTER.isEnabled()) {
            callback.onDeactivated();
        }

        // If the user disable the bluetooth while the discovery process is running
        mBluetoothReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                final String action = intent.getAction();

                if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                    final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

                    System.out.println("THE STATE CHANGED");

                    switch (state) {
                        case BluetoothAdapter.STATE_OFF:
                            System.out.println("OFF");
//                            stop();
                            callback.onDeactivated();
                            break;
                        case BluetoothAdapter.STATE_ON:
                            System.out.println("ON");
                            callback.onActivated();
                        default:
                            System.out.println(state);
                            break;
                    }
                }
            }
        };

        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        mContext.registerReceiver(mBluetoothReceiver, filter);
    }

    @Override
    public void stop() {
        try {
            mContext.unregisterReceiver(mBluetoothReceiver);
        }
        catch (IllegalArgumentException e) {}
    }
}
