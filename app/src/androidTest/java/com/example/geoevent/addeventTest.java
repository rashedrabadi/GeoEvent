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

public class addeventTest {
    @Rule
    public ActivityTestRule<addevent> rActivityTestRule=new ActivityTestRule<addevent>(addevent.class);
    private addevent rActivity=null;
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
        assertNotNull(rActivity.findViewById(R.id.submit));
        new Handler(Looper.getMainLooper()).post(new Runnable(){
            @Override
            public void run() {
                rActivity.editTime.setText("14:30");
                rActivity.editDate.setText("15/7/2021");
                rActivity.editDescription.setText("conference with an IT company ");
                rActivity.editSponsers.setText("shelter");
                rActivity.editName.setText("M3kom");
                rActivity.editPlace.setSelection(2);

            }
        });

        onView(ViewMatchers.withId(R.id.submit)).perform(ViewActions.click());


        onView(withText(R.string.toast2)).inRoot(new ToastMatcher())
                .check(matches(withText("Event added successfully")));

    }

}