package com.example.mylogin;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class WhereToActivity extends Activity {

    String[] places;
    String[] packages;
    ListView myList;
    public static String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where_to);

        myList = (ListView) findViewById(R.id.listview);

        Resources res = getResources();
        places = res.getStringArray(R.array.places);
        packages = res.getStringArray(R.array.description);

        //myList.setAdapter(new ArrayAdapter<String>(this,R.layout.my_layout,places));
        //Helper.getListViewSize(myList);

        Helper helper=new Helper(this,places,packages);
        myList.setAdapter(helper);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                value=places[position];
                Intent jsonpage = new Intent(getApplicationContext(),JsonActivity.class);
                startActivity(jsonpage);
            }
        });
    }
}