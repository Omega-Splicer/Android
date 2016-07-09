package com.epitech.billy.omega_splicer.domain.interactors;

import com.epitech.billy.omega_splicer.domain.models.Plane;

import java.util.List;

/**
 * Created by Billy on 07/04/2016.
 */
public interface IGetPairedPlanesInteractor extends IBaseInteractor {
    interface Callback {
        void onResult(List<Plane> planes);
    }
}
