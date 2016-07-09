package com.epitech.billy.omega_splicer.presentation.presenters;

import com.epitech.billy.omega_splicer.presentation.models.PlaneModel;
import com.epitech.billy.omega_splicer.presentation.ui.IBaseView;

import java.util.List;

/**
 * Created by Billy on 07/04/2016.
 */
public interface IScannedDevicesPresenter extends IBasePresenter {
    interface View extends IBaseView {
        void addPlaneToList(PlaneModel plane);
        void addPlanesToList(List<PlaneModel> planes);
        void displayErrorBluetooth(String error);
    }
    void scan();
}
