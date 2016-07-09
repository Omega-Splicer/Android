package com.epitech.billy.omega_splicer.domain.interactors.impl;

import com.epitech.billy.omega_splicer.domain.data.IScanOmegaSplicerPlanes;
import com.epitech.billy.omega_splicer.domain.executors.IMainThread;
import com.epitech.billy.omega_splicer.domain.executors.IThreadExecutor;
import com.epitech.billy.omega_splicer.domain.interactors.AbstractInteractor;
import com.epitech.billy.omega_splicer.domain.interactors.IGetAllPlanesAvailableInteractor;
import com.epitech.billy.omega_splicer.domain.models.Plane;

/**
 * Created by bichon_b on 3/19/16.
 */
public class GetAllPlanesAvailableInteractor extends AbstractInteractor implements IGetAllPlanesAvailableInteractor, IScanOmegaSplicerPlanes.Callback {

    Callback mCallback;
    private IScanOmegaSplicerPlanes mScanOmegaSplicerPlanes;

    public GetAllPlanesAvailableInteractor(IThreadExecutor threadExecutor, IMainThread mainThread, Callback callback, IScanOmegaSplicerPlanes scanOmegaSplicerPlanes) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mScanOmegaSplicerPlanes = scanOmegaSplicerPlanes;
    }

    @Override
    public void run() {
        mScanOmegaSplicerPlanes.startScan(this);
    }

    @Override
    public void onNewPlaneRetrieved(final Plane plane) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onNewPlaneDetected(plane);
            }
        });
    }

    @Override
    public void onError(final IScanOmegaSplicerPlanes.Error error) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onError(error);
            }
        });
    }

    @Override
    public void onFinish() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onFinish();
            }
        });
    }
}
