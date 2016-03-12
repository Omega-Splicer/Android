package com.epitech.billy.omega_splicer.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.epitech.billy.omega_splicer.R;
import com.epitech.billy.omega_splicer.domain.data.ISettingSharedPreferencesStore;

/**
 * Created by bichon_b on 3/4/16.
 * <p/>
 * This class provides methods in order to access and change the setting stored in the shared preferences.
 * <p/>
 */
public class SettingSharedPreferencesStore implements ISettingSharedPreferencesStore {

    private Context mContext;
    private SharedPreferences mSharedPreferences;

    public SettingSharedPreferencesStore(Context context) {
        mSharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_preference_file_key), Context.MODE_PRIVATE);
        mContext = context;
    }

    @Override
    public String getCurrentControlSetting() {
        return mSharedPreferences.getString(mContext.getString(R.string.control_shared_preference), mContext.getString(R.string.default_control_value_shared_preference));
    }

    @Override
    public void changeControlSetting(String controlSetting) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(mContext.getString(R.string.control_shared_preference), controlSetting);
        editor.apply();
    }

    @Override
    public String getCurrentSpeedUnitSetting() {
        return mSharedPreferences.getString(mContext.getString(R.string.speed_unit_shared_preference), mContext.getString(R.string.default_speed_unit_value_shared_preference));
    }

    @Override
    public void changeSpeedUnitSetting(String speedUnitSetting) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(mContext.getString(R.string.speed_unit_shared_preference), speedUnitSetting);
        editor.apply();
    }
}
