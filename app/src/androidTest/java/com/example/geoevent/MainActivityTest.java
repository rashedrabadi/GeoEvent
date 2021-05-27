package com.example.geoevent;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule=new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mActivity=null;

    Instrumentation.ActivityMonitor monitor= InstrumentationRegistry.getInstrumentation().addMonitor(TeamStudent.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }


    @Test
    public void proceed() {
        assertNotNull(mActivity.findViewById(R.id.proceed));
        Espresso.onView(ViewMatchers.withId(R.id.proceed)).perform(ViewActions.click());

        Activity Register= InstrumentationRegistry.getInstrumentation().waitForMonitor(monitor);
        assertNotNull(Register);
        Register.finish();
    }

}