package com.example.android.bookexchange1;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.Espresso.onView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by l on 11/28/17.
 */

@RunWith(AndroidJUnit4.class)

public class MainActivityInstrumentedTest {

    /*@Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule = new
            ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void clickSignUp_opensSignUpUi() throws Exception{
        onView(withId(R.id.sign_up)).perform(click());
    }

    @Test
    public void clickLogIn_openLogInUi() throws Exception{
        onView(withId(R.id.log_in)).perform(click());
    }*/
}
