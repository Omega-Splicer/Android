package com.epitech.billy.omega_splicer.presentation.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.epitech.billy.omega_splicer.R;
import com.epitech.billy.omega_splicer.presentation.ui.IBaseView;

/**
 * Created by bichon_b on 3/4/16.
 * <p/>
 * This class provides methods in order to access and change the setting stored in the shared preferences.
 * <p/>
 */
public class SettingSharedPreferencesStore {

    private Context mContext;
    private SharedPreferences mSharedPreferences;

    public SettingSharedPreferencesStore(Context context) {
        mSharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_preference_file_key), Context.MODE_PRIVATE);
        mContext = context;
    }

    /**
     * Fetch the user's orientation preference previously saved by {@link #changeOrientationSetting(String)}
     * @return The user's orientation preference
     */
    public String getCurrentOrientationSetting() {
        return mSharedPreferences.getString(mContext.getString(R.string.orientation_shared_preference), mContext.getString(R.string.default_orientation_value_shared_preference));
    }

    /**
     * Save the user's orientation preference
     * @param orientationSetting The orientation to be save
     */
    public void changeOrientationSetting(String orientationSetting) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(mContext.getString(R.string.orientation_shared_preference), orientationSetting);
        editor.apply();
    }

    /**
     * Fetch the user's control preference previously saved by {@link #changeControlSetting(String)}
     * @return The user's control preference
     */
    public String getCurrentControlSetting() {
        return mSharedPreferences.getString(mContext.getString(R.string.control_shared_preference), mContext.getString(R.string.default_control_value_shared_preference));
    }

    /**
     * Save the user's control preference
     * @param controlSetting The type of control to be save
     */
    public void changeControlSetting(String controlSetting) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(mContext.getString(R.string.control_shared_preference), controlSetting);
        editor.apply();
    }

    /**
     * Fetch the user's speed unit preference previously saved by {@link #changeSpeedUnitSetting(String)}
     * @return The user's speed unit preference
     */
    public String getCurrentSpeedUnitSetting() {
        return mSharedPreferences.getString(mContext.getString(R.string.speed_unit_shared_preference), mContext.getString(R.string.default_speed_unit_value_shared_preference));
    }

    /**
     * Save the user's speed unit preference
     * @param speedUnitSetting The type of speed unit to be save
     */
    public void changeSpeedUnitSetting(String speedUnitSetting) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(mContext.getString(R.string.speed_unit_shared_preference), speedUnitSetting);
        editor.apply();
    }
}
