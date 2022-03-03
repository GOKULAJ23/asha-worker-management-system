package com.example.ashaworker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Userregisteration extends AppCompatActivity implements JsonResponse{
    EditText e1,e2,e3,e4,e5,e6,e7,e8;
    String fname,lname,phone,email,user,pass,place;
    Button b1;
    private DatePickerDialog fromDatePickerDialog;

    private SimpleDateFormat dateFormatter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userregisteration);

//        startService(new Intent(getApplicationContext(),LocationService.class));
        e1=(EditText)findViewById(R.id.etfname);
        e2=(EditText)findViewById(R.id.etlname);
        e3=(EditText)findViewById(R.id.etphone);
        e4=(EditText)findViewById(R.id.etemail);
        e6=(EditText)findViewById(R.id.etuser);
        e7=(EditText)findViewById(R.id.etpass);
        e8=(EditText)findViewById(R.id.etplace);
        b1=(Button)findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname=e1.getText().toString();
                lname=e2.getText().toString();
                phone=e3.getText().toString();
                email=e4.getText().toString();
                user=e6.getText().toString();
                pass=e7.getText().toString();
                place=e8.getText().toString();

                if(fname.equalsIgnoreCase(""))
                {
                    e1.setError("Enter Your First Name");
                    e1.setFocusable(true);
                }
                else if(lname.equalsIgnoreCase(""))
                {
                    e2.setError("Enter Your Last Name");
                    e2.setFocusable(true);
                }
                else if(phone.equalsIgnoreCase(""))
                {
                    e3.setError("Enter your phone number");
                    e3.setFocusable(true);
                }
                else if(email.equalsIgnoreCase(""))
                {
                    e4.setError("Enter your email");
                    e4.setFocusable(true);
                }
                else if(user.equalsIgnoreCase("")) {
                    e6.setError("Enter Username");
                    e6.setFocusable(true);
                }
                else if(pass.equalsIgnoreCase(""))
                {
                    e7.setError("Enter password");
                    e7.setFocusable(true);
                }
                else{
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Userregisteration.this;
                    String q ="/userregister?fname="+fname+"&lname="+lname+"&phone="+phone+"&email="+email+"&username="+user+"&password="+pass+"&place="+place;
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                }
            }

        });
    }




    @Override
    public void response(JSONObject jo) {
        try {
            String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                Toast.makeText(getApplicationContext(), "REGISTRATION SUCCESS", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), Login.class));

            } else if (status.equalsIgnoreCase("duplicate")) {
                startActivity(new Intent(getApplicationContext(), Userregisteration.class));
                Toast.makeText(getApplicationContext(), "Username and Password already Exist...", Toast.LENGTH_LONG).show();

            } else {
                startActivity(new Intent(getApplicationContext(), Userregisteration.class));

                Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}