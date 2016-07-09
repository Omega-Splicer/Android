package com.epitech.billy.omega_splicer.domain.data;

import android.content.Context;

/**
 * Created by Billy on 24/05/2016.
 */
public interface IConnectWithOmegaSplicerPlane {
    interface Callback {
        void onSuccessfulConnection();
        void onUnsuccessfulConnection();
        void onInvalidAddress();
        void onUnexpectedError();
    }
    void connect(String macAddress, Callback callback);
    void disconnect();
}
