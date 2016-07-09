package com.epitech.billy.omega_splicer.presentation.models;

/**
 * Created by bichon_b on 3/11/16.
 * <p/>
 * This class represents a plane in the presentation layer.
 * <p/>
 */
public class PlaneModel {

    private String name;
    private int signalStrength;
    private String macAddress;

    public PlaneModel() {}

    public PlaneModel(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
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

    @Override
    public boolean equals(Object o) {
        if (o instanceof PlaneModel) {
            return ((PlaneModel) o).getMacAddress().equals(this.getMacAddress());
        }
        else
            return super.equals(o);
    }
}
