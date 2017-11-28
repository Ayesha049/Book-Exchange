package com.example.android.bookexchange1;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.intercepting.SingleActivityFactory;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by l on 11/28/17.
 */

public class SignUpInstrumentedTest {
    /*@Rule
    public ActivityTestRule<SignUp> mSignUpTestRule = new
            ActivityTestRule<SignUp>(SignUp.class);

    @Test
    public void clickSignUp_opensConfirmationUi() throws Exception{
        onView(withId(R.id.signn_up)).perform(click());
    }*/
}
