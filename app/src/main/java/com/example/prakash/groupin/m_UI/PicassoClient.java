package com.example.prakash.groupin.m_UI;

import android.content.Context;
import android.widget.ImageView;

import com.example.prakash.groupin.R;
import com.squareup.picasso.Picasso;

public class PicassoClient {
    public static void downloadImage(Context c, String imageUrl, ImageView img) {
        if (imageUrl.length() > 0 && imageUrl != null)
            Picasso.with(c).load(imageUrl).placeholder(R.drawable.backg).into(img);
        else
            Picasso.with(c).load(R.drawable.backg).into(img);
    }
}
