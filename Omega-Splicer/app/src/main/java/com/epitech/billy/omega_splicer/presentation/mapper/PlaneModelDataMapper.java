package com.epitech.billy.omega_splicer.presentation.mapper;

import com.epitech.billy.omega_splicer.domain.models.Plane;
import com.epitech.billy.omega_splicer.presentation.models.PlaneModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Billy on 06/04/2016.
 */
public class PlaneModelDataMapper {

    private PlaneModelDataMapper() {}

    public static PlaneModel transform(Plane plane) {
        PlaneModel planeModel = null;
        if (plane != null) {
            planeModel = new PlaneModel();
            planeModel.setName(plane.getName());
            planeModel.setSignalStrength(plane.getSignalStrength());
            planeModel.setMacAddress(plane.getMacAddress());
        }
        return planeModel;
    }

    public static List<PlaneModel> transform(Collection<Plane> planeCollection) {
        List<PlaneModel> list = new ArrayList<>();
        for (Plane plane : planeCollection) {
            PlaneModel planeModel = transform(plane);
            if (planeModel != null) {
                list.add(planeModel);
            }
        }
        return list;
    }
}
