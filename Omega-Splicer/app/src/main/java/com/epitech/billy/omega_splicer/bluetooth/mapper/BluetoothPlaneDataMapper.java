package com.epitech.billy.omega_splicer.bluetooth.mapper;

import com.epitech.billy.omega_splicer.bluetooth.models.BluetoothPlane;
import com.epitech.billy.omega_splicer.domain.models.Plane;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Billy on 06/04/2016.
 */
public class BluetoothPlaneDataMapper {

    private BluetoothPlaneDataMapper() {}

    public static Plane transform(BluetoothPlane bluetoothPlane) {
        Plane plane = null;
        if (bluetoothPlane != null) {
            plane = new Plane();
            plane.setName(bluetoothPlane.getName());
            plane.setSignalStrength(bluetoothPlane.getSignalStrength());
            plane.setMacAddress(bluetoothPlane.getMacAddress());
        }
        return plane;
    }

    public static List<Plane> transform (Collection<BluetoothPlane> bluetoothPlaneCollection) {
        List<Plane> list = new ArrayList<>();
        Plane plane = null;
        for (BluetoothPlane bluetoothPlane : bluetoothPlaneCollection) {
            plane = transform(bluetoothPlane);
            if (plane != null) {
                list.add(plane);
            }
        }
        return list;
    }

}
