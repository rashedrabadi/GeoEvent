package com.example.geoevent;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class StudentLoginRegister extends AppCompatActivity {
 Button login , register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_student_login_register);

        login=findViewById(R.id.loginbtn);
        register=findViewById(R.id.registerbtnn);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login =new Intent(StudentLoginRegister.this, StudentLogin.class);
                startActivity(login);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg =new Intent(StudentLoginRegister.this,Register.class);
                startActivity(reg);
            }
        });

    }
}