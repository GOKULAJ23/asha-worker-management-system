package com.example.ashaworker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Usersendenquiry extends AppCompatActivity implements JsonResponse, AdapterView.OnItemSelectedListener {
    EditText e1;
    public  static String enquiry,worker_id;
    ListView l1;
    Spinner s1;
    Button b1;
    SharedPreferences sh;
    String[] workername,enquirys,date,reply,value,workerid,workernames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersendenquiry);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1=(EditText)findViewById(R.id.etenq);

        s1=(Spinner)findViewById(R.id.spinner);
        s1.setOnItemSelectedListener(this);
        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse)Usersendenquiry.this;
        String q="/userviewworker";
        q=q.replace(" ","%20");
        JR.execute(q);


        b1=(Button)findViewById(R.id.button);


        l1=(ListView)findViewById(R.id.lvview);
        JsonReq JR1=new JsonReq();
        JR1.json_response=(JsonResponse)Usersendenquiry.this;
        String q1="/userviewenquiry?lid="+sh.getString("log_id","");
        q1=q1.replace(" ","%20");
        JR1.execute(q1);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enquiry=e1.getText().toString();
                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) Usersendenquiry.this;
                String q = "/usersendenquiry?enquiry=" + enquiry + "&lid=" + sh.getString("log_id", "")+"&wid="+worker_id;
                q = q.replace(" ", "%20");
                JR.execute(q);
            }
        });
    }

    @Override
    public void response(JSONObject jo) {
        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("usersendenquiry")) {

                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "ADDED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Usersendenquiry.class));

                } else {

                    Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                }
            }


            else if(method.equalsIgnoreCase("userviewenquiry"))
            {
                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");
                    workername=new String[ja1.length()];
                    enquirys=new String[ja1.length()];
                    reply=new String[ja1.length()];
                    date=new String[ja1.length()];
                    value=new String[ja1.length()];

                    for(int i = 0;i<ja1.length();i++)
                    {
                        workername[i]=ja1.getJSONObject(i).getString("firstname")+" "+ja1.getJSONObject(i).getString("lastname");
                        enquirys[i]=ja1.getJSONObject(i).getString("enquiry");
                        reply[i]=ja1.getJSONObject(i).getString("reply");
                        date[i]=ja1.getJSONObject(i).getString("date");
                        value[i]="Workername:"+workername[i]+"\nenquiry: "+enquirys[i]+"\nreply: "+reply[i]+"\ndate: "+date[i];

                    }
                    ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,value);
                    l1.setAdapter(ar);
                }
            }


            else if(method.equalsIgnoreCase("userviewworker")) {
                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");


                    workerid = new String[ja1.length()];
                    workernames = new String[ja1.length()];
                    // value=new String[ja1.length()];

                    for (int i = 0; i < ja1.length(); i++) {

                        workerid[i] = ja1.getJSONObject(i).getString("worker_id");
                        workernames[i] = ja1.getJSONObject(i).getString("firstname")+" "+ja1.getJSONObject(i).getString("lastname");
                        // value[i] = "docname: " + name[i]+"\ndescription: "+description[i];

                    }
                    ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, workernames);
                    s1.setAdapter(ar);

                }
            }

        }

        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        worker_id=workerid[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}