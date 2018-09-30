package com.example.prakash.groupin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class SearchActivity extends AppCompatActivity {
    String usr,userType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        usr=getIntent().getExtras().getString("username");
        userType=getIntent().getExtras().getString("usertype");
        BottomNavigationView bottombar=(BottomNavigationView)findViewById(R.id.navigationView);
        if(userType.compareTo("teacher")==0)
            bottombar.inflateMenu(R.menu.bottom_navigation_teacher);
        else
            bottombar.inflateMenu(R.menu.bottom_navigation);

        bottombar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i;
                switch (item.getItemId())
                {
                    case R.id.nav_notification:
                        i=new Intent(SearchActivity.this,Category.class);
                        i.putExtra("username",usr);
                        i.putExtra("usertype",userType);
                        startActivity(i);
                        break;
                    case R.id.nav_search:
                       // startActivity(new Intent(SearchActivity.this,SearchActivity.class));

                        break;
                    case R.id.nav_person:
                        i=new Intent(SearchActivity.this,ProfileActivity.class);
                        i.putExtra("username",usr);
                        i.putExtra("usertype",userType);
                        startActivity(i);
                        break;
                    case R.id.nav_upload:
                        if(userType.compareTo("teacher")==0){
                        i=new Intent(SearchActivity.this,UploadActivity.class);
                        i.putExtra("username",usr);
                        i.putExtra("usertype",userType);
                        startActivity(i);
                        }
                        break;
                }
                return false;
            }
        });
    }
}
