package com.epitech.billy.omega_splicer.Fly;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.epitech.billy.omega_splicer.App;
import com.epitech.billy.omega_splicer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Billy on 01/09/2015.
 *
 * Allow the user to drive the paired plane with his device
 */
public class FlyActivity extends Activity {

    // Ui Stuff
    private int mMotorSpeed;
    private SeekBar mMotorSeekBar;

    // miscellaneous
    private SharedPreferences mSharedPreferences;

//    private RelativeLayout mJoystickLayout;
//    private JoystickBuilder mJoystickBuilder;
//    private Joystick mJoystick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fly);

        mSharedPreferences = getSharedPreferences(getString(R.string.shared_preference_file_key), MODE_PRIVATE);

        ImageButton settingButton = (ImageButton) findViewById(R.id.fly_activity_setting_button);
        if (settingButton != null) {
            setupSettingButton(settingButton);
        }

        mMotorSeekBar = (SeekBar) findViewById(R.id.fly_activity_seek_bar);
        TextView motorValue = (TextView) findViewById(R.id.fly_activity_motor_value);
        if (mMotorSeekBar != null && motorValue != null) {
            setupMotorSeekBar(mMotorSeekBar, motorValue);
        }

//        mJoystickLayout = (RelativeLayout) findViewById(R.id.fly_activity_joystick);
//
//        mJoystickBuilder = new JoystickBuilder(this, mJoystickLayout, R.drawable.logout)
//            .setJoystickSize(150, 150)
//            .setLayoutSize(500, 500)
//            .setLayoutAlpha(150)
//            .setJoystickAlpha(100)
//            .setOffset(90)
//            .setMinDistance(50);
//
//        mJoystick = mJoystickBuilder.build();
//
//        mJoystickLayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                mJoystick.drawJoystick(event);
//                return true;
//            }
//        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("motorValue", mMotorSpeed);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.format("------> on RestoreSavedInstance() -> receive %d%n", savedInstanceState.getInt("motorValue", 0));
        mMotorSpeed = savedInstanceState.getInt("motorValue", 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMotorSeekBar != null) {
            mMotorSeekBar.setProgress(mMotorSpeed);
        }
    }

    private void setupMotorSeekBar(SeekBar seekBar, final TextView textView) {
        seekBar.setProgress(mMotorSpeed);

        textView.setTypeface(App.ROBOTO_LIGHT);
        textView.setText(String.format("%d%%", seekBar.getProgress()));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mMotorSpeed = seekBar.getProgress();
                textView.setText(String.format("%d%%", mMotorSpeed));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setupSettingButton(ImageButton settingButton) {
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                LayoutInflater inflater = (LayoutInflater) v.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.popup_setting_layout, null);
                final Dialog dialog = builder.setView(view).create();
                
                ((TextView) view.findViewById(R.id.setting_popup_title)).setTypeface(App.ROBOTO_BOLD);
                ((TextView) view.findViewById(R.id.setting_popup_control)).setTypeface(App.ROBOTO_REGULAR);
                ((TextView) view.findViewById(R.id.setting_popup_speed_unit)).setTypeface(App.ROBOTO_REGULAR);

                final Spinner controlSpinner = (Spinner) view.findViewById(R.id.setting_popup_control_spinner);
                final List<String> controlList = new ArrayList<String>();
                controlList.add(getString(R.string.control_buttons));
                controlList.add(getString(R.string.control_gyroscope));
                controlSpinner.setAdapter(new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_dropdown_item, controlList));

                if (mSharedPreferences.contains(getString(R.string.control_shared_preference))) {
                    if (mSharedPreferences.getString(getString(R.string.control_shared_preference), getString(R.string.default_control_value_shared_preference)).equals(getString(R.string.gyroscope_control_shared_preference))) {
                        controlSpinner.setSelection(1);
                    }
                    else {
                        controlSpinner.setSelection(0);
                    }
                }

                final Spinner speedUnitSpinner = (Spinner) view.findViewById(R.id.setting_popup_speed_unit_spinner);
                List<String> speedUnitList = new ArrayList<String>();
                speedUnitList.add(getString(R.string.speed_unit_kmph));
                speedUnitList.add(getString(R.string.speed_unit_mps));
                speedUnitList.add(getString(R.string.speed_unit_mph));
                speedUnitSpinner.setAdapter(new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_dropdown_item, speedUnitList));

                if (mSharedPreferences.contains(getString(R.string.speed_unit_shared_preference))) {
                    if (mSharedPreferences.getString(getString(R.string.speed_unit_shared_preference), getString(R.string.default_speed_unit_value_shared_preference)).equals(getString(R.string.km_speed_unit_shared_preference))) {
                        speedUnitSpinner.setSelection(0);
                    }
                    else if (mSharedPreferences.getString(getString(R.string.speed_unit_shared_preference), getString(R.string.default_speed_unit_value_shared_preference)).equals(getString(R.string.ms_speed_unit_shared_preference))) {
                        speedUnitSpinner.setSelection(1);
                    }
                    else if (mSharedPreferences.getString(getString(R.string.speed_unit_shared_preference), getString(R.string.default_speed_unit_value_shared_preference)).equals(getString(R.string.mph_speed_unit_shared_preference))) {
                        speedUnitSpinner.setSelection(2);
                    }
                }

                view.findViewById(R.id.setting_popup_cancel_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                view.findViewById(R.id.setting_popup_ok_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final SharedPreferences.Editor editor = mSharedPreferences.edit();
                        switch (controlSpinner.getSelectedItemPosition()) {
                            case 0:
                                editor.putString(getString(R.string.control_shared_preference), getString(R.string.button_control_shared_preference));
                                break;
                            case 1:
                                editor.putString(getString(R.string.control_shared_preference), getString(R.string.gyroscope_control_shared_preference));
                                break;
                            default:
                                editor.putString(getString(R.string.control_shared_preference), getString(R.string.default_control_value_shared_preference));
                                break;
                        }

                        switch (speedUnitSpinner.getSelectedItemPosition()) {
                            case 0:
                                editor.putString(getString(R.string.speed_unit_shared_preference), getString(R.string.km_speed_unit_shared_preference));
                                break;
                            case 1:
                                editor.putString(getString(R.string.speed_unit_shared_preference), getString(R.string.ms_speed_unit_shared_preference));
                                break;
                            case 2:
                                editor.putString(getString(R.string.speed_unit_shared_preference), getString(R.string.mph_speed_unit_shared_preference));
                                break;
                            default:
                                editor.putString(getString(R.string.speed_unit_shared_preference), getString(R.string.default_speed_unit_value_shared_preference));
                                break;
                        }

                        editor.apply();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }
}
