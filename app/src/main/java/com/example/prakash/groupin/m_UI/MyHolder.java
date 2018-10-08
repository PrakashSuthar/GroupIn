package com.example.prakash.groupin.m_UI;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prakash.groupin.R;

public class MyHolder  extends RecyclerView.ViewHolder{
    TextView nameText;
    ImageView img;

    public MyHolder(View itemView){
        super(itemView);
        nameText=itemView.findViewById(R.id.nameTxt);
        img=(ImageView)itemView.findViewById(R.id.movieImage);

    }
}
