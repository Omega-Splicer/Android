package com.epitech.billy.omega_splicer.presentation.presenters;

/**
 * Created by bichon_b on 3/3/16.
 * <p/>
 */
public interface IBasePresenter {
    /**
     * Method that control the lifecycle of the view. It should be called in the view's (Activity, Fragment or CustomView) onResume() method.
     */
    void resume();

    /**
     * Method that control the lifecycle of the view.
     * It should be called in the view's (Activity, Fragment or CustomView) onPause() method.
     */
    void pause();

    /**
     * Method that control the lifecycle of the view.
     * It should be called in the view's (Activity, Fragment or CustomView) onStop() method.
     */
    void stop();

    /**
     * Method that control the lifecycle of the view.
     * It should be called in the view's (Activity, Fragment or CustomView) onDestroy() method.
     */
    void destroy();

    /**
     * Method that should signal the appropriate view to show the appropriate error with the provided message.
     * <p/>
     * @param message The error message
     */
    void onError(String message);
}
