package com.epitech.billy.omega_splicer.domain.data;

/**
 * Created by Billy on 08/04/2016.
 */
public interface IBluetoothConnectionManager {
    interface Callback {
        void onUnsupported();
        void onDeactivated();
        void onActivated();
    }

    void start(Callback callback);
    void stop();
}
