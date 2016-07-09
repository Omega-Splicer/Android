package com.epitech.billy.omega_splicer.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import com.epitech.billy.omega_splicer.App;
import com.epitech.billy.omega_splicer.bluetooth.mapper.BluetoothPlaneDataMapper;
import com.epitech.billy.omega_splicer.bluetooth.models.BluetoothPlane;
import com.epitech.billy.omega_splicer.domain.data.IGetPairedOmegaSplicerPlanes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Billy on 07/04/2016.
 */
public class GetPairedOmegaSplicerPlanes implements IGetPairedOmegaSplicerPlanes {

    private BluetoothAdapter mBluetoothAdapter;

    public GetPairedOmegaSplicerPlanes() {
        mBluetoothAdapter = App.BLUETOOTH_ADAPTER;
    }

    public void getPaired(Callback callback) {
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        List<BluetoothPlane> bluetoothPlaneList = new ArrayList<>();
        for (BluetoothDevice bluetoothDevice : pairedDevices) {
            bluetoothPlaneList.add(new BluetoothPlane(bluetoothDevice));
        }
        callback.onResult(BluetoothPlaneDataMapper.transform(bluetoothPlaneList));
    }
}
