package com.epitech.billy.omega_splicer.old.Fly;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.epitech.billy.omega_splicer.R;
import com.epitech.billy.omega_splicer.presentation.ui.activities.FlyActivity;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

/**
 * Created by bichon_b on 11/28/15.
 *
 */
public class FlyActivityTest extends ActivityInstrumentationTestCase2<FlyActivity> {
//
//    private FlyActivity mFlyActivity;
//    private TextView mTextView;
//    private SeekBar mSeekBar;

    public FlyActivityTest() {
        super(FlyActivity.class);
    }
//
//    @Override
//    protected void setUp() throws Exception {
//        super.setUp();
//        mFlyActivity = getActivity();
//        mTextView = (TextView) mFlyActivity.findViewById(R.id.fly_activity_motor_value);
//        mSeekBar = (SeekBar) mFlyActivity.findViewById(R.id.fly_activity_motor_seek_bar);
//    }
//
//    public void testPreconditions_FlyActivity() {
//        MatcherAssert.assertThat("FlyActivity is null", mFlyActivity, Matchers.notNullValue());
//        MatcherAssert.assertThat("FlyActivity.TextView is null", mTextView, Matchers.notNullValue());
//        MatcherAssert.assertThat("FlyActivity.SeekBar is null", mSeekBar, Matchers.notNullValue());
//    }
//
//    public void testMotorTextView_FlyActivity() {
//        final String expected = "0%";
//        final String actual = mTextView.getText().toString();
//        assertEquals(expected, actual);
//    }
//
//    public void testChangeSeekBarAndTextView_FlyActivity() {
//        if (mSeekBar != null && mTextView != null) {
//            Espresso.onView(ViewMatchers.withId(R.id.fly_activity_motor_seek_bar)).perform(setProgress(15));
//            Espresso.onView(ViewMatchers.withId(R.id.fly_activity_motor_value)).check(ViewAssertions.matches(ViewMatchers.withText("15%")));
//            Espresso.onView(ViewMatchers.withId(R.id.fly_activity_motor_seek_bar)).perform(setProgress(27));
//            Espresso.onView(ViewMatchers.withId(R.id.fly_activity_motor_value)).check(ViewAssertions.matches(ViewMatchers.withText("27%")));
//            Espresso.onView(ViewMatchers.withId(R.id.fly_activity_motor_seek_bar)).perform(setProgress(53));
//            Espresso.onView(ViewMatchers.withId(R.id.fly_activity_motor_value)).check(ViewAssertions.matches(ViewMatchers.withText("53%")));
//            Espresso.onView(ViewMatchers.withId(R.id.fly_activity_motor_seek_bar)).perform(setProgress(82));
//            Espresso.onView(ViewMatchers.withId(R.id.fly_activity_motor_value)).check(ViewAssertions.matches(ViewMatchers.withText("82%")));
//            Espresso.onView(ViewMatchers.withId(R.id.fly_activity_motor_seek_bar)).perform(setProgress(100));
//            Espresso.onView(ViewMatchers.withId(R.id.fly_activity_motor_value)).check(ViewAssertions.matches(ViewMatchers.withText("100%")));
//        }
//    }
//
//    public ViewAction setProgress(final int progress) {
//        return new ViewAction() {
//            @Override
//            public Matcher<View> getConstraints() {
//                return ViewMatchers.isAssignableFrom(SeekBar.class);
//            }
//
//            @Override
//            public String getDescription() {
//                return "Set a progress on a seekBar";
//            }
//
//            @Override
//            public void perform(UiController uiController, View view) {
//                ((SeekBar) view).setProgress(progress);
//            }
//        };
//    }
}
