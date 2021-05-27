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
import com.google.firebase.auth.FirebaseUser;

public class StudentLogin extends AppCompatActivity implements View.OnClickListener {




 public EditText editEmail,editPass;
 public Button signIn;
    private Button reset;
private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        getSupportActionBar().hide();

        setContentView(R.layout.activity_student_login);


signIn=(Button)findViewById(R.id.SLogin);
signIn.setOnClickListener(this);
reset=(Button)findViewById(R.id.Sreset);
reset.setOnClickListener(this);
editEmail=(EditText)findViewById(R.id.SEmail);
editPass=(EditText)findViewById(R.id.Spassword);
mAuth=FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View view) {
switch (view.getId()){
    case R.id.SLogin:
    { userLogin();
        break;
    }
    case R.id.Sreset:
    {
        startActivity(new Intent(StudentLogin.this,reset.class));
        break;
    }
}
    }

    private void userLogin() {
        String email=editEmail.getText().toString().trim();
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
if(task.isSuccessful()){
    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
    if(user.isEmailVerified()){
        startActivity(new Intent(StudentLogin.this,studentDashboard.class));
    }
    else {
        user.sendEmailVerification();
        Toast.makeText(StudentLogin.this,"check your Email to verify your account !",Toast.LENGTH_LONG).show();
    }

}else {
    Toast.makeText(StudentLogin.this,"Login Failed !",Toast.LENGTH_LONG).show();
}
            }
        });
    }
}