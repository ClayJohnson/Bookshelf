package com.example.fragmentmeasure;
/*Author Anjana Chatta*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.view.ViewTreeObserver;

public class MainActivity extends FragmentActivity {
    private ViewPager viewPager;
    StringBuilder sb_text;
	File myFile;
	ArrayList<String> arrList;
	String targetPath;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
       
        String ExternalStorageDirectoryPath = Environment
				.getExternalStorageDirectory().getAbsolutePath();
		targetPath = ExternalStorageDirectoryPath + "/SDFILE.txt";
       
		viewPager = (ViewPager) findViewById(R.id.pages);

        // to get ViewPager width and height we have to wait global layout
        viewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressLint("NewApi")
			@Override
            public void onGlobalLayout() {
                SplitFragment splitFragments = new SplitFragment(viewPager.getWidth(), viewPager.getHeight(), 1, 0);

                TextPaint textPaint = new TextPaint();
                textPaint.setTextSize(getResources().getDimension(R.dimen.text_size));
                
                arrList = new ArrayList<String>();;
                sb_text = new StringBuilder();
                try {
        			myFile = new File("/sdcard/SDFILE.txt");
        			 //FileInputStream
        			FileInputStream fIn = new FileInputStream(myFile);    
        			//BufferedInput
        			BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));        
        			
        			String fileLine = "";
        			String strLines = "";
        			while ((fileLine = myReader.readLine()) != null)
        			{
        				strLines += fileLine + "\n";
        				//add all the lines from the text into ArrayList
        				//arrList.add(strLines);
        				arrList.add(fileLine);
        			}
        		
        		  //for (int i = 0; i < 100; i++) {
        			for (int i = 0; i < arrList.size(); i++) {
        		        splitFragments.append(arrList.get(i), textPaint);
                       // if ((i + 1) % 10 == 0) {
                      //      splitFragments.append("\n", textPaint);
                      //  }
                    }
   				 
        			myReader.close();
        			
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
             
                viewPager.setAdapter(new Custom_PagerAdpater(getSupportFragmentManager(), splitFragments.getPages()));
                viewPager.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

    }
}
