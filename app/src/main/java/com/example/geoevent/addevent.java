package com.example.geoevent;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

public class addevent extends AppCompatActivity implements View.OnClickListener {
    public EditText editTime,editDate,editDescription,editSponsers,editName;

private Button submit;
public Spinner editPlace;
DatabaseReference EVENTS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        getSupportActionBar().hide();
        setContentView(R.layout.activity_addevent);


        editName=(EditText) findViewById(R.id.EventName);
        editTime=(EditText) findViewById(R.id.time);
        editDate=(EditText) findViewById(R.id.date);
        editDescription=(EditText) findViewById(R.id.description);
        editSponsers=(EditText) findViewById(R.id.sponsers);




List<String> JUSTPlaces =
                Arrays.asList("A1","A2","A3","A4",
                              "G1","G2","G3","G4",
                              "C1","C2","C3","c4","C5","C6",
                              "M1(Engineering)","M2(Engineering)","M3(Engineering)","M4(Engineering)","M5(Engineering)","M6(Engineering)", "M7(Engineering)","M8(Engineering)",
                              "E1","E2","E3","E4",
                              "N1(Engineering)","N2(Engineering)",
                              "CH1","CH2",
                              "SF(Halls)", "SG(Halls)","SB(Halls)","NF(Halls)","NG(Halls)","NB(Halls)",
                              "PH1","PH2","PH3","PH4",
                              "P1","P2","P3",
                              "D1","D2","D3","D4",
                              "N1(Medicine)","N2(Medicine)","N3(Medicine)","N4(Medicine)",
                              "M1(Medicine)","M2(Medicine)","M3(Medicine)","M4(Medicine)");
        editPlace =(Spinner) findViewById(R.id.place);
        ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,JUSTPlaces);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
editPlace.setAdapter(adapter);
        submit=(Button)findViewById(R.id.submit);
        submit.setOnClickListener(this);
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:
            { submition();
                break;
            }

        }
    }

    private void submition() {
        String name=editName.getText().toString().trim();
        String time=editTime.getText().toString().trim();
    String date=editDate.getText().toString().trim();
    String description=editDescription.getText().toString().trim();
    String sponsers=editSponsers.getText().toString().trim();
    String place=editPlace.getSelectedItem().toString();
    if(name.isEmpty()||time.isEmpty()||date.isEmpty()||description.isEmpty())
    if(name.isEmpty()){
        editName.setError("Name is required !");
        editName.requestFocus();
        return;
    }
    if(time.isEmpty()){
        editTime.setError("Time is required !");
        editTime.requestFocus();
        return;
    }
    if(date.isEmpty()){
        editDate.setError("Date is required !");
        editDate.requestFocus();
        return;
    }
    if(description.isEmpty()){
        editDescription.setError("Description is required !");
        editDescription.requestFocus();
        return;
    }

    else{

        EVENTS= FirebaseDatabase.getInstance().getReference("Events").child(name);
        String id= FirebaseAuth.getInstance().getCurrentUser().getUid();


        event Event = new event(id,name,time,date,description,sponsers,place);
        EVENTS.setValue(Event);
        Toast.makeText(addevent.this,"Event added successfully",Toast.LENGTH_LONG).show();
    }




    }
}