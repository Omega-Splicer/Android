package com.epitech.billy.omega_splicer.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.widget.Toast;

import com.epitech.billy.omega_splicer.App;
import com.epitech.billy.omega_splicer.domain.data.IConnectWithOmegaSplicerPlane;

/**
 * Created by Billy on 24/05/2016.
 */
public class ConnectWithOmegaSplicerPlane implements IConnectWithOmegaSplicerPlane {

    private Context mContext;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothGatt mBluetoothGatt;

    public ConnectWithOmegaSplicerPlane(Context context) {
        mContext = context;
        mBluetoothAdapter = App.BLUETOOTH_ADAPTER;
    }

    @Override
    public void connect(String macAddress, final Callback callback) {
        if (macAddress.isEmpty()) {
            callback.onInvalidAddress();
            return;
        }

        // stop all pending connection
        disconnect();

        try {
            BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(macAddress);

            if (device == null) {
                callback.onUnexpectedError();
                return;
            }

            mBluetoothGatt = device.connectGatt(mContext, false, new BluetoothGattCallback() {
                @Override
                public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                    super.onConnectionStateChange(gatt, status, newState);

                    switch (newState) {
                        case BluetoothProfile.STATE_CONNECTED:
                            callback.onSuccessfulConnection();
                            System.out.println("CONNECTED");
                            break;
                        case BluetoothProfile.STATE_CONNECTING:
                            System.out.println("CONNECTING");
                            break;
                        case BluetoothProfile.STATE_DISCONNECTED:
                            callback.onUnsuccessfulConnection();
                            System.out.println("DISCONNECTED");
                            break;
                        case BluetoothProfile.STATE_DISCONNECTING:
                            System.out.println("DISCONNECTING");
                            break;
                        default:
                            System.out.println("UNKNOWN");
                            break;
                    }

                    System.out.println("the connection state change " + newState + " with status " + status);
                }

                @Override
                public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                    super.onServicesDiscovered(gatt, status);
                }
            });
        }
        catch (IllegalArgumentException e) {
            callback.onInvalidAddress();
        }
    }

    @Override
    public void disconnect() {
        if( mBluetoothGatt != null) {
            // todo verify the disconnect + close
            mBluetoothGatt.disconnect();
            mBluetoothGatt.close();
            mBluetoothGatt = null;
        }
    }
}
