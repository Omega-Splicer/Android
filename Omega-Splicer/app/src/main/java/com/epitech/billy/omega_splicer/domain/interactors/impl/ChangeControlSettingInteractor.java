package com.epitech.billy.omega_splicer.domain.interactors.impl;

import com.epitech.billy.omega_splicer.domain.data.ISettingSharedPreferencesStore;
import com.epitech.billy.omega_splicer.domain.executors.IMainThread;
import com.epitech.billy.omega_splicer.domain.executors.IThreadExecutor;
import com.epitech.billy.omega_splicer.domain.interactors.AbstractInteractor;
import com.epitech.billy.omega_splicer.domain.interactors.IChangeControlSettingInteractor;

/**
 * Created by bichon_b on 3/4/16.
 */
public class ChangeControlSettingInteractor extends AbstractInteractor implements IChangeControlSettingInteractor {

    private ISettingSharedPreferencesStore mSettingSharedPreferencesStore;
    private String mControlSetting;

    public ChangeControlSettingInteractor(IThreadExecutor threadExecutor, IMainThread mainThread, ISettingSharedPreferencesStore settingSharedPreferencesStore) {
        super(threadExecutor, mainThread);
        mSettingSharedPreferencesStore = settingSharedPreferencesStore;
    }

    @Override
    public void run() {
        mSettingSharedPreferencesStore.changeControlSetting(mControlSetting);
    }

    @Override
    public void setNewControlSetting(String newControlSetting) {
        mControlSetting = newControlSetting;
    }
}
