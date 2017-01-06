package com.swarmnyc.mvvmlib.sampleapp;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.Swipe;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isOneOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecyclerViewActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    @Test
    public void recyclerViewActivityTest() {
        // click Button "RecyclerView Demo"
        ViewInteraction appCompatButton = onView(
                allOf(withText("RecyclerView Demo"), isDisplayed()));
        OnDeviceScreenshotAction.perform(null);
        appCompatButton.perform(click());

        // click Button "more"
        ViewInteraction appCompatButton3 = onView(
                allOf(withText("more"), isDisplayed()));
        OnDeviceScreenshotAction.perform(null);
        appCompatButton3.perform(click());

        // put "Test" into EditText View.
        ViewInteraction appCompatEditText = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatEditText")), isDisplayed()));
        OnDeviceScreenshotAction.perform(null);
        appCompatEditText.perform(replaceText("Test"), closeSoftKeyboard());

        // Check Description Text View contains "Test"
        ViewInteraction viewDescription = onView(
                allOf(withId(R.id.description), isDisplayed()));
        viewDescription.check(matches(isDisplayed()));
        viewDescription.check(matches(withText(containsString("Test"))));

        // Swipe down to load more.
        ViewInteraction viewGroup = onView(
                allOf(withId(R.id.layout_swipe), isDisplayed()));
        viewGroup.check(matches(isDisplayed()));
        OnDeviceScreenshotAction.perform(null);
        viewGroup.perform(swipeDown());

        // Check RecyclerView "allEpisode" has at least one row.
        ViewInteraction imageView = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.allEpisodes),
                                0),
                        0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));
        OnDeviceScreenshotAction.perform(null);

        // put "The" into EditText View.
        appCompatEditText.perform(replaceText("The"), closeSoftKeyboard());

        // Check Description Text View contains "The"
        viewDescription.check(matches(withText(containsString("The"))));
        OnDeviceScreenshotAction.perform(null);


        pressBack();
        OnDeviceScreenshotAction.perform(null);

    }
}
