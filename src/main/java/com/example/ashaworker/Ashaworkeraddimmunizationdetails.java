package com.example.ashaworker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Ashaworkeraddimmunizationdetails extends AppCompatActivity implements JsonResponse{
    Button b1;
    ListView l1;
    String[] name,date,statuss,value;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ashaworkeraddimmunizationdetails);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1=(ListView)findViewById(R.id.lvview);
        JsonReq JR1=new JsonReq();
        JR1.json_response=(JsonResponse)Ashaworkeraddimmunizationdetails.this;
        String q1="/ashaworkerviewimmunization?lid="+sh.getString("log_id","");
        q1=q1.replace(" ","%20");
        JR1.execute(q1);
        b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) Ashaworkeraddimmunizationdetails.this;
                String q = "/ashaworkeraddimmunization?lid=" + sh.getString("log_id", "");
                q = q.replace(" ", "%20");
                JR.execute(q);
            }
        });
    }

    @Override
    public void response(JSONObject jo) {
        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("ashaworkeraddimmunization")) {

                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "ADDED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Ashaworkeraddimmunizationdetails.class));

                } else {

                    Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                }
            }


            else if(method.equalsIgnoreCase("ashaworkerviewimmunization"))
            {
                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");
                    name=new String[ja1.length()];
                    date=new String[ja1.length()];
                    statuss=new String[ja1.length()];
                    value=new String[ja1.length()];

                    for(int i = 0;i<ja1.length();i++)
                    {
                        name[i]=ja1.getJSONObject(i).getString("firstname")+" "+ja1.getJSONObject(i).getString("lastname");
                        statuss[i]=ja1.getJSONObject(i).getString("status");
                        date[i]=ja1.getJSONObject(i).getString("date");
                        value[i]="Workername:"+name[i]+"\ndate: "+date[i]+"\nstatuss:"+statuss[i];

                    }
                    ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,value);
                    l1.setAdapter(ar);
                }
            }

        }

        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}