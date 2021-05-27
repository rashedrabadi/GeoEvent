package com.example.geoevent;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class TeamStudent extends AppCompatActivity {
    Button team,student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        getSupportActionBar().hide();

        setContentView(R.layout.activity_team_student);

        team=findViewById(R.id.teambtn);
        student=findViewById(R.id.studentbtn);

        team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent team =new Intent(TeamStudent.this, TeamLogin.class);
                startActivity(team);
            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent student =new Intent(TeamStudent.this,StudentLoginRegister.class);
                startActivity(student);
            }
        });

    }
}