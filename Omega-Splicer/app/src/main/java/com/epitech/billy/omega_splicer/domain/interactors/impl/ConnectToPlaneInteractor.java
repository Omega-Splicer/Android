package com.epitech.billy.omega_splicer.domain.interactors.impl;

import com.epitech.billy.omega_splicer.bluetooth.ConnectWithOmegaSplicerPlane;
import com.epitech.billy.omega_splicer.domain.executors.IMainThread;
import com.epitech.billy.omega_splicer.domain.executors.IThreadExecutor;
import com.epitech.billy.omega_splicer.domain.interactors.AbstractInteractor;
import com.epitech.billy.omega_splicer.domain.interactors.IConnectToPlaneInteractor;

/**
 * Created by Billy on 09/07/2016.
 */
public class ConnectToPlaneInteractor extends AbstractInteractor implements IConnectToPlaneInteractor, ConnectWithOmegaSplicerPlane.Callback {

    private String mAddress;
    private ConnectWithOmegaSplicerPlane mConnectionService;
    private Callback mCallback;

    public ConnectToPlaneInteractor(IThreadExecutor threadExecutor, IMainThread mainThread, Callback callback, ConnectWithOmegaSplicerPlane connectionService) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mConnectionService = connectionService;
    }

    @Override
    public void run() {
        if (mAddress.isEmpty()) {
            throw new IllegalArgumentException("The address mustn't be empty");
        }
        mConnectionService.connect(mAddress, this);
    }

    @Override
    public void setAddressToConnect(String address) {
        mAddress = address;
    }

    @Override
    public void onSuccessfulConnection() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onConnectionFinish();
            }
        });
    }

    @Override
    public void onUnsuccessfulConnection() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onFailure();
            }
        });
    }

    @Override
    public void onInvalidAddress() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onInvalidAddress();
            }
        });
    }

    @Override
    public void onUnexpectedError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onUnknownError();
            }
        });
    }
}
