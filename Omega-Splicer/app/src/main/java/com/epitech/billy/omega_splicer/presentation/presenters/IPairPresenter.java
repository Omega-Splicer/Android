package com.epitech.billy.omega_splicer.presentation.presenters;

import com.epitech.billy.omega_splicer.presentation.models.PlaneModel;
import com.epitech.billy.omega_splicer.presentation.ui.IBaseView;

import java.util.List;

/**
 * Created by bichon_b on 3/4/16.
 */
public interface IPairPresenter extends IBasePresenter {
    interface View extends IBaseView {
        void displayErrorBluetoothDisabled();
    }
    void setPairedSubviewPresenter(IPairedDevicesPresenter subviewPresenter);
}
