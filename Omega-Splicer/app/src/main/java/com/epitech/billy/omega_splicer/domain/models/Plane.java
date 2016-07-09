package com.epitech.billy.omega_splicer.domain.models;

/**
 * Created by bichon_b on 3/11/16.
 * <p/>
 * This class represents a plane in the domain layer.
 * <p/>
 */
public class Plane {
    private String name;
    private int signalStrength;
    private String macAddress;

    public Plane() {}

    public Plane(String name) {
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
}
