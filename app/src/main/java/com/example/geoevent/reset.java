package com.example.geoevent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class reset extends AppCompatActivity {
private EditText emailEditText;
private Button resetPass;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        getSupportActionBar().hide();

        setContentView(R.layout.activity_reset);
        emailEditText=(EditText)findViewById(R.id.Email);
        resetPass=(Button)findViewById(R.id.reset);
        auth=FirebaseAuth.getInstance();
        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String email=emailEditText.getText().toString().trim();
        if(email.isEmpty()){
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Please provide valid email !");
            emailEditText.requestFocus();
            return;
        }
       auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
           @Override
           public void onComplete(@NonNull Task<Void> task) {
               if(task.isSuccessful()){
                   Toast.makeText(reset.this,"Check your Email to reset your password !",Toast.LENGTH_LONG).show();
               }
               else {
                   Toast.makeText(reset.this,"Try again Something wrong happened or not connected to internet !",Toast.LENGTH_LONG).show();
               }
           }
       });
    }
}