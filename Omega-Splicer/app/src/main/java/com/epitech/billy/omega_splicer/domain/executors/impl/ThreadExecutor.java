package com.epitech.billy.omega_splicer.domain.executors.impl;

import com.epitech.billy.omega_splicer.domain.executors.IThreadExecutor;
import com.epitech.billy.omega_splicer.domain.interactors.AbstractInteractor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by bichon_b on 3/3/16.
 * <p/>
 * This singleton class will make sure that each interactor gets a background thread to be run on.
 * <p/>
 */
public class ThreadExecutor implements IThreadExecutor {

    public static volatile ThreadExecutor ourInstance;

    private static final int                        CORE_POOL_SIZE = 3;
    private static final int                        MAX_POOL_SIZE = 5;
    private static final int                        KEEP_ALIVE_TIME = 120;
    private static final TimeUnit                   TIME_UNIT = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable>    WORK_QUEUE = new LinkedBlockingDeque<>();

    private ThreadPoolExecutor mThreadPoolExecutor;

    private ThreadExecutor() {
        long keepAlive = KEEP_ALIVE_TIME;
        mThreadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, keepAlive, TIME_UNIT, WORK_QUEUE);
    }

    /**
     * Return a singleton instance of this executor. If the executor  si not initialized then it initializes it and returns the instance.
     */
    public static ThreadExecutor getInstance() {
        if (ourInstance == null) {
            ourInstance = new ThreadExecutor();
        }
        return ourInstance;
    }

    @Override
    public void execute(final AbstractInteractor interactor) {
        mThreadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                // run the main logic
                interactor.run();
                // mark it as finished
                interactor.onFinished();
            }
        });
    }
}
