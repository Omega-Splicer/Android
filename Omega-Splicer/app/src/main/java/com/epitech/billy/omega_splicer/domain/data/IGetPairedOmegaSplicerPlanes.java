package com.epitech.billy.omega_splicer.domain.data;

import com.epitech.billy.omega_splicer.domain.interactors.IGetPairedPlanesInteractor;
import com.epitech.billy.omega_splicer.domain.models.Plane;

import java.util.List;

/**
 * Created by Billy on 07/04/2016.
 */
public interface IGetPairedOmegaSplicerPlanes {
    interface Callback {
        void onResult(List<Plane> planes);
    }
    void getPaired(Callback callback);
}
