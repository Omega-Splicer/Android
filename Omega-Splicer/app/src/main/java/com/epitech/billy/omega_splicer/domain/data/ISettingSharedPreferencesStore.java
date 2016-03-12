package com.epitech.billy.omega_splicer.domain.data;

/**
 * Created by bichon_b on 3/4/16.
 */
public interface ISettingSharedPreferencesStore {
    String getCurrentControlSetting();
    void changeControlSetting(String controlSetting);
    String getCurrentSpeedUnitSetting();
    void changeSpeedUnitSetting(String speedUnitSetting);
}
