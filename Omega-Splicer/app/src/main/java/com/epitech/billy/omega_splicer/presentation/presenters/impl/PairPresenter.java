package com.epitech.billy.omega_splicer.presentation.presenters.impl;

import com.epitech.billy.omega_splicer.bluetooth.GetAllOmegaSplicerPlanes;
import com.epitech.billy.omega_splicer.domain.data.IGetAllOmegaSplicerPlanes;
import com.epitech.billy.omega_splicer.domain.executors.IMainThread;
import com.epitech.billy.omega_splicer.domain.executors.IThreadExecutor;
import com.epitech.billy.omega_splicer.domain.interactors.IGetAllPlanesAvailableInteractor;
import com.epitech.billy.omega_splicer.domain.interactors.impl.GetAllPlanesAvailableInteractor;
import com.epitech.billy.omega_splicer.presentation.models.Plane;
import com.epitech.billy.omega_splicer.presentation.presenters.AbstractPresenter;
import com.epitech.billy.omega_splicer.presentation.presenters.IPairPresenter;

/**
 * Created by bichon_b on 3/4/16.
 */
public class PairPresenter extends AbstractPresenter implements IPairPresenter, IGetAllPlanesAvailableInteractor.Callback {

    private  View mPairView;
    private IGetAllOmegaSplicerPlanes mGetAllBluetoothPlanes;

    public PairPresenter(IThreadExecutor threadExecutor, IMainThread mainThread, View pairView, IGetAllOmegaSplicerPlanes getAllBluetoothPlanes) {
        super(threadExecutor, mainThread);
        mPairView = pairView;
        mGetAllBluetoothPlanes = getAllBluetoothPlanes;
    }

    @Override
    public void resume() {
        super.resume();
        mPairView.showLoading();
        IGetAllPlanesAvailableInteractor interactor = new GetAllPlanesAvailableInteractor(mThreadExecutor, mMainThread, this, mGetAllBluetoothPlanes);
        interactor.execute();
    }

    @Override
    public void onNewPlaneDetected(com.epitech.billy.omega_splicer.domain.models.Plane plane) {
//        mPairView.hideLoading(); // TODO: display another loader
        Plane _plane = new Plane(plane.getName());
        _plane.setSignalStrength(plane.getSignalStrength());
        mPairView.addPlaneToList(_plane); // TODO: use the converters
    }

    @Override
    public void onError(IGetAllOmegaSplicerPlanes.Error error) {
        if (error.getCode() == GetAllOmegaSplicerPlanes.BLUETOOTH_DISABLED) {
            mPairView.displayErrorBluetoothDisabled();
        }
        else {
            mPairView.displayErrorBluetooth(error.getDescription());
        }
    }

    @Override
    public void onFinish() {
        mPairView.hideLoading();
        // TODO the adapter is empty display a message, else hide the loader
    }
}
