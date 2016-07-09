package com.epitech.billy.omega_splicer.presentation.presenters;

import com.epitech.billy.omega_splicer.presentation.ui.IBaseView;

/**
 * Created by bichon_b on 3/4/16.
 */
public interface ISettingPresenter extends IBasePresenter {
    interface View extends IBaseView {
        void showOrientationSetting(String orientationSetting);
        void showControlSetting(String controlSetting);
        void showSpeedUnitSetting(String speedUnitSetting);
        void changeOrientation(String orientation);
    }

    void changeOrientationSetting(String newOrientationSetting);
    void changeControlSetting(String newControlSetting);
    void changeSpeedUnitSetting(String newSpeedUnitSetting);
}
