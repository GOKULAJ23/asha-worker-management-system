package com.example.ashaworker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class Ashaworkersendenquiry extends AppCompatActivity implements JsonResponse{
    EditText e1;
    Button b1;
    String reply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ashaworkersendenquiry);
        e1=(EditText)findViewById(R.id.etreply);
        b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reply=e1.getText().toString();
                JsonReq JR=new JsonReq();
                JR.json_response=(JsonResponse)Ashaworkersendenquiry.this;
                String q="/ashaworkersendreply?eid="+Ashaworkerviewenquiry.eids+"&reply="+reply;
                q=q.replace(" ","%20");
                JR.execute(q);
            }
        });



    }

    @Override
    public void response(JSONObject jo) {
        try {
            String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                Toast.makeText(getApplicationContext(), "Updated SUCCESS", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), Ashaworkerviewenquiry.class));

            }
            else {
                startActivity(new Intent(getApplicationContext(), Ashaworkersendenquiry.class));

                Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }
}