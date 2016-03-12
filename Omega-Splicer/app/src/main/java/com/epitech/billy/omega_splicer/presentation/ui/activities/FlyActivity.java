package com.epitech.billy.omega_splicer.presentation.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.epitech.billy.omega_splicer.App;
import com.epitech.billy.omega_splicer.R;
import com.epitech.billy.omega_splicer.domain.executors.impl.ThreadExecutor;
import com.epitech.billy.omega_splicer.presentation.presenters.IFlyPresenter;
import com.epitech.billy.omega_splicer.presentation.presenters.impl.FlyPresenter;
import com.epitech.billy.omega_splicer.presentation.ui.custom_views.SettingDialog;
import com.epitech.billy.omega_splicer.threading.MainThread;

/**
 * Created by bichon_b on 3/4/16.
 * <p/>
 * This is the fly view.
 * <p/>
 */
public class FlyActivity extends AppCompatActivity implements IFlyPresenter.View {

    // UI Stuff
    private ImageButton mSettingButton;
    private SettingDialog mSettingDialog;
    private SeekBar mMotorSeekBar;
    private TextView mMotorSpeedText;
    private int mMotorSpeed;

    // Presenter
    private IFlyPresenter mFlyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
            setupMotorSeekBar();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFlyPresenter.resume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("motorSpeed", mMotorSpeed);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("recup " + savedInstanceState.getInt("motorSpeed"));
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

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMotorSpeed(int motorSpeed) {
        System.out.println("inside the show of view");
        mMotorSpeed = motorSpeed;
        if (mMotorSeekBar != null) {
            mMotorSeekBar.setProgress(motorSpeed);
        }
        if (mMotorSpeedText != null) {
            mMotorSpeedText.setText(String.format("%d%%", motorSpeed));
        }
    }
}
