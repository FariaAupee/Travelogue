package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class JsonActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static TextView data;
    public static ImageView image;

    private DrawerLayout drawerLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_json);
        setContentView(R.layout.nav_drawer_layout);
        Toolbar toolbar=findViewById(R.id.toolbarid);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigation=findViewById(R.id.navigation_view);
        navigation.setNavigationItemSelectedListener(this);

        data = findViewById(R.id.jsontextid);
        image = findViewById(R.id.imageid);
        TourDetails process = new TourDetails();
        process.execute();
    }

    public void onBackPressed()
    {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;
        if(menuItem.getItemId()==R.id.homeid)
        {
            intent=new Intent(getApplicationContext(),ProfileActivity.class);
            startActivity(intent);
        }
        else if(menuItem.getItemId()==R.id.destinationid)
        {
            intent=new Intent(getApplicationContext(),WhereToActivity.class);
            startActivity(intent);
        }
        else if(menuItem.getItemId()==R.id.signoutid)
        {
            ProgressBar progressBar=findViewById(R.id.signoutprogress);
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(),"Signing out...",Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
            finish();
            intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
        else if(menuItem.getItemId()==R.id.contactid)
        {
            intent=new Intent(getApplicationContext(),ContactUsActivity.class);
            startActivity(intent);
        }
        else if(menuItem.getItemId()==R.id.bookingid)
        {
            intent=new Intent(getApplicationContext(),BookActivity.class);
            startActivity(intent);
        }
        return false;
    }
}
