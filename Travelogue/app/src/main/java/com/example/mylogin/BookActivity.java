package com.example.mylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mylogin.BookingClass;
import com.example.mylogin.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.zip.Inflater;

public class BookActivity extends AppCompatActivity {

    EditText placeid,nameid,emailid,dateid,peopleid;
    Button reqbutton,resbutton;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        placeid=findViewById(R.id.reqplaceid);
        nameid=findViewById(R.id.reqnameid);
        emailid=findViewById(R.id.reqemailid);
        dateid=findViewById(R.id.reqdateid);
        peopleid=findViewById(R.id.reqpeopleid);
        reqbutton=findViewById(R.id.reqbuttonid);
        resbutton=findViewById(R.id.resbuttonid);

        databaseReference= FirebaseDatabase.getInstance().getReference("details");

        reqbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedata();
            }

            private void savedata() {

                String place=placeid.getText().toString().trim();
                String name=nameid.getText().toString().trim();
                String mail=emailid.getText().toString().trim();
                String date=dateid.getText().toString().trim();
                String people=peopleid.getText().toString().trim();

                String key=databaseReference.push().getKey();
                BookingClass booking=new BookingClass(place,name,mail,date,people);
                databaseReference.child(key).setValue(booking);

                Toast.makeText(getApplicationContext(),"Request sent",Toast.LENGTH_SHORT).show();
            }
        });

        resbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),RequestActivity.class);
                startActivity(intent);
            }
        });
    }
}
