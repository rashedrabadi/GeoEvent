package com.example.geoevent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TeamLogin extends AppCompatActivity implements View.OnClickListener {

    public EditText editEmail,editPass;
    private Button signIn;
    private Button reset;
    private FirebaseAuth mAuth;
    private String getType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        reset=(Button)findViewById(R.id.reset);
        reset.setOnClickListener(this);
        signIn=(Button)findViewById(R.id.Login);
        signIn.setOnClickListener(this);
        editEmail=(EditText)findViewById(R.id.Email);
        editPass=(EditText)findViewById(R.id.password);
        mAuth=FirebaseAuth.getInstance();
        getType=getIntent().getStringExtra("type");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Login:
                {
                userLogin();
                break;
                }
            case R.id.reset:
            {
                startActivity(new Intent(TeamLogin.this,reset.class));
                break;
            }


        }
    }

    private void userLogin() {
        final String email=editEmail.getText().toString().trim();
        String pass=editPass.getText().toString().trim();
        if(email.isEmpty()){
            editEmail.setError("Email is required !");
            editEmail.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            editPass.setError("Password is required !");
            editPass.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Please provide valid Email !");
            editEmail.requestFocus();
            return;
        }
        if(pass.length()<6){
            editPass.setError("Min password length should be 6 characters !");
            editPass.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(email.equals("Admin@JUST.com")){
                    if(task.isSuccessful()){
                        startActivity(new Intent(TeamLogin.this,TeamRegister.class));
                    }else {
                        Toast.makeText(TeamLogin.this,"Login Failed !",Toast.LENGTH_LONG).show();
                    }
                }
                else {


                    if(task.isSuccessful()){


                            startActivity(new Intent(TeamLogin.this,teamdashboard.class));


                    }else {
                        Toast.makeText(TeamLogin.this,"Login Failed !",Toast.LENGTH_LONG).show();
                    }
                }

            }
 });


    }
}