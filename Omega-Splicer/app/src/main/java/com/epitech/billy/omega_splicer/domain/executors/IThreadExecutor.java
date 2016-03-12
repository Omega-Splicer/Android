package com.epitech.billy.omega_splicer.domain.executors;

import com.epitech.billy.omega_splicer.domain.interactors.AbstractInteractor;

/**
 * Created by bichon_b on 3/3/16.
 * <p/>
 * The executor is responsible for running interactor on background threads.
 * <p/>
 */
public interface IThreadExecutor {
    /**
     * This method should call the interactor's run method and thus start the interactor.
     * This should be called on a background thread as interactors might do lenghty operations.
     * @param interactor The interactor which as instructions to execute.
     */
    void execute(final AbstractInteractor interactor);
}
