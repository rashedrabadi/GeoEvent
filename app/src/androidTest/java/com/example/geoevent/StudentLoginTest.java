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

import static org.junit.Assert.assertNotNull;

public class StudentLoginTest {
    @Rule
    public ActivityTestRule<StudentLogin> lActivityTestRule=new ActivityTestRule<StudentLogin>(StudentLogin.class);
    private StudentLogin lActivity=null;
    Instrumentation.ActivityMonitor monitor= InstrumentationRegistry.getInstrumentation().addMonitor(studentDashboard.class.getName(),null,false);



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
        assertNotNull(lActivity.findViewById(R.id.SLogin));
        new Handler(Looper.getMainLooper()).post(new Runnable(){
        @Override
        public void run() {
            lActivity.editEmail.setText("rashedmrabadi@gmail.com");
            lActivity.editPass.setText("Rabadi.1234");
        }
    });


        Espresso.onView(ViewMatchers.withId(R.id.SLogin)).perform(ViewActions.click());

        Activity login= InstrumentationRegistry.getInstrumentation().waitForMonitor(monitor);
        assertNotNull(login);
        login.finish();
    }
}