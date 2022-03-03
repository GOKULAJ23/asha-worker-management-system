package com.example.ashaworker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Ashaworkervieweventnotification extends AppCompatActivity implements JsonResponse{
    ListView l1;
    String[] event,date,value;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ashaworkervieweventnotification);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1=(ListView)findViewById(R.id.lvview);
        JsonReq JR1=new JsonReq();
        JR1.json_response=(JsonResponse)Ashaworkervieweventnotification.this;
        String q1="/ashaworkerviewneventotification?lid="+sh.getString("log_id","");
        q1=q1.replace(" ","%20");
        JR1.execute(q1);
    }

    @Override
    public void response(JSONObject jo) {
        try {
            String status=jo.getString("status");
            Log.d("pearl",status);


            if(status.equalsIgnoreCase("success")){
                JSONArray ja1=(JSONArray)jo.getJSONArray("data");

                event=new String[ja1.length()];
                date=new String[ja1.length()];
                value=new String[ja1.length()];

                for(int i = 0;i<ja1.length();i++)
                {
                    event[i]=ja1.getJSONObject(i).getString("event");
                    date[i]=ja1.getJSONObject(i).getString("date");
                    value[i]="event: "+event[i]+"\ndate: "+date[i];

                }
                ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,value);
                l1.setAdapter(ar);
            }




        }

        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}