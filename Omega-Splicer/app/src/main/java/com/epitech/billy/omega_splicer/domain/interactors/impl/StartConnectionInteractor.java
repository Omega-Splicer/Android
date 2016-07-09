package com.epitech.billy.omega_splicer.domain.interactors.impl;

import com.epitech.billy.omega_splicer.domain.data.IBluetoothConnectionManager;
import com.epitech.billy.omega_splicer.domain.executors.IMainThread;
import com.epitech.billy.omega_splicer.domain.executors.IThreadExecutor;
import com.epitech.billy.omega_splicer.domain.interactors.AbstractInteractor;
import com.epitech.billy.omega_splicer.domain.interactors.IStartConnectionInteractor;

/**
 * Created by Billy on 08/04/2016.
 */
public class StartConnectionInteractor extends AbstractInteractor implements IStartConnectionInteractor, IBluetoothConnectionManager.Callback {

    private Callback mCallback;
    private IBluetoothConnectionManager mConnectionManager;

    public StartConnectionInteractor(IThreadExecutor threadExecutor, IMainThread mainThread, Callback callback, IBluetoothConnectionManager connectionManager) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mConnectionManager = connectionManager;
    }

    @Override
    public void run() {
        mConnectionManager.start(this);
    }

    @Override
    public void stop() {
        mConnectionManager.stop();
    }

    @Override
    public void onUnsupported() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.unsupported();
            }
        });
    }

    @Override
    public void onDeactivated() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.deactivate();
            }
        });
    }

    @Override
    public void onActivated() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.activate();
            }
        });
    }
}
