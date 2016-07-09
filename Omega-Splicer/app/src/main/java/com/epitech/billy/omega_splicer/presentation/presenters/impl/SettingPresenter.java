package com.epitech.billy.omega_splicer.presentation.presenters.impl;

import com.epitech.billy.omega_splicer.App;
import com.epitech.billy.omega_splicer.domain.executors.IMainThread;
import com.epitech.billy.omega_splicer.domain.executors.IThreadExecutor;
import com.epitech.billy.omega_splicer.presentation.presenters.AbstractPresenter;
import com.epitech.billy.omega_splicer.presentation.presenters.ISettingPresenter;
import com.epitech.billy.omega_splicer.presentation.storage.SettingSharedPreferencesStore;

/**
 * Created by bichon_b on 3/4/16.
 * <p/>
 * This is the presenter of the settings, it will notify the view when the current setting are fetch and will save them if the user change them.
 * <p/>
 */
public class SettingPresenter extends AbstractPresenter implements ISettingPresenter {

    // View
    private View mSettingView;

    // Storage
    private SettingSharedPreferencesStore mSettingSharedPreferencesStore;

    public SettingPresenter(IThreadExecutor threadExecutor, IMainThread mainThread, View settingView, SettingSharedPreferencesStore settingSharedPreferencesStore) {
        super(threadExecutor, mainThread);
        mSettingView = settingView;
        mSettingSharedPreferencesStore = settingSharedPreferencesStore;
    }

    @Override
    public void resume() {
        mSettingView.showOrientationSetting(mSettingSharedPreferencesStore.getCurrentOrientationSetting());
        mSettingView.showControlSetting(mSettingSharedPreferencesStore.getCurrentControlSetting());
        mSettingView.showSpeedUnitSetting(mSettingSharedPreferencesStore.getCurrentSpeedUnitSetting());
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void changeOrientationSetting(String newOrientationSetting) {
        mSettingSharedPreferencesStore.changeOrientationSetting(newOrientationSetting);
        App.getInstance().setGeneralOrientation(newOrientationSetting);
        mSettingView.changeOrientation(newOrientationSetting);
    }

    @Override
    public void changeControlSetting(String newControlSetting) {
        mSettingSharedPreferencesStore.changeControlSetting(newControlSetting);
    }

    @Override
    public void changeSpeedUnitSetting(String newSpeedUnitSetting) {
        mSettingSharedPreferencesStore.changeSpeedUnitSetting(newSpeedUnitSetting);
    }
}
