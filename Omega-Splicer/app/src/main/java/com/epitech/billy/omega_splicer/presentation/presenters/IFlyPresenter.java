package com.epitech.billy.omega_splicer.presentation.presenters;

import com.epitech.billy.omega_splicer.presentation.ui.IBaseView;

/**
 * Created by bichon_b on 3/4/16.
 */
public interface IFlyPresenter extends IBasePresenter {
    interface View extends IBaseView {
        void showMotorSpeed(int motorSpeed);
    }
    void changeMotorSpeed(int motorSpeed);
}
