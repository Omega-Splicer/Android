package com.epitech.billy.omega_splicer.domain.interactors.impl;

import com.epitech.billy.omega_splicer.domain.data.IGetAllOmegaSplicerPlanes;
import com.epitech.billy.omega_splicer.domain.executors.IMainThread;
import com.epitech.billy.omega_splicer.domain.executors.IThreadExecutor;
import com.epitech.billy.omega_splicer.domain.interactors.AbstractInteractor;
import com.epitech.billy.omega_splicer.domain.interactors.IGetAllPlanesAvailableInteractor;
import com.epitech.billy.omega_splicer.domain.models.Plane;

/**
 * Created by bichon_b on 3/19/16.
 */
public class GetAllPlanesAvailableInteractor extends AbstractInteractor implements IGetAllPlanesAvailableInteractor, IGetAllOmegaSplicerPlanes.Callback {

    Callback mCallback;
    private IGetAllOmegaSplicerPlanes mGetAllBluetoothPlanes;

    public GetAllPlanesAvailableInteractor(IThreadExecutor threadExecutor, IMainThread mainThread, Callback callback, IGetAllOmegaSplicerPlanes getAllBluetoothPlanes) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mGetAllBluetoothPlanes = getAllBluetoothPlanes;
    }

    @Override
    public void run() {
        mGetAllBluetoothPlanes.getAllPlanes(this);
    }

    @Override
    public void onNewPlaneRetrieved(Plane plane) {
        mCallback.onNewPlaneDetected(plane);
    }

    @Override
    public void onError(IGetAllOmegaSplicerPlanes.Error error) {
        mCallback.onError(error);
    }

    @Override
    public void onFinish() {
        mCallback.onFinish();
    }
}
