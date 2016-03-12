package com.epitech.billy.omega_splicer.presentation.ui;

/**
 * Created by bichon_b on 3/4/16.
 * <p/>
 * This interface represents a basic view. All view should implement these common methods.
 * <p/>
 */
public interface IBaseView {
    /**
     * This is a general method used for showing some kind of progress during a background task.
     * For example, this method should show a progress bar and/or disable buttons before some background work starts.
     */
    void showLoading();

    /**
     * This is a general method used for hiding progress information after a background task finished.
     */
    void hideLoading();
}
