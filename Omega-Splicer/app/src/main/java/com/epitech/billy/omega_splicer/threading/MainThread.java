package com.epitech.billy.omega_splicer.threading;

import android.os.Handler;
import android.os.Looper;

import com.epitech.billy.omega_splicer.domain.executors.IMainThread;

/**
 * Created by bichon_b on 3/3/16.
 * </p> This class makes sure that the  runnable we provide will be run on the main UI thread
 */
public class MainThread implements IMainThread {

    private static MainThread ourInstance;
    private Handler mHandler;

    private MainThread() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static MainThread getInstance() {
        if (ourInstance == null) {
            ourInstance = new MainThread();
        }
        return ourInstance;
    }

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }
}
