package com.epitech.billy.omega_splicer.domain.interactors.impl;

import com.epitech.billy.omega_splicer.domain.data.ISettingSharedPreferencesStore;
import com.epitech.billy.omega_splicer.domain.executors.IMainThread;
import com.epitech.billy.omega_splicer.domain.executors.IThreadExecutor;
import com.epitech.billy.omega_splicer.domain.interactors.AbstractInteractor;
import com.epitech.billy.omega_splicer.domain.interactors.IChangeSpeedUnitSettingInteractor;

/**
 * Created by bichon_b on 3/5/16.
 */
public class ChangeSpeedUnitSettingInteractor extends AbstractInteractor implements IChangeSpeedUnitSettingInteractor {

    private ISettingSharedPreferencesStore mSettingSharedPreferencesStore;
    private String mSpeedUnitSetting;

    public ChangeSpeedUnitSettingInteractor(IThreadExecutor threadExecutor, IMainThread mainThread, ISettingSharedPreferencesStore settingSharedPreferencesStore) {
        super(threadExecutor, mainThread);
        mSettingSharedPreferencesStore = settingSharedPreferencesStore;
    }

    @Override
    public void run() {
        mSettingSharedPreferencesStore.changeSpeedUnitSetting(mSpeedUnitSetting);
    }

    @Override
    public void setNewSpeedUnitSetting(String speedUnitSsetting) {
        mSpeedUnitSetting = speedUnitSsetting;
    }
}
