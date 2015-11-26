package com.epitech.billy.omega_splicer.Setting;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.epitech.billy.omega_splicer.R;

/**
 * Created by Billy on 01/09/2015.
 *
 * Allow the user to change his preferences through several options
 */
public class SettingActivity extends Activity {

    private SharedPreferences mSharedPreferences;

    // Control radio group
    private RadioButton mButtonControl;
    private RadioButton mGyroscopeControl;

    // Spped's unit radio group
    private RadioButton mKilometerPerHour;
    private RadioButton mMeterPerSecond;
    private RadioButton mMilesPerHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mSharedPreferences = getSharedPreferences(getString(R.string.shared_preference_file_key), MODE_PRIVATE);

        findViewById(R.id.setting_activity_back_cloud).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mButtonControl = (RadioButton) findViewById(R.id.setting_activity_control_with_buttons);
        mGyroscopeControl = (RadioButton) findViewById(R.id.setting_activity_control_with_gyroscope);
        setupControlRadioGroup();

        mKilometerPerHour = (RadioButton) findViewById(R.id.setting_activity_speed_unit_km_h);
        mMeterPerSecond = (RadioButton) findViewById(R.id.setting_activity_speed_unit_m_s);
        mMilesPerHour = (RadioButton) findViewById(R.id.setting_activity_speed_unit_mph);
        setupSpeedUnitRadioGRoup();
    }

    @Override
    public void onBackPressed() {
        animatedClose();
        super.onBackPressed();
    }

    /**
     * If the device is running under post lollipop version (API >= 21), launch animations
     * If not, do nothing
     */
    private void animatedClose() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ((ImageView) findViewById(R.id.setting_activity_back_cloud)).setImageDrawable(null);

            final TextView text = (TextView) findViewById(R.id.setting_activity_title);
            text.setTextColor(Color.BLACK);

            final float startValue = 40;
            final float endValue = 25;

            ValueAnimator animator = ValueAnimator.ofFloat(startValue, endValue);
            animator.setDuration(150);

            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    text.setTextSize(TypedValue.COMPLEX_UNIT_SP, (float) animation.getAnimatedValue());
                    if ((float) animation.getAnimatedValue() <= 75) {
                        text.setAllCaps(false);
                    }
                }
            });
            animator.start();
        }
    }

    /**
     * Display the controls setting from the shared preference, if it's the first time display the defualt settings
     * Save every changes in the shared preferences
     */
    private void setupControlRadioGroup() {
        if (mSharedPreferences.contains(getString(R.string.control_shared_preference))) {
            if (mSharedPreferences.getString(getString(R.string.control_shared_preference), getString(R.string.default_control_value_shared_preference)).equals(getString(R.string.gyroscope_control_shared_preference))) {
                mGyroscopeControl.setChecked(true);
            }
            else {
                mButtonControl.setChecked(true);
            }
        }

        final SharedPreferences.Editor editor = mSharedPreferences.edit();

        RadioGroup controlRadioGroup = (RadioGroup) findViewById(R.id.setting_activity_controls_radio_group);
        controlRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                editor.putString(getString(R.string.control_shared_preference), radioGroup.findViewById(i).getTag().toString());
                editor.apply();
            }
        });
    }

    /**
     * Display the speed's unit setting from the shared preference, if it's the first time display the defualt settings
     * Save every changes in the shared preferences
     */
    private void setupSpeedUnitRadioGRoup() {
        if (mSharedPreferences.contains(getString(R.string.speed_unit_shared_preference))) {
            if (mSharedPreferences.getString(getString(R.string.speed_unit_shared_preference), getString(R.string.default_speed_unit_value_shared_preference)).equals(getString(R.string.km_speed_unit_shared_preference))) {
                mKilometerPerHour.setChecked(true);
            }
            else if (mSharedPreferences.getString(getString(R.string.speed_unit_shared_preference), getString(R.string.default_speed_unit_value_shared_preference)).equals(getString(R.string.ms_speed_unit_shared_preference))) {
                mMeterPerSecond.setChecked(true);
            }
            else if (mSharedPreferences.getString(getString(R.string.speed_unit_shared_preference), getString(R.string.default_speed_unit_value_shared_preference)).equals(getString(R.string.mph_speed_unit_shared_preference))) {
                mMilesPerHour.setChecked(true);
            }
        }

        final SharedPreferences.Editor editor = mSharedPreferences.edit();

        RadioGroup speedUnitRadioGroup = (RadioGroup) findViewById(R.id.setting_activity_speed_unit_radio_group);
        speedUnitRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                editor.putString(getString(R.string.speed_unit_shared_preference), radioGroup.findViewById(i).getTag().toString());
                editor.apply();
            }
        });
    }
}
