package com.example.geoevent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class studentDashboard extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    public static EventsAdapter RA;
    public static List<event> eventsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_student_dashbard);

        eventsList = new ArrayList<>();
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Users").child("Students").child(currentuser);

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                if (snapshot.getValue() == null) {
                    startActivity(new Intent(studentDashboard.this, teamdashboard.class));
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
        DatabaseReference databaseEvents=FirebaseDatabase.getInstance().getReference("Events");
    @Override
    protected void onStart() {
        super.onStart();
        databaseEvents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                eventsList.clear();
                for(DataSnapshot EventSnapshot:snapshot.getChildren()){
                        event Event=EventSnapshot.getValue(event.class);
                        eventsList.add(Event);

                }
                RA=new EventsAdapter(studentDashboard.this,eventsList);
                recyclerView = findViewById(R.id.results);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(studentDashboard.this));
               recyclerView.setAdapter(RA);
                RA.notifyDataSetChanged();
        }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        RA=new EventsAdapter(this,eventsList);
        recyclerView = findViewById(R.id.results);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(RA);

        RA.notifyDataSetChanged();

    }


    @Override
    public void onClick(View view) {

    }
}