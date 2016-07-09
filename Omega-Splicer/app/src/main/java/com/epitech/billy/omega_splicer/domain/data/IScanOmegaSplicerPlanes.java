package com.epitech.billy.omega_splicer.domain.data;

import com.epitech.billy.omega_splicer.domain.models.Plane;

/**
 * Created by bichon_b on 3/19/16.
 */
public interface IScanOmegaSplicerPlanes {
    interface Error {
        String getDescription();
        int getCode();
    }

    interface Callback {
        void onNewPlaneRetrieved(Plane plane);
        void onError(Error error);
        void onFinish();
    }

    void startScan(Callback callback);
}
