package com.epitech.billy.omega_splicer.presentation.presenters.impl;

import com.epitech.billy.omega_splicer.bluetooth.ConnectWithOmegaSplicerPlane;
import com.epitech.billy.omega_splicer.domain.executors.IMainThread;
import com.epitech.billy.omega_splicer.domain.executors.IThreadExecutor;
import com.epitech.billy.omega_splicer.domain.interactors.IConnectToPlaneInteractor;
import com.epitech.billy.omega_splicer.domain.interactors.impl.ConnectToPlaneInteractor;
import com.epitech.billy.omega_splicer.presentation.presenters.AbstractPresenter;
import com.epitech.billy.omega_splicer.presentation.presenters.IPlanesListPresenter;

/**
 * Created by Billy on 09/07/2016.
 */
public class PlanesListPresenter extends AbstractPresenter implements IPlanesListPresenter, IConnectToPlaneInteractor.Callback {

    // view
    private View mPlanesListView;

    // interactor
    private IConnectToPlaneInteractor mInteractor;

    public PlanesListPresenter(IThreadExecutor threadExecutor, IMainThread mainThread, View view, ConnectWithOmegaSplicerPlane connectWithOmegaSplicerPlane) {
        super(threadExecutor, mainThread);
        mPlanesListView = view;
        mInteractor = new ConnectToPlaneInteractor(threadExecutor, mainThread, this, connectWithOmegaSplicerPlane);
    }

    @Override
    public void resume() {
        // todo maybe restart the previous pending connection
    }

    @Override
    public void pause() {
        // todo stop the pending connection if any
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
    public void clickOnPlane(String macAddress) {
        mPlanesListView.displayConnectingStatus();
        mInteractor.setAddressToConnect(macAddress);
        mInteractor.execute();
    }

    @Override
    public void onConnectionFinish() {
        mPlanesListView.displayConnectedStatus();
    }

    @Override
    public void onFailure() {
        mPlanesListView.resetStatus();
        mPlanesListView.showCantConnectError();
    }

    @Override
    public void onMissingAddress() {
        mPlanesListView.resetStatus();
        mPlanesListView.showMissingAddressError();
    }

    @Override
    public void onInvalidAddress() {
        mPlanesListView.resetStatus();
        mPlanesListView.showInvalidAddressError();
    }

    @Override
    public void onUnknownError() {
        mPlanesListView.resetStatus();
        mPlanesListView.showUnknownError();
    }

}
