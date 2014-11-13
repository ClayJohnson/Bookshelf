package com.example.fragmentwithdaynightmode;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle; 
import android.support.v4.app.Fragment; 
import android.util.Log; 
import android.view.LayoutInflater; 
import android.view.View; 
import android.view.ViewGroup; 
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView; 
import android.widget.Toast;
 
 
public class FragmentA extends Fragment implements View.OnClickListener
{
	
	private String tag = getClass().getSimpleName(); 
 	View v; 
 	int pageId; 
 	public Button btnDay,btnNight; 
 	TextView tv2;
	
 
 	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
 	{
 		
 		Log.d(tag, "onCreateView"); 
		View v = inflater.inflate(R.layout.fragment, container, false); 
		final TextView tv1 = (TextView) v.findViewById(R.id.textView1); 
		btnDay = (Button)v.findViewById(R.id.button1);
		btnNight = (Button)v.findViewById(R.id.button2);
 		
	
		//=================include sd card file
		tv2 = (TextView) v.findViewById(R.id.textView2);
		try {
			File myFile = new File("/sdcard/SDFILE.txt");
			FileInputStream fIn = new FileInputStream(myFile);           //FileInputStream
			BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));               //BufferedInput
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null) {
				aBuffer += aDataRow + "\n";
			}
			tv2.setText(aBuffer);
			myReader.close();
			Toast.makeText(getActivity(),"Done reading SD 'mysdfile.txt'",Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getActivity(), e.getMessage(),
					Toast.LENGTH_SHORT).show();
		}
		//===============================
		
		
		tv1.setText("Page " + pageId); 
		
		btnDay.setOnClickListener(this);
		btnNight.setOnClickListener(this);
		

   return v; 
 	} 
 
 	@Override 
	public void onActivityCreated(Bundle savedInstanceState) { 
 		// TODO Auto-generated method stub 
 		super.onActivityCreated(savedInstanceState); 
 
		
 	} 
 
 
 	public void updateView(int pageId) { 
 		this.pageId = pageId; 
 		Log.d(tag, "update view ja"); 
 
 
 	} 
 

 /*	@Override 
 	public void onStart() { 
 		// TODO Auto-generated method stub 
 		super.onStart(); 
 		Log.d(tag, "onStart"); 
 	}*/

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
        case R.id.button1:     //DAY
        		tv2.setTextColor(Color.BLACK);
		        tv2.setBackgroundColor(Color.WHITE);
		        break;
        case R.id.button2:     //NIGHT
    		tv2.setTextColor(Color.WHITE);
	        tv2.setBackgroundColor(Color.BLACK);
	        break;
		  }
		
		/*if(v.getId() == R.id.button1)
		{
			tv2.setTextColor(Color.BLACK);
	        tv2.setBackgroundColor(Color.WHITE);
	    }
		else if(v.getId() == R.id.button2)
		{
			tv2.setTextColor(Color.WHITE);      //night
	        tv2.setBackgroundColor(Color.BLACK);
		}
		*/
		
	} 
 } 











