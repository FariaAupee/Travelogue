package com.example.mylogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    Button btnletsgo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnletsgo=findViewById(R.id.btnletsgo);

        btnletsgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent whereto = new Intent(getApplicationContext(),WhereToActivity.class);
                startActivity(whereto);
            }
        });
    }
}
