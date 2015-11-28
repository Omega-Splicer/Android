package com.epitech.billy.omega_splicer.Fly;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.SeekBar;
import android.widget.TextView;

import com.epitech.billy.omega_splicer.R;

/**
 * Created by bichon_b on 11/28/15.
 *
 */
public class FlyActivityTest extends ActivityInstrumentationTestCase2<FlyActivity> {

    private FlyActivity mFlyActivity;
    private TextView mTextView;
    private SeekBar mSeekBar;

    public FlyActivityTest() {
        super(FlyActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mFlyActivity = getActivity();
        mTextView = (TextView) mFlyActivity.findViewById(R.id.fly_activity_motor_value);
        mSeekBar = (SeekBar) mFlyActivity.findViewById(R.id.fly_activity_seek_bar);
    }

    public void testPreconditions() {
        assertNotNull("FlyActivity is null", mFlyActivity);
        assertNotNull("FlyActivity.TextView is null", mTextView);
        assertNotNull("FlyActivity.SeekBar is null", mSeekBar);
    }

    public void testFlyActivity_MotorTextView() {
        final String expected = "0%";
        final String actual = mTextView.getText().toString();
        assertEquals(expected, actual);
    }
}
