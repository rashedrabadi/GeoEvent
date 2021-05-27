package com.example.geoevent;


import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
public class TeamRegister extends AppCompatActivity implements View.OnClickListener  {
    private FirebaseAuth mAuth;
    public TextView registerbtn;
    public EditText editEmail,editPassword,editPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        getSupportActionBar().hide();

        setContentView(R.layout.activity_team_register);
        mAuth = FirebaseAuth.getInstance();
        registerbtn=(Button)findViewById(R.id.Tregisterbtn);
        registerbtn.setOnClickListener(this);
        editEmail=(EditText)findViewById(R.id.Temail);
        editPassword=(EditText)findViewById(R.id.Tpass1);
        editPassword2=(EditText)findViewById(R.id.Tpass2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Tregisterbtn:
                registerTeam();

                break;

        }

    }
    private void registerTeam() {
        final String email=editEmail.getText().toString().trim();
        final String pass=editPassword.getText().toString().trim();
        final String pass2=editPassword2.getText().toString().trim();

        if(email.isEmpty()){
            editEmail.setError("Email is required !");
            editEmail.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            editPassword.setError("Password is required !");
            editPassword.requestFocus();
            return;
        }
        if(pass2.isEmpty()){
            editPassword2.setError("Confirming Password is required !");
            editPassword2.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Please provide valid Email !");
            editEmail.requestFocus();
            return;
        }
        if(pass.length()<6){
            editPassword.setError("Min password length should be 6 characters !");
            editPassword.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user=new User(email);
                    FirebaseDatabase.getInstance().getReference("Users").child("Teams").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(TeamRegister.this,"Registered successfully",Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(TeamRegister.this,"Failed to Register",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }});
    }
    }
