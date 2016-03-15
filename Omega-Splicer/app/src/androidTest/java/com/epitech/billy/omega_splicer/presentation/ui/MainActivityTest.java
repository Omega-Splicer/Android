package com.epitech.billy.omega_splicer.presentation.ui;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.epitech.billy.omega_splicer.R;
import com.epitech.billy.omega_splicer.presentation.ui.activities.MainActivity;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by bichon_b on 3/11/16.
 * <p/>
 * This class will test the MainActivity using espresso.
 * <p/>
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void checkFlyButton() {
        onView(withId(R.id.main_activity_fly_button)).check(matches(notNullValue()));
        onView(withId(R.id.main_activity_fly_button)).check(matches(withText(R.string.fly)));
        onView(withId(R.id.main_activity_fly_button)).perform(click());
        onView(withId(R.id.fly_activity_setting_button)).check(matches(isDisplayed()));
    }

    @Test
    public void checkPairButton() {
        onView(withId(R.id.main_activity_pair_button)).check(matches(notNullValue()));
        onView(withId(R.id.main_activity_pair_button)).check(matches(withText(R.string.pair)));
        onView(withId(R.id.main_activity_pair_button)).perform(click());
        onView(withId(R.id.pair_activity_title)).check(matches(isDisplayed()));
    }

    @Test
    public void checkSettingButton() {
        onView(withId(R.id.main_activity_setting_button)).check(matches(notNullValue()));
        onView(withId(R.id.main_activity_setting_button)).check(matches(withText(R.string.setting)));
        onView(withId(R.id.main_activity_setting_button)).perform(click());
        onView(withId(R.id.setting_activity_title)).check(matches(isDisplayed()));
    }

}
