package com.example.geoevent;

import android.os.Handler;
import android.os.Looper;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class RegisterTest {
    @Rule
    public ActivityTestRule<Register> rActivityTestRule=new ActivityTestRule<Register>(Register.class);
    private Register rActivity=null;
    //Instrumentation.ActivityMonitor monitor= InstrumentationRegistry.getInstrumentation().addMonitor(search.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        rActivity=rActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        rActivity=null;
    }

    @Test
    public void register(){ //this also will make sure that the database is connected
        assertNotNull(rActivity.findViewById(R.id.registerbtnn));
        new Handler(Looper.getMainLooper()).post(new Runnable(){
            @Override
            public void run() {
                rActivity.editEmail.setText("rashedrabadi@just.com");
                rActivity.editPassword.setText("Pass@123");
                rActivity.editPassword2.setText("Pass@123");
            }
        });

        onView(ViewMatchers.withId(R.id.registerbtnn)).perform(ViewActions.click());


        onView(withText(R.string.toast)).inRoot(new ToastMatcher())
                .check(matches(withText("Registered successfully")));

    }
}