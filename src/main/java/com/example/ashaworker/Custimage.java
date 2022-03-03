package com.example.ashaworker;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Custimage extends ArrayAdapter<String>  {

	 private Activity context;       //for to get current activity context
	    SharedPreferences sh;
	private String[] file;
	private String[] name;
	private String[] healthdetails;
	private String[] date;
	private String[] statuss;
//	private String [] pincode;
//	private String[] housename;
//	private String[] gender;
//	private String[] dob;
//	private String[] phone;
//	private String[] email;

	
	 public Custimage(Activity context, String[] file,String[] name,String[] healthdetails,String[] date,String[] statuss){
	        //constructor of this class to get the values from main_activity_class

	        super(context, R.layout.cust_images, file);
	        this.context = context;
	        this.file = file;
		    this.name = name;
		    this.healthdetails = healthdetails;
		    this.date = date;
		    this.statuss=statuss;
//		    this.pincode=pincode;
//		 	this.housename = housename;
//		    this.gender = gender;
//		    this.dob = dob;
//		    this.phone = phone;
//		    this.email = email;


	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	                 //override getView() method

	        LayoutInflater inflater = context.getLayoutInflater();
	        View listViewItem = inflater.inflate(R.layout.cust_images, null, true);
			//cust_list_view is xml file of layout created in step no.2

	        ImageView im = (ImageView) listViewItem.findViewById(R.id.imageView1);

	        TextView t1=(TextView)listViewItem.findViewById(R.id.textView3);
	        TextView t2=(TextView)listViewItem.findViewById(R.id.textView5);
			TextView t3=(TextView)listViewItem.findViewById(R.id.textView7);
			TextView t4=(TextView)listViewItem.findViewById(R.id.textView8);
//			TextView t5=(TextView)listViewItem.findViewById(R.id.textView9);
//			TextView t6=(TextView)listViewItem.findViewById(R.id.textView10);
//			TextView t7=(TextView)listViewItem.findViewById(R.id.textView11);
//			TextView t8=(TextView)listViewItem.findViewById(R.id.textView12);
//			TextView t9=(TextView)listViewItem.findViewById(R.id.textView13);
//			TextView t10=(TextView)listViewItem.findViewById(R.id.textView14);

			t1.setText(name[position]);
			t2.setText(healthdetails[position]);
			t3.setText(date[position]);
			t4.setText(statuss[position]);
//			t5.setText(pincode[position]);
//			t6.setText(housename[position]);
//			t7.setText(gender[position]);
//			t8.setText(dob[position]);
//			t9.setText(phone[position]);
//			t10.setText(email[position]);


	        sh=PreferenceManager.getDefaultSharedPreferences(getContext());
	        
	       String pth = "http://"+sh.getString("ip", "")+"/"+file[position];
	       pth = pth.replace("~", "");
//	       Toast.makeText(context, pth, Toast.LENGTH_LONG).show();
	        
	        Log.d("-------------", pth);
	        Picasso.with(context)
	                .load(pth)
	                .placeholder(R.drawable.ic_launcher_background)
	                .error(R.drawable.ic_launcher_background).into(im);
	        
	        return  listViewItem;
	    }

		private TextView setText(String string) {
			// TODO Auto-generated method stub
			return null;
		}
}