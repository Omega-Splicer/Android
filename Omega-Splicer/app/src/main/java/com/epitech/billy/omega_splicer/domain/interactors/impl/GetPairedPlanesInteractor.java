package com.epitech.billy.omega_splicer.domain.interactors.impl;

import com.epitech.billy.omega_splicer.domain.data.IGetPairedOmegaSplicerPlanes;
import com.epitech.billy.omega_splicer.domain.executors.IMainThread;
import com.epitech.billy.omega_splicer.domain.executors.IThreadExecutor;
import com.epitech.billy.omega_splicer.domain.interactors.AbstractInteractor;
import com.epitech.billy.omega_splicer.domain.interactors.IGetPairedPlanesInteractor;
import com.epitech.billy.omega_splicer.domain.models.Plane;

import java.util.List;

/**
 * Created by Billy on 07/04/2016.
 */
public class GetPairedPlanesInteractor extends AbstractInteractor implements IGetPairedPlanesInteractor, IGetPairedOmegaSplicerPlanes.Callback {

    private Callback mCallback;
    private IGetPairedOmegaSplicerPlanes mGetPairedOmegaSplicerPlanes;

    public GetPairedPlanesInteractor(IThreadExecutor threadExecutor, IMainThread mainThread, Callback callback, IGetPairedOmegaSplicerPlanes getPairedOmegaSplicerPlanes) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mGetPairedOmegaSplicerPlanes = getPairedOmegaSplicerPlanes;
    }

    @Override
    public void run() {
        mGetPairedOmegaSplicerPlanes.getPaired(this);
    }

    @Override
    public void onResult(final List<Plane> planes) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onResult(planes);
            }
        });
    }
}
