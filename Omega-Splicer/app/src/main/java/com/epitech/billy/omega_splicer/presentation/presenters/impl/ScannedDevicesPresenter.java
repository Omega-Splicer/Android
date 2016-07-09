package com.epitech.billy.omega_splicer.presentation.presenters.impl;

import com.epitech.billy.omega_splicer.domain.data.IScanOmegaSplicerPlanes;
import com.epitech.billy.omega_splicer.domain.executors.IMainThread;
import com.epitech.billy.omega_splicer.domain.executors.IThreadExecutor;
import com.epitech.billy.omega_splicer.domain.interactors.IGetAllPlanesAvailableInteractor;
import com.epitech.billy.omega_splicer.domain.interactors.impl.GetAllPlanesAvailableInteractor;
import com.epitech.billy.omega_splicer.domain.models.Plane;
import com.epitech.billy.omega_splicer.presentation.mapper.PlaneModelDataMapper;
import com.epitech.billy.omega_splicer.presentation.presenters.AbstractPresenter;
import com.epitech.billy.omega_splicer.presentation.presenters.IScannedDevicesPresenter;

/**
 * Created by Billy on 08/04/2016.
 */
public class ScannedDevicesPresenter extends AbstractPresenter implements IScannedDevicesPresenter, IGetAllPlanesAvailableInteractor.Callback {

    // view
    private View mScannedDevicesView;

    // 'repository'
    private IScanOmegaSplicerPlanes mScanOmegaSplicerPlanes;

    public ScannedDevicesPresenter(IThreadExecutor threadExecutor, IMainThread mainThread, View view, IScanOmegaSplicerPlanes scanOmegaSplicerPlanes) {
        super(threadExecutor, mainThread);
        mScannedDevicesView = view;
        mScanOmegaSplicerPlanes = scanOmegaSplicerPlanes;
    }

    @Override
    public void scan() {
        mScannedDevicesView.showLoading();
        new GetAllPlanesAvailableInteractor(mThreadExecutor, mMainThread, this, mScanOmegaSplicerPlanes).execute();
    }


    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onNewPlaneDetected(Plane plane) {
        mScannedDevicesView.addPlaneToList(PlaneModelDataMapper.transform(plane));
    }

    @Override
    public void onError(IScanOmegaSplicerPlanes.Error error) {
        mScannedDevicesView.displayErrorBluetooth(error.getDescription());
    }

    @Override
    public void onFinish() {
        mScannedDevicesView.hideLoading();
    }
}
