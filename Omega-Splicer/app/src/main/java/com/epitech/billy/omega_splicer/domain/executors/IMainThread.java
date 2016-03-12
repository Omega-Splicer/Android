package com.epitech.billy.omega_splicer.domain.executors;

/**
 * Created by bichon_b on 3/3/16.
 * </p>
 * This interface will define a class that will enable interactors to run certains operations on the main UI thread.
 * For example, if an interactor needs to show an object to the UI this can be used to make sure the show method is called on the UI thread.
 * </p>
 */
public interface IMainThread {
    /**
     * Make runnable operation run in the main UI thread.
     * @param runnable The runnable to run
     */
    void post(final Runnable runnable);
}
