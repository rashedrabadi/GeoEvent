
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

public class deleteEvent extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    public static teamAdapter RA;
    public static List<event> eventsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_delete_event);

        eventsList = new ArrayList<>();

    }
    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference databaseEvents=FirebaseDatabase.getInstance().getReference("Events");
    @Override
    protected void onStart() {
        super.onStart();
        databaseEvents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                eventsList.clear();
                for(DataSnapshot EventSnapshot:snapshot.getChildren()){
                    if(EventSnapshot.child("id").getValue().equals(currentuser))
                    { event Event=EventSnapshot.getValue(event.class);
                    eventsList.add(Event);}

                }
                RA=new teamAdapter(deleteEvent.this,eventsList);
                recyclerView = findViewById(R.id.Teamresults);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(deleteEvent.this));
                recyclerView.setAdapter(RA);
                RA.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        RA=new teamAdapter(this,eventsList);
        recyclerView = findViewById(R.id.Teamresults);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(RA);

        RA.notifyDataSetChanged();

    }



    @Override
    public void onClick(View view) {

    }
}