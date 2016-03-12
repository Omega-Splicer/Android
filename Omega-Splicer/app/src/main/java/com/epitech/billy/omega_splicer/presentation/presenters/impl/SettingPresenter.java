package com.epitech.billy.omega_splicer.presentation.presenters.impl;

import com.epitech.billy.omega_splicer.domain.data.ISettingSharedPreferencesStore;
import com.epitech.billy.omega_splicer.domain.executors.IMainThread;
import com.epitech.billy.omega_splicer.domain.executors.IThreadExecutor;
import com.epitech.billy.omega_splicer.domain.interactors.IChangeControlSettingInteractor;
import com.epitech.billy.omega_splicer.domain.interactors.IChangeSpeedUnitSettingInteractor;
import com.epitech.billy.omega_splicer.domain.interactors.IGetCurrentSettingsInteractor;
import com.epitech.billy.omega_splicer.domain.interactors.impl.ChangeControlSettingInteractor;
import com.epitech.billy.omega_splicer.domain.interactors.impl.ChangeSpeedUnitSettingInteractor;
import com.epitech.billy.omega_splicer.domain.interactors.impl.GetCurrentSettingsInteractor;
import com.epitech.billy.omega_splicer.presentation.presenters.AbstractPresenter;
import com.epitech.billy.omega_splicer.presentation.presenters.ISettingPresenter;

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
    private ISettingSharedPreferencesStore mSettingSharedPreferencesStore;

    // Interactors
    private IChangeControlSettingInteractor mChangeControlSettingInteractor;
    private IChangeSpeedUnitSettingInteractor mChangeSpeedUnitSettingInteractor;

    public SettingPresenter(IThreadExecutor threadExecutor, IMainThread mainThread, View settingView, ISettingSharedPreferencesStore settingSharedPreferencesStore) {
        super(threadExecutor, mainThread);
        mSettingView = settingView;
        mSettingSharedPreferencesStore = settingSharedPreferencesStore;
        mChangeControlSettingInteractor = new ChangeControlSettingInteractor(mThreadExecutor, mMainThread, mSettingSharedPreferencesStore);
        mChangeSpeedUnitSettingInteractor = new ChangeSpeedUnitSettingInteractor(mThreadExecutor, mMainThread, mSettingSharedPreferencesStore);
    }

    @Override
    public void resume() {
        super.resume();
        IGetCurrentSettingsInteractor interactor = new GetCurrentSettingsInteractor(mThreadExecutor, mMainThread, mSettingSharedPreferencesStore);
        mSettingView.showControlSetting(interactor.getCurrentControlSetting());
        mSettingView.showSpeedUnitSetting(interactor.getCurrentSpeedUnitSetting());
    }

    @Override
    public void changeControlSetting(String newControlSetting) {
        mChangeControlSettingInteractor.setNewControlSetting(newControlSetting);
        mChangeControlSettingInteractor.execute();
    }

    @Override
    public void changeSpeedUnitSetting(String newSpeedUnitSetting) {
        mChangeSpeedUnitSettingInteractor.setNewSpeedUnitSetting(newSpeedUnitSetting);
        mChangeSpeedUnitSettingInteractor.execute();
    }
}
