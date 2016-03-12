package com.epitech.billy.omega_splicer.presentation.presenters;

import com.epitech.billy.omega_splicer.presentation.ui.IBaseView;

/**
 * Created by bichon_b on 3/4/16.
 * <p/>
 * The interface of the main presenter.
 * This work as a boundary between the view and the presenter.
 */
public interface IMainPresenter extends IBasePresenter {
    interface View extends IBaseView {
        void navigateToFlyView();
        void navigateToPairView();
        void navigateToSettingView();
    }
    void clickOnFlyButton();
    void clickOnPairButton();
    void clickOnSettingButton();
}
