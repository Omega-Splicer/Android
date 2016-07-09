package com.epitech.billy.omega_splicer.presentation.presenters.impl;

import com.epitech.billy.omega_splicer.domain.data.IGetPairedOmegaSplicerPlanes;
import com.epitech.billy.omega_splicer.domain.executors.IMainThread;
import com.epitech.billy.omega_splicer.domain.executors.IThreadExecutor;
import com.epitech.billy.omega_splicer.domain.interactors.IGetPairedPlanesInteractor;
import com.epitech.billy.omega_splicer.domain.interactors.impl.GetPairedPlanesInteractor;
import com.epitech.billy.omega_splicer.domain.models.Plane;
import com.epitech.billy.omega_splicer.presentation.mapper.PlaneModelDataMapper;
import com.epitech.billy.omega_splicer.presentation.presenters.AbstractPresenter;
import com.epitech.billy.omega_splicer.presentation.presenters.IPairedDevicesPresenter;

import java.util.List;

/**
 * Created by Billy on 07/04/2016.
 */
public class PairedDevicesPresenter extends AbstractPresenter implements IPairedDevicesPresenter, IGetPairedPlanesInteractor.Callback {

    // todo implements the callback of the connection with bluetooth

    // view
    private View mPairedDevicesView;

    // 'repository'
    private IGetPairedOmegaSplicerPlanes mGetPairedOmegaSplicerPlanes;

    public PairedDevicesPresenter(IThreadExecutor threadExecutor, IMainThread mainThread, View view, IGetPairedOmegaSplicerPlanes getPairedOmegaSplicerPlanes) {
        super(threadExecutor, mainThread);
        mPairedDevicesView = view;
        mGetPairedOmegaSplicerPlanes = getPairedOmegaSplicerPlanes;
    }

    @Override
    public void fetch() {
        mPairedDevicesView.showLoading();
        new GetPairedPlanesInteractor(mThreadExecutor, mMainThread, this, mGetPairedOmegaSplicerPlanes).execute();
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
    public void onResult(List<Plane> planes) {
        mPairedDevicesView.addPlanesToList(PlaneModelDataMapper.transform(planes));
        mPairedDevicesView.hideLoading();
    }
}
