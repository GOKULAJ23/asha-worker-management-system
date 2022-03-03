package com.example.ashaworker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Ashaworkerviewenquiry extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {
    ListView l1;
    String[] enquiry,reply,date,value,eid,username;
    public static  String eids,replys;
    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ashaworkerviewenquiry);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1=(ListView)findViewById(R.id.lvview);
        l1.setOnItemClickListener(this);
        JsonReq JR1=new JsonReq();
        JR1.json_response=(JsonResponse)Ashaworkerviewenquiry.this;
        String q1="/ashaworkerviewenquiry?lid="+sh.getString("log_id","");
        q1=q1.replace(" ","%20");
        JR1.execute(q1);

    }

    @Override
    public void response(JSONObject jo) {
        try {

            String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");

                eid=new String[ja1.length()];
                username = new String[ja1.length()];
               enquiry = new String[ja1.length()];
                reply = new String[ja1.length()];
                date = new String[ja1.length()];
                value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {

                    eid[i]=ja1.getJSONObject(i).getString("enquiry_id");
                    username[i]=ja1.getJSONObject(i).getString("firstname")+" "+ja1.getJSONObject(i).getString("lastname");
                   enquiry[i] = ja1.getJSONObject(i).getString("enquiry");
                    reply[i] = ja1.getJSONObject(i).getString("reply");
                    date[i] = ja1.getJSONObject(i).getString("date");
                    value[i] = "enquiry: " + enquiry[i] +"\nreply: "+reply[i]+"\ndatetime: " + date[i]+"\nusername:"+username[i];


                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, value);
                l1.setAdapter(ar);



            } else {
                Toast.makeText(getApplicationContext(), "Not successfull!!", Toast.LENGTH_LONG).show();

            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        eids=eid[position];
        replys=reply[position];


        if(replys.equalsIgnoreCase("pending")) {
            final CharSequence[] items = {"Send Reply", "Cancel"};

            AlertDialog.Builder builder = new AlertDialog.Builder(Ashaworkerviewenquiry.this);
            // builder.setTitle("Add Photo!");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {


                    if (items[item].equals("Send Reply")) {
                        startActivity(new Intent(getApplicationContext(), Ashaworkersendenquiry.class));

                    } else if (items[item].equals("Cancel")) {
                        dialog.dismiss();
                    }

                }

            });
            builder.show();
        }
    }
}