package com.example.geoevent;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Handler;
import android.os.Looper;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeamLoginTest {
    @Rule
    public ActivityTestRule<TeamLogin> lActivityTestRule=new ActivityTestRule<TeamLogin>(TeamLogin.class);
    private TeamLogin lActivity=null;
    Instrumentation.ActivityMonitor monitor= InstrumentationRegistry.getInstrumentation().addMonitor(teamdashboard.class.getName(),null,false);



    @Before
    public void setUp() throws Exception {
        lActivity=lActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        lActivity=null;
    }

    @Test
    public void login()
    {
        assertNotNull(lActivity.findViewById(R.id.Login));

        new Handler(Looper.getMainLooper()).post(new Runnable(){
            @Override
            public void run() {
                lActivity.editEmail.setText("computergy@just.com");
                lActivity.editPass.setText("Pass@123");
            }
        });


        Espresso.onView(ViewMatchers.withId(R.id.Login)).perform(ViewActions.click());

        Activity login= InstrumentationRegistry.getInstrumentation().waitForMonitor(monitor);
        assertNotNull(login);
        login.finish();
    }
}