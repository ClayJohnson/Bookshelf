package com.example.testfragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.os.Bundle; 
import android.support.v4.app.Fragment; 
import android.util.Log; 
import android.view.LayoutInflater; 
import android.view.View; 
import android.view.ViewGroup; 
import android.widget.TextView; 
import android.widget.Toast;
 
 
public class FragmentA extends Fragment
{
	
	private String tag = getClass().getSimpleName(); 
 	View v; 
 	int pageId; 
 
 	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
 	{
 		
 		Log.d(tag, "onCreateView"); 
		View v = inflater.inflate(R.layout.tab1_fragment, container, false); 
		TextView tv1 = (TextView) v.findViewById(R.id.textView1); 
	
		//=================include sd card file
		TextView tv2 = (TextView) v.findViewById(R.id.textView2);
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
 

 	@Override 
 	public void onStart() { 
 		// TODO Auto-generated method stub 
 		super.onStart(); 
 		Log.d(tag, "onStart"); 
 	} 
 } 

