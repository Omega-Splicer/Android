package com.epitech.billy.omega_splicer.presentation.ui.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.epitech.billy.omega_splicer.App;
import com.epitech.billy.omega_splicer.R;
import com.epitech.billy.omega_splicer.domain.executors.impl.ThreadExecutor;
import com.epitech.billy.omega_splicer.presentation.presenters.ISettingPresenter;
import com.epitech.billy.omega_splicer.presentation.presenters.impl.SettingPresenter;
import com.epitech.billy.omega_splicer.presentation.storage.SettingSharedPreferencesStore;
import com.epitech.billy.omega_splicer.threading.MainThread;

/**
 * Created by bichon_b on 3/4/16.
 * <p/>
 * This activity will show the setting to the user and allow him to change them according to his choices
 */
public class SettingActivity extends AppCompatActivity implements ISettingPresenter.View {

    // UI stuff
    private RadioButton mPortraitOrientation;
    private RadioButton mLandscapeOrientation;
    private RadioButton mButtonControl;
    private RadioButton mGyroscopeControl;
    private RadioButton mKilometerPerHour;
    private RadioButton mMeterPerSecond;
    private RadioButton mMilesPerHour;


    // presenter
    private ISettingPresenter mSettingPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation((App.getInstance().getGeneralOrientation().equals(getString(R.string.landscape_orientation_shared_preference))) ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mSettingPresenter = new SettingPresenter(ThreadExecutor.getInstance(), MainThread.getInstance(), this, new SettingSharedPreferencesStore(this));

        mPortraitOrientation = (RadioButton) findViewById(R.id.setting_activity_orientation_portrait);
        mLandscapeOrientation = (RadioButton) findViewById(R.id.setting_activity_orientation_landscape);
        mButtonControl = (RadioButton) findViewById(R.id.setting_activity_control_with_buttons);
        mGyroscopeControl = (RadioButton) findViewById(R.id.setting_activity_control_with_gyroscope);
        mKilometerPerHour = (RadioButton) findViewById(R.id.setting_activity_speed_unit_km_h);
        mMeterPerSecond = (RadioButton) findViewById(R.id.setting_activity_speed_unit_m_s);
        mMilesPerHour = (RadioButton) findViewById(R.id.setting_activity_speed_unit_mph);

        setupOrientationRadioGroup();
        setupControlRadioGroup();
        setupSpeedUnitRadioGroup();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSettingPresenter.resume();
    }

    @Override
    protected void onPause() {
        mSettingPresenter.pause();
        super.onPause();
    }

    private void setupOrientationRadioGroup() {
        System.out.println("Setup the orientation");
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.setting_activity_orientation_radio_group);
        if (radioGroup == null)
            return;

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (radioGroup.findViewById(checkedId) == mPortraitOrientation) {
                    mSettingPresenter.changeOrientationSetting(getString(R.string.portrait_orientation_shared_preference));
                } else if (radioGroup.findViewById(checkedId) == mLandscapeOrientation) {
                    mSettingPresenter.changeOrientationSetting(getString(R.string.landscape_orientation_shared_preference));
                }
            }
        });
    }

    private void setupControlRadioGroup() {
        System.out.println("Setup the controls");
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.setting_activity_controls_radio_group);
        if (radioGroup == null)
            return;

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group.findViewById(checkedId) == mButtonControl) {
                    mSettingPresenter.changeControlSetting(getString(R.string.button_control_shared_preference));
                } else if (group.findViewById(checkedId) == mGyroscopeControl) {
                    mSettingPresenter.changeControlSetting(getString(R.string.gyroscope_control_shared_preference));
                }
            }
        });

    }

    private void setupSpeedUnitRadioGroup() {
        System.out.println("Setup the speed unit");
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.setting_activity_speed_unit_radio_group);
        if (radioGroup == null)
            return;

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group.findViewById(checkedId) == mKilometerPerHour) {
                    mSettingPresenter.changeSpeedUnitSetting(getString(R.string.km_speed_unit_shared_preference));
                }
                else if (group.findViewById(checkedId) == mMilesPerHour) {
                    mSettingPresenter.changeSpeedUnitSetting(getString(R.string.mph_speed_unit_shared_preference));
                }
                else if (group.findViewById(checkedId) == mMeterPerSecond) {
                    mSettingPresenter.changeSpeedUnitSetting(getString(R.string.ms_speed_unit_shared_preference));
                }
            }
        });
    }

    @Override
    public void showOrientationSetting(String orientationSetting) {
        System.out.println("check the orientation");
        if (orientationSetting.equals(getString(R.string.portrait_orientation_shared_preference)) && mPortraitOrientation != null) {
            mPortraitOrientation.setChecked(true);
        }
        else if (orientationSetting.equals(getString(R.string.landscape_orientation_shared_preference)) && mLandscapeOrientation != null) {
            mLandscapeOrientation.setChecked(true);
        }
    }

    @Override
    public void showControlSetting(String controlSetting) {
        System.out.println("check the controls");
        if (controlSetting.equals(getString(R.string.button_control_shared_preference)) && mButtonControl != null) {
            mButtonControl.setChecked(true);
        }
        else if (controlSetting.equals(getString(R.string.gyroscope_control_shared_preference)) && mGyroscopeControl != null) {
            mGyroscopeControl.setChecked(true);
        }
    }

    @Override
    public void showSpeedUnitSetting(String speedUnitSetting) {
        System.out.println("check the speed unit");
        if (speedUnitSetting.equals(getString(R.string.km_speed_unit_shared_preference)) && mKilometerPerHour != null) {
            mKilometerPerHour.setChecked(true);
        }
        else if (speedUnitSetting.equals(getString(R.string.mph_speed_unit_shared_preference)) && mMilesPerHour != null) {
            mMilesPerHour.setChecked(true);
        }
        else if (speedUnitSetting.equals(getString(R.string.ms_speed_unit_shared_preference)) && mMeterPerSecond != null) {
            mMeterPerSecond.setChecked(true);
        }
    }

    @Override
    public void changeOrientation(String orientation) {
//        recreate();
        setRequestedOrientation((orientation.equals(getString(R.string.landscape_orientation_shared_preference))) ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
