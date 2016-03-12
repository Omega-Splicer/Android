package com.epitech.billy.omega_splicer.domain.interactors;

/**
 * Created by bichon_b on 3/3/16.
 * <p/>
 * This is the main interface of an interactor.
 * Each interactor serves a specific use case.
 * <p/>
 */
public interface IBaseInteractor {
    /**
     * THe main method that starts an interactor. It will make sure that the interactor is done on a background thread.
     */
    void execute();
}
