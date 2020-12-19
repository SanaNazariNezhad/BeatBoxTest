package org.maktab.beatbox.view.activity;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maktab.beatbox.R;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class BeatBoxActivityTest {

    @Rule
    public ActivityScenarioRule<BeatBoxActivity> mActivityRule =
            new ActivityScenarioRule<>(BeatBoxActivity.class);

    @Test
    public void swipe() {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_beat_box))
                .perform(ViewActions.swipeUp());
        Espresso.onView(ViewMatchers.withText("86_oa-h"))
                .check(ViewAssertions.matches(Matchers.anything()));
        Espresso.onView(ViewMatchers.withText("86_oa-h"))
                .perform(ViewActions.click());
    }
}