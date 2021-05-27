package com.example.geoevent;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class teamdashboardTest {




    @Rule
    public ActivityTestRule<teamdashboard> cActivityTestRule=new ActivityTestRule<teamdashboard>(teamdashboard.class);
    private teamdashboard cActivity=null;
    private FirebaseAuth auth;
    Instrumentation.ActivityMonitor monitor= InstrumentationRegistry.getInstrumentation().addMonitor(addevent.class.getName(),null,false);
    Instrumentation.ActivityMonitor monitor1= InstrumentationRegistry.getInstrumentation().addMonitor(deleteEvent.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        cActivity=cActivityTestRule.getActivity();
        auth.getInstance().signInWithEmailAndPassword("rashedmrabadi@gmail.com","Rabadi.1234");
    }

    @After
    public void tearDown() throws Exception {
        cActivity=null;
    }




    @Test
    public void add(){


        assertNotNull(cActivity.findViewById(R.id.addeventbtn));
        Espresso.onView(ViewMatchers.withId(R.id.addeventbtn)).perform(ViewActions.click());

        Activity add= InstrumentationRegistry.getInstrumentation().waitForMonitor(monitor);
        assertNotNull(add);
        add.finish();

    }

    @Test
    public void delete(){
        assertNotNull(cActivity.findViewById(R.id.deleteeventbtn));
        Espresso.onView(ViewMatchers.withId(R.id.deleteeventbtn)).perform(ViewActions.click());

        Activity delete= InstrumentationRegistry.getInstrumentation().waitForMonitor(monitor1);
        assertNotNull(delete);
        delete.finish();

    }
}