package com.epitech.billy.omega_splicer.domain.data;

import com.epitech.billy.omega_splicer.domain.models.Plane;

/**
 * Created by bichon_b on 3/19/16.
 */
public interface IGetAllOmegaSplicerPlanes {
    interface Error {
        String getDescription();
        int getCode();
    }

    interface Callback {
        void onNewPlaneRetrieved(Plane plane);
        void onError(Error error);
        void onFinish();
    }

    void getAllPlanes(Callback callback);
}
