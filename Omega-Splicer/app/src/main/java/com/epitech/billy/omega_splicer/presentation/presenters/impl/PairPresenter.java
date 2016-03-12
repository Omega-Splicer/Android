package com.epitech.billy.omega_splicer.presentation.presenters.impl;

import com.epitech.billy.omega_splicer.domain.executors.IMainThread;
import com.epitech.billy.omega_splicer.domain.executors.IThreadExecutor;
import com.epitech.billy.omega_splicer.presentation.presenters.AbstractPresenter;
import com.epitech.billy.omega_splicer.presentation.presenters.IPairPresenter;

/**
 * Created by bichon_b on 3/4/16.
 */
public class PairPresenter extends AbstractPresenter implements IPairPresenter {

    private  View mPairView;

    public PairPresenter(IThreadExecutor threadExecutor, IMainThread mainThread, View pairView) {
        super(threadExecutor, mainThread);
        mPairView = pairView;
    }
}
