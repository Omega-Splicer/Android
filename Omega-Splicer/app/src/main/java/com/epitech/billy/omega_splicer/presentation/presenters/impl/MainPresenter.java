package com.epitech.billy.omega_splicer.presentation.presenters.impl;

import com.epitech.billy.omega_splicer.domain.executors.IMainThread;
import com.epitech.billy.omega_splicer.domain.executors.IThreadExecutor;
import com.epitech.billy.omega_splicer.presentation.presenters.AbstractPresenter;
import com.epitech.billy.omega_splicer.presentation.presenters.IMainPresenter;

/**
 * Created by bichon_b on 3/4/16.
 * <p/>
 * This is the presenter for the main view, his main purpose his to redirect the user to the other view.
 */
public class MainPresenter extends AbstractPresenter implements IMainPresenter {

    private IMainPresenter.View mMainView;

    public MainPresenter(IThreadExecutor threadExecutor, IMainThread mainThread, View mainView) {
        super(threadExecutor, mainThread);
        mMainView = mainView;
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
    public void clickOnFlyButton() {
        mMainView.navigateToFlyView();
    }

    @Override
    public void clickOnPairButton() {
        mMainView.navigateToPairView();
    }

    @Override
    public void clickOnSettingButton() {
        mMainView.navigateToSettingView();
    }
}
