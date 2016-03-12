package com.epitech.billy.omega_splicer.presentation.presenters;

import com.epitech.billy.omega_splicer.domain.executors.IMainThread;
import com.epitech.billy.omega_splicer.domain.executors.IThreadExecutor;

/**
 * Created by bichon_b on 3/3/16.
 * <p/>
 * This is a based class for all presenters which are communicating with interactors.
 * This base class will hold a reference to the Executor and MainThread objects that are needed for running interactors in a background thread.
 */
public class AbstractPresenter implements IBasePresenter {

    protected IThreadExecutor mThreadExecutor;
    protected IMainThread mMainThread;

    public AbstractPresenter(IThreadExecutor threadExecutor, IMainThread mainThread) {
        mThreadExecutor = threadExecutor;
        mMainThread = mainThread;
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
}
