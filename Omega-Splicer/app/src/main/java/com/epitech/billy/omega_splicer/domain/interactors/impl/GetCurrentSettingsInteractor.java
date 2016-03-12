package com.epitech.billy.omega_splicer.domain.interactors.impl;

import com.epitech.billy.omega_splicer.domain.data.ISettingSharedPreferencesStore;
import com.epitech.billy.omega_splicer.domain.executors.IMainThread;
import com.epitech.billy.omega_splicer.domain.executors.IThreadExecutor;
import com.epitech.billy.omega_splicer.domain.interactors.AbstractInteractor;
import com.epitech.billy.omega_splicer.domain.interactors.IGetCurrentSettingsInteractor;

/**
 * Created by bichon_b on 3/4/16.
 * <p/>
 * This interactor get the current settings for the control and the speed unit.
 * <p/>
 */
public class GetCurrentSettingsInteractor extends AbstractInteractor implements IGetCurrentSettingsInteractor {

    private ISettingSharedPreferencesStore mSettingSharedPreferencesStore;

    public GetCurrentSettingsInteractor(IThreadExecutor threadExecutor, IMainThread mainThread, ISettingSharedPreferencesStore settingSharedPreferencesStore) {
        super(threadExecutor, mainThread);
        mSettingSharedPreferencesStore = settingSharedPreferencesStore;
    }

    // empty because we don't need any job to be run on the background thread
    @Override
    public void run() {}


    @Override
    public String getCurrentControlSetting() {
        return mSettingSharedPreferencesStore.getCurrentControlSetting();
    }

    @Override
    public String getCurrentSpeedUnitSetting() {
        return mSettingSharedPreferencesStore.getCurrentSpeedUnitSetting();
    }
}
