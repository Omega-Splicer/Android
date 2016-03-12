package com.epitech.billy.omega_splicer.domain.interactors;

import com.epitech.billy.omega_splicer.domain.executors.IThreadExecutor;
import com.epitech.billy.omega_splicer.domain.executors.IMainThread;

/**
 * Created by bichon_b on 3/3/16.
 * <p/>
 * This abstract class implements some common methods for all interactors. Cancelling an interactor, check if it's running and finishing an interactor has mostly the same code throughout so that is why this class was created.
 * Field methods are declared volatile as we might use this method from different threads (mainly from the UI one).
 * <p/>
 * For example, when an activity is getting destroyed then we should probably cancel an interactor but the request will come from the UI thread unless the request was specifically assigned to a background thread.
 * <p/>
 */
public abstract class AbstractInteractor implements IBaseInteractor {

    protected IThreadExecutor mThreadExecutor;
    protected IMainThread mMainThread;

    protected volatile boolean mIsCanceled;
    protected volatile boolean mIsRunning;

    public AbstractInteractor(IThreadExecutor threadExecutor, IMainThread mainThread) {
        mThreadExecutor = threadExecutor;
        mMainThread = mainThread;
    }

    /**
     * This method contains the actual business logic of the interactor. IT SHOULD NOT BE USED DIRECTLY but, instead, a developer should call the execute() method of an interactor to make sure the operation is done on a background thread.
     * <p/>
     * This method should only be called directly while doing unit/integration tests. That is the only reason it is declared public as to help with easier testing.
     */
    public abstract void run();

    public void cancel() {
        mIsCanceled = true;
        mIsRunning = false;
    }

    public boolean isRunning() {
        return isRunning();
    }

    public void onFinished() {
        mIsCanceled = false;
        mIsRunning = false;
    }

    @Override
    public void execute() {
        mIsRunning = true;
        mThreadExecutor.execute(this);
    }
}
