package com.example.prakash.groupin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationPresenter;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class Notify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        //Get object corresponding to BottomNavigationBarNamed navigationView
        BottomNavigationView bottombar=(BottomNavigationView)findViewById(R.id.navigationView);
        bottombar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_notification:
                        startActivity(new Intent(Notify.this,Notify.class));
                        break;
                    case R.id.nav_search:
                        startActivity(new Intent(Notify.this,SearchActivity.class));

                        break;
                    case R.id.nav_person:
                        startActivity(new Intent(Notify.this,ProfileActivity.class));
                        break;

                }
                return false;
            }
        });
    }

}
