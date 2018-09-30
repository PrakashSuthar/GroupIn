package com.example.prakash.groupin.m_MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.prakash.groupin.m_DataObject.Spacecraft;
import com.example.prakash.groupin.m_UI.CustomAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class DataParser extends AsyncTask<Void,Void,Integer> {

    Context c;
    String jsonData;
    RecyclerView rv;
    ProgressDialog pd;
    ArrayList<Spacecraft> spacecrafts=new ArrayList<>();

    public DataParser(Context c, String jsonData, RecyclerView rv) {
        this.c = c;
        this.jsonData = jsonData;
        this.rv = rv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("parse");
        pd.setMessage("Parsing......Please Wait");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        pd.dismiss();
        if (result==0){
            Toast.makeText(c,"Unable to parse",Toast.LENGTH_SHORT).show();
        }
        else {
            //BIND DATA TO RECYCLER VIEW
            Toast.makeText(c,"Adapter being attached",Toast.LENGTH_SHORT).show();
            CustomAdapter adapter=new CustomAdapter(c,spacecrafts);
            rv.setAdapter(adapter);
        }
    }
    private int parseData(){
        try {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;
            spacecrafts.clear();
            Spacecraft spacecraft;
            for (int i=0;i<ja.length();i++){
                jo=ja.getJSONObject(i);
                int id=jo.getInt("id");
                String name=jo.getString("title");
                String imageUrl=jo.getString("url");
                spacecraft=new Spacecraft();
                spacecraft.setId(id);
                spacecraft.setName(name);
                spacecraft.setImageUrl(imageUrl);
                spacecrafts.add(spacecraft);
            }
            return 1;
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        return 0;
    }
}
