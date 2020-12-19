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

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class BeatBoxActivityTest {

    @Rule
    public ActivityScenarioRule<BeatBoxActivity> mActivityRule =
            new ActivityScenarioRule<>(BeatBoxActivity.class);

    @Test
    public void showsFirstFileName() {
        Espresso.onView(ViewMatchers.withText("65_cjipie"))
                .check(ViewAssertions.matches(Matchers.anything()));
    }

    @Test
    public void clickTheFirstFile() {
        Espresso.onView(ViewMatchers.withText("65_cjipie"))
                .perform(ViewActions.click());
    }
}