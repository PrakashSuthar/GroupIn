package com.example.prakash.groupin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.internal.BottomNavigationPresenter;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.graphics.Bitmap;
import java.net.*;
import java.io.*;
public class Notify extends AppCompatActivity {
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        final ConstraintLayout CL=(ConstraintLayout)findViewById(R.id.container);
        //Initialize  a new ImageView widget
        ImageView Iv1=new ImageView(getApplicationContext());
        Iv1.setId(1);
        ConstraintSet set = new ConstraintSet();
        CL.addView(Iv1,0);
       // set.clone(CL);
        Iv1.requestLayout();
        //Iv1.getLayoutParams().height=400;
        //Iv1.getLayoutParams().width=400;
       //set.connect(Iv1.getId(), ConstraintSet.TOP, CL.getId(), ConstraintSet.TOP, 60);
        //set.applyTo(CL);
        //Set an image for ImageView
        try {
            URL newurl = new URL("https://talksport.com/wp-content/uploads/sites/5/2018/08/GettyImages-1020217934.jpg?strip=all&w=960&quality=100");
            Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
            ConstraintLayout.LayoutParams ImP=(ConstraintLayout.LayoutParams)Iv1.getLayoutParams();
            ImP.height=mIcon_val.getHeight();
            ImP.width=mIcon_val.getWidth();
            Iv1.setImageBitmap(mIcon_val);

            Log.i("msg","Displayed image1");
            ImageView Iv=new ImageView(getApplicationContext());
            Iv.setId(2);
            CL.addView(Iv,1);
            newurl = new URL("https://talksport.com/wp-content/uploads/sites/5/2018/08/GettyImages-1020217934.jpg?strip=all&w=960&quality=100");

            mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
            ConstraintLayout.LayoutParams ImP2=(ConstraintLayout.LayoutParams)Iv.getLayoutParams();
            ImP2.height=mIcon_val.getHeight();
            ImP2.width=mIcon_val.getWidth();
            Log.i("width,height",ImP2.width+"..."+ ImP2.height);
            //ImP2.startToEnd=1;
            ImP2.startToStart=R.id.parent;
            ImP2.topToBottom=1;
            ImP2.topMargin=50;
            //Set image Parameters ie resize accordingly...
            //set.connect(Iv.getId(), ConstraintSet.TOP, Iv1.getId(), ConstraintSet.BOTTOM, 60);
            //THE connect() function accepts parametrs(ID of element to be displayed,which propertyof element to be displayed ,element with respect to which we are assigning property,which property of element to affect)
          //  set.applyTo(CL);
            Iv.setImageBitmap(mIcon_val);

            Log.i("msg","Displayed image 2");
        }
        catch (Exception E){
            Log.i("msg","error");
        }















        //Get object corresponding to BottomNavigationBarNamed navigationView
        BottomNavigationView bottombar=(BottomNavigationView)findViewById(R.id.navigationView);
        bottombar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_notification:
                        //startActivity(new Intent(Notify.this,Notify.class));
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
