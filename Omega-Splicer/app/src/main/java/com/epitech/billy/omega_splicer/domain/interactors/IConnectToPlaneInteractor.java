package com.epitech.billy.omega_splicer.domain.interactors;

/**
 * Created by Billy on 09/07/2016.
 */
public interface IConnectToPlaneInteractor extends IBaseInteractor {
    interface Callback {
        void onConnectionFinish();
        void onFailure();
        void onMissingAddress();
        void onInvalidAddress();
        void onUnknownError();
    }
    void setAddressToConnect(String address);
}
