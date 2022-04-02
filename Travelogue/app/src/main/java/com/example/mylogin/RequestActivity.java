package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RequestActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    ListView listView;
    private List<BookingClass> list;
    private Request_Adapter request_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        databaseReference= FirebaseDatabase.getInstance().getReference("details");
        listView=findViewById(R.id.showlistviewid);

        list=new ArrayList<>();
        request_adapter = new Request_Adapter(RequestActivity.this,list);
    }

    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    BookingClass bookingClass;
                    bookingClass = dataSnapshot1.getValue(BookingClass.class);
                    list.add(bookingClass);
                }

                listView.setAdapter(request_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }
}
