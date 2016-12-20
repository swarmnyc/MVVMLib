package com.swarmnyc.mvvmlib.sampleapp;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withText("Activity Demo"), isDisplayed()));
        OnDeviceScreenshotAction.perform(appCompatButton.toString());
        appCompatButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatEditText")), not(withHint("No Data Passed")), isDisplayed()));
        OnDeviceScreenshotAction.perform(appCompatEditText.toString());
        appCompatEditText.perform(replaceText("test"), closeSoftKeyboard());


        ViewInteraction appCompatButton2 = onView(
                allOf(withText("Navigate To Second Layout by Adapter"), isDisplayed()));
        OnDeviceScreenshotAction.perform(appCompatButton2.toString());
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.button), withText("Return"), isDisplayed()));
        OnDeviceScreenshotAction.perform(appCompatButton3.toString());
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withText("Navigate To Third Layout by ViewModel"), isDisplayed()));
        OnDeviceScreenshotAction.perform(appCompatButton4.toString());
        appCompatButton4.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withText("Close"), isDisplayed()));
        OnDeviceScreenshotAction.perform(appCompatButton5.toString());
        appCompatButton5.perform(click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withText("Close by Adapter"), isDisplayed()));
        OnDeviceScreenshotAction.perform(appCompatButton6.toString());
        appCompatButton6.perform(click());

        OnDeviceScreenshotAction.perform("Final");

    }
}
