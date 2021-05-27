package com.example.geoevent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class teamdashboard  extends AppCompatActivity implements View.OnClickListener   {
    Button add,delete;
    public FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_teamdashbored);
        add=findViewById(R.id.addeventbtn);
        delete=findViewById(R.id.deleteeventbtn);
        mAuth=FirebaseAuth.getInstance();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               
                Intent adding =new Intent(teamdashboard.this,addevent.class);
                startActivity(adding);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent adding =new Intent(teamdashboard.this,deleteEvent.class);
                startActivity(adding);
            }
        });

        if(mAuth.getCurrentUser() !=null){
         String currentuser = mAuth.getCurrentUser().getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Users").child("Teams").child(currentuser);

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                if (snapshot.getValue() == null) {
                    mAuth.signOut();
                    startActivity(new Intent(teamdashboard.this,TeamLogin.class));
                    finish();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }}

    @Override
    public void onClick(View view) {

    }
}