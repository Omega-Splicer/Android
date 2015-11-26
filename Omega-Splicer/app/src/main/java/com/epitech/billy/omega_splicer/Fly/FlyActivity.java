package com.epitech.billy.omega_splicer.Fly;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.epitech.billy.omega_splicer.R;
import com.epitech.billy.omega_splicer.Utils.Joystick.Joystick;
import com.epitech.billy.omega_splicer.Utils.Joystick.JoystickBuilder;

/**
 * Created by Billy on 01/09/2015.
 *
 * Allow the user to drive the paired plane with his device
 */
public class FlyActivity extends Activity {

//    private RelativeLayout mJoystickLayout;
//    private JoystickBuilder mJoystickBuilder;
//    private Joystick mJoystick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fly);

        SeekBar motorSeekBar = (SeekBar) findViewById(R.id.fly_activity_seek_bar);
        TextView motorValue = (TextView) findViewById(R.id.fly_activity_motor_value);
        if (motorSeekBar != null && motorValue != null) {
            setupMotorSeekBar(motorSeekBar, motorValue);
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
        textView.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf"));
        textView.setText(String.format("%d%%", seekBar.getProgress()));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView.setText(String.format("%d%%", seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
