package com.epitech.billy.omega_splicer.Fly;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.epitech.billy.omega_splicer.R;

/**
 * Created by Billy on 01/09/2015.
 *
 * Allow the user to drive the paired plane with his device
 */
public class FlyActivity extends Activity {

    private int mMotorSpeed;
    private SeekBar mMotorSeekBar;

//    private RelativeLayout mJoystickLayout;
//    private JoystickBuilder mJoystickBuilder;
//    private Joystick mJoystick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fly);

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

    private void setupMotorSeekBar(SeekBar seekBar, final TextView textView) {
        /* TODO: gérer les typeface autrement */

        seekBar.setProgress(mMotorSpeed);

        textView.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf"));
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
}
