package com.epitech.billy.omega_splicer.presentation.presenters.impl;

import com.epitech.billy.omega_splicer.domain.executors.IMainThread;
import com.epitech.billy.omega_splicer.domain.executors.IThreadExecutor;
import com.epitech.billy.omega_splicer.presentation.presenters.AbstractPresenter;
import com.epitech.billy.omega_splicer.presentation.presenters.IFlyPresenter;

/**
 * Created by bichon_b on 3/4/16.
 */
public class FlyPresenter extends AbstractPresenter implements IFlyPresenter {

    private View mFlyView;
    private int mMotorSpeed;

    public FlyPresenter(IThreadExecutor threadExecutor, IMainThread mainThread, View flyView) {
        super(threadExecutor, mainThread);
        mFlyView = flyView;
        mMotorSpeed = 0;
        System.out.println("creating the fly presenter");
    }

    @Override
    public void resume() {
        mFlyView.showMotorSpeed(mMotorSpeed);
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
    public void changeMotorSpeed(int motorSpeed) {
        System.out.println("inside the change of presenter");
        mMotorSpeed = motorSpeed;
        mFlyView.showMotorSpeed(mMotorSpeed);
    }
}
