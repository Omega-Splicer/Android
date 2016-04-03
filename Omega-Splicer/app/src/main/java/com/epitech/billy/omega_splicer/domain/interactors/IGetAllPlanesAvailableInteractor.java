package com.epitech.billy.omega_splicer.domain.interactors;

import com.epitech.billy.omega_splicer.domain.data.IGetAllOmegaSplicerPlanes;
import com.epitech.billy.omega_splicer.domain.models.Plane;

/**
 * Created by bichon_b on 3/19/16.
 */
public interface IGetAllPlanesAvailableInteractor extends IBaseInteractor {
    interface Callback {
        void onNewPlaneDetected(Plane plane);
        void onError(IGetAllOmegaSplicerPlanes.Error error);
        void onFinish();
    }
}
