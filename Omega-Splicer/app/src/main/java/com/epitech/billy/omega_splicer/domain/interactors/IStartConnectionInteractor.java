package com.epitech.billy.omega_splicer.domain.interactors;

/**
 * Created by Billy on 08/04/2016.
 */
public interface IStartConnectionInteractor extends IBaseInteractor {
    interface Callback {
        void unsupported();
        void deactivate();
        void activate();
    }
    void stop();
}
