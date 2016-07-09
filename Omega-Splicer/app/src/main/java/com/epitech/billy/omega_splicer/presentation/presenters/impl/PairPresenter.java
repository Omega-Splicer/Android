package com.epitech.billy.omega_splicer.presentation.presenters.impl;

import com.epitech.billy.omega_splicer.domain.data.IBluetoothConnectionManager;
import com.epitech.billy.omega_splicer.domain.executors.IMainThread;
import com.epitech.billy.omega_splicer.domain.executors.IThreadExecutor;
import com.epitech.billy.omega_splicer.domain.interactors.IStartConnectionInteractor;
import com.epitech.billy.omega_splicer.domain.interactors.impl.StartConnectionInteractor;
import com.epitech.billy.omega_splicer.presentation.presenters.AbstractPresenter;
import com.epitech.billy.omega_splicer.presentation.presenters.IPairPresenter;
import com.epitech.billy.omega_splicer.presentation.presenters.IPairedDevicesPresenter;

/**
 * Created by bichon_b on 3/4/16.
 */
public final class PairPresenter extends AbstractPresenter implements IPairPresenter, IStartConnectionInteractor.Callback {

    private View mPairView;
    private IBluetoothConnectionManager mConnectionManager;
    private IStartConnectionInteractor mInteractor;

    // subpresenters
    private IPairedDevicesPresenter mPairedSubviewPresenter;

    public PairPresenter(IThreadExecutor threadExecutor, IMainThread mainThread, View pairView, IBluetoothConnectionManager connectionManager) {
        super(threadExecutor, mainThread);
        mPairView = pairView;
        mConnectionManager = connectionManager;
        mInteractor = new StartConnectionInteractor(mThreadExecutor, mMainThread, this, mConnectionManager);
        mInteractor.execute();
    }

    @Override
    public void resume() {
        mPairedSubviewPresenter.fetch();
    }

    @Override
    public void pause() {}

    @Override
    public void stop() {}

    @Override
    public void destroy() {
        mInteractor.stop();
        mPairView = null;
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void unsupported() {
        // todo
    }

    @Override
    public void deactivate() {
        if (mPairView != null)
            mPairView.displayErrorBluetoothDisabled();
    }

    @Override
    public void activate() {
        mPairedSubviewPresenter.fetch();
    }

    public void setPairedSubviewPresenter(IPairedDevicesPresenter subPresenter) {
        mPairedSubviewPresenter = subPresenter;
    }
}
