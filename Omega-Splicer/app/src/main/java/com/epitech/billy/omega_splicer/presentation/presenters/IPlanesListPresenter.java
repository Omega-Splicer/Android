package com.epitech.billy.omega_splicer.presentation.presenters;

import com.epitech.billy.omega_splicer.presentation.ui.IBaseView;

/**
 * Created by Billy on 09/07/2016.
 */
public interface IPlanesListPresenter extends IBasePresenter {
    interface View extends IBaseView {
        void displayConnectingStatus();
        void displayConnectedStatus();
        void resetStatus();
        void showInvalidAddressError();
        void showMissingAddressError();
        void showUnknownError();
        void showCantConnectError();
    }
    void clickOnPlane(String macAddress);
}
