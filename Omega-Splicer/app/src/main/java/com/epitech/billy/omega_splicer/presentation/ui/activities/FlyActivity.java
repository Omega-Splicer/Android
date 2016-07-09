package com.epitech.billy.omega_splicer.presentation.ui.activities;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.epitech.billy.omega_splicer.App;
import com.epitech.billy.omega_splicer.R;
import com.epitech.billy.omega_splicer.domain.executors.impl.ThreadExecutor;
import com.epitech.billy.omega_splicer.presentation.presenters.IFlyPresenter;
import com.epitech.billy.omega_splicer.presentation.presenters.impl.FlyPresenter;
import com.epitech.billy.omega_splicer.presentation.ui.JoyStickClass;
import com.epitech.billy.omega_splicer.presentation.ui.SensorInterpreter;
import com.epitech.billy.omega_splicer.presentation.ui.custom_views.SettingDialog;
import com.epitech.billy.omega_splicer.threading.MainThread;

import java.util.Locale;

/**
 * Created by bichon_b on 3/4/16.
 * <p/>
 * This is the fly view.
 * <p/>
 */
public class FlyActivity extends AppCompatActivity implements IFlyPresenter.View, SensorEventListener {

    // UI Stuff
    private ImageButton mSettingButton;
    private SettingDialog mSettingDialog;
    private SeekBar mMotorSeekBar;
    private TextView mMotorSpeedText;
    private int mMotorSpeed;
    private RelativeLayout mJoystickLayout;
    private JoyStickClass mJoyStickClass;

    // Presenter
    private IFlyPresenter mFlyPresenter;

    // Sensor stuff
    private SensorInterpreter mSensorInterpreter;
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation((App.getInstance().getGeneralOrientation().equals(getString(R.string.landscape_orientation_shared_preference))) ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fly);

        mFlyPresenter = new FlyPresenter(ThreadExecutor.getInstance(), MainThread.getInstance(), this);

        mSettingDialog = new SettingDialog(this);

        mSettingButton = (ImageButton) findViewById(R.id.fly_activity_setting_button);
        if (mSettingButton != null) {
            setupSettingButton();
        }

        mMotorSeekBar = (SeekBar) findViewById(R.id.fly_activity_motor_seek_bar);
        mMotorSpeedText = (TextView) findViewById(R.id.fly_activity_motor_value);
        if (mMotorSeekBar != null && mMotorSpeedText != null) {
            mSensorInterpreter = new SensorInterpreter();
            setupMotorSeekBar();
        }

        mJoystickLayout = (RelativeLayout) findViewById(R.id.fly_activity_layout_joystick_control);
        if (mJoystickLayout != null) {
            setupJoystick();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFlyPresenter.resume();
        if (mSensorInterpreter != null && mSensorManager == null) {
            System.out.println("the sensor interpreter and the manager are not null");
            mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            if (mSensorManager != null) {
                System.out.println("manager is not null");
                mSensorManager.registerListener(this,
                        mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),
                        SensorManager.SENSOR_DELAY_FASTEST);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mSensorInterpreter != null && mSensorManager != null) {
            mSensorManager.unregisterListener(this);
            mSensorManager = null;
            mSensorInterpreter.reset();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("motorSpeed", mMotorSpeed);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mFlyPresenter.changeMotorSpeed(savedInstanceState.getInt("motorSpeed", 0));
    }

    private void setupSettingButton() {
        mSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSettingDialog != null) {
                    mSettingDialog.show();
                }
            }
        });
    }

    private void setupMotorSeekBar() {
        mMotorSpeedText.setTypeface(App.ROBOTO_LIGHT);

        mMotorSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mFlyPresenter.changeMotorSpeed(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setupJoystick() {
        mJoyStickClass = new JoyStickClass(this, mJoystickLayout, R.drawable.joystick);
        mJoyStickClass.setStickSize(150, 150);
        mJoyStickClass.setLayoutSize(500, 500);
        mJoyStickClass.setLayoutAlpha(150);
        mJoyStickClass.setStickAlpha(100);
        mJoyStickClass.setOffset(90);
        mJoyStickClass.setMinimumDistance(50);

        mJoystickLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mJoyStickClass.drawStick(motionEvent);
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN
                        || motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    System.out.println("X : " + String.valueOf(mJoyStickClass.getX()));
                    System.out.println("Y : " + String.valueOf(mJoyStickClass.getY()));
                    System.out.println("Angle : " + String.valueOf(mJoyStickClass.getAngle()));
                    System.out.println("Distance : " + String.valueOf(mJoyStickClass.getDistance()));

                    int direction = mJoyStickClass.get8Direction();
                    if(direction == JoyStickClass.STICK_UP) {
                        System.out.println("Direction : Up");
                    } else if(direction == JoyStickClass.STICK_UPRIGHT) {
                        System.out.println("Direction : Up Right");
                    } else if(direction == JoyStickClass.STICK_RIGHT) {
                        System.out.println("Direction : Right");
                    } else if(direction == JoyStickClass.STICK_DOWNRIGHT) {
                        System.out.println("Direction : Down Right");
                    } else if(direction == JoyStickClass.STICK_DOWN) {
                        System.out.println("Direction : Down");
                    } else if(direction == JoyStickClass.STICK_DOWNLEFT) {
                        System.out.println("Direction : Down Left");
                    } else if(direction == JoyStickClass.STICK_LEFT) {
                        System.out.println("Direction : Left");
                    } else if(direction == JoyStickClass.STICK_UPLEFT) {
                        System.out.println("Direction : Up Left");
                    } else if(direction == JoyStickClass.STICK_NONE) {
                        System.out.println("Direction : Center");
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMotorSpeed(int motorSpeed) {
        mMotorSpeed = motorSpeed;
        if (mMotorSeekBar != null) {
            mMotorSeekBar.setProgress(motorSpeed);
        }
        if (mMotorSpeedText != null) {
            mMotorSpeedText.setText(String.format(Locale.FRANCE, "%d%%", motorSpeed));
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (mSensorInterpreter == null) {
            System.out.println("the interpreter is null");
            return ;
        }
        final float[] vectors = mSensorInterpreter.interpretSensorEvent(this, sensorEvent);

        if (vectors == null) {
            System.out.println("the data are uninterpretable");
            return ;
        }

        System.out.format("The vector are (x, y, z) => (%f, %f, %f)%n", vectors[0], vectors[1], vectors[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
