package com.epitech.billy.omega_splicer.presentation.presenters;

import com.epitech.billy.omega_splicer.presentation.models.Plane;
import com.epitech.billy.omega_splicer.presentation.ui.IBaseView;

/**
 * Created by bichon_b on 3/4/16.
 */
public interface IPairPresenter extends IBasePresenter {
    interface View extends IBaseView {
        void addPlaneToList(Plane plane);
        void displayErrorBluetooth(String error);
        void displayErrorBluetoothDisabled();
    }
}
