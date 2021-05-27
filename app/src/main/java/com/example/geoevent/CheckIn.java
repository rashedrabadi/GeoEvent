package com.example.geoevent;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class CheckIn extends AppCompatActivity {
    TextView description;
    String name;
    String info;
    private Button go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        name=getIntent().getStringExtra("name");
        description=findViewById(R.id.textView);
        go=findViewById(R.id.going);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("Events").child(name).child("GoingStudents").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(CheckIn.this,"you will be added to the list of people coming",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(CheckIn.this,"Failed ! please try again later",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        info="Description: "+"\n"  + getIntent().getStringExtra("description")+"\n" +"\n" ;
        info=info+"\n" +"place: " +getIntent().getStringExtra("place")+"\n" ;
        info=info+"\n" +"date: " +getIntent().getStringExtra("date")+"\n" ;
        info=info+"\n" +"time: " +getIntent().getStringExtra("time")+"\n" ;
        info=info+"\n" +"sponsers: "+"\n"  +getIntent().getStringExtra("sponsers");
        description.setText(info);

    }
}