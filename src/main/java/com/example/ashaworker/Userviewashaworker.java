package com.example.ashaworker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Userviewashaworker extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {
    ListView l1;
    String[] Name,place,phone,email,value,wid;
    public static String wids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userviewashaworker);
        l1=(ListView)findViewById(R.id.lvview);
        l1.setOnItemClickListener(this);
        JsonReq JR1=new JsonReq();
        JR1.json_response=(JsonResponse)Userviewashaworker.this;
        String q1="/userviewashaworkerdetail";
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

                wid=new String[ja1.length()];
                Name=new String[ja1.length()];
                place=new String[ja1.length()];
                phone=new String[ja1.length()];
                email=new String[ja1.length()];
                value=new String[ja1.length()];

                for(int i = 0;i<ja1.length();i++)
                {
                    wid[i]=ja1.getJSONObject(i).getString("worker_id");
                    Name[i]=ja1.getJSONObject(i).getString("firstname")+" "+ja1.getJSONObject(i).getString("lastname");
                    place[i]=ja1.getJSONObject(i).getString("place");
                    phone[i]=ja1.getJSONObject(i).getString("phone");
                    email[i]=ja1.getJSONObject(i).getString("email");
                    value[i]="Name: "+Name[i]+"\nplace: "+place[i]+"\nPhone:"+phone[i]+"\nemail:"+email[i];

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        wids=wid[position];




            final CharSequence[] items = {"Notification", "Cancel"};

            AlertDialog.Builder builder = new AlertDialog.Builder(Userviewashaworker.this);
            // builder.setTitle("Add Photo!");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {


                    if (items[item].equals("Notification")) {
                        startActivity(new Intent(getApplicationContext(), Uservieweventnotification.class));

                    } else if (items[item].equals("Cancel")) {
                        dialog.dismiss();
                    }

                }

            });
            builder.show();
    }
}