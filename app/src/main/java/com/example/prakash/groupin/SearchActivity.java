package com.example.prakash.groupin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.prakash.groupin.m_DataObject.Spacecraft;
import com.example.prakash.groupin.m_MySQL.Downloader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {
    String usr,userType;
    String urlAddress="http://groupin.orgfree.com/Search.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        usr=getIntent().getExtras().getString("username");
        userType=getIntent().getExtras().getString("usertype");


       // usr=getIntent().getExtras().getString("username");
      //  userType=getIntent().getExtras().getString("usertype");
        //String cat=getIntent().getExtras().getString("category");
        Map<String, String> params = new HashMap<String, String>();
        params.put("title","E-Government");
        //params.put("username",usr);
        //params.put("category",cat);
        RecyclerView rv=(RecyclerView)findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        ArrayList<Spacecraft> spacecrafts=new ArrayList<>();
        spacecrafts.clear();
        Downloader d=new Downloader(SearchActivity.this,urlAddress,rv,params,spacecrafts);
        //System.out.println("JSON RECIEVED=>"+Downloader.JSONDATA);
        d.execute();












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
