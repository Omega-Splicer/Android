package com.epitech.billy.omega_splicer.bluetooth.models;

import android.bluetooth.BluetoothDevice;

/**
 * Created by bichon_b on 3/11/16.
 * <p/>
 * This class represents a plane retrieve by bluetooth by the network layer.
 * <p/>
 */
public class BluetoothPlane {

    private String name = "";
    private int signalStrength = 0;
    private String macAddress = "";

    public BluetoothPlane(BluetoothDevice device) {
        name = device.getName();
        macAddress = device.getAddress();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSignalStrength() {
        return signalStrength;
    }

    public void setSignalStrength(int signalStrength) {
        this.signalStrength = signalStrength;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
