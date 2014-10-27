package com.example.InitialPage_GridClick;
/**
* @author Anjana Chatta
*/


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      
      GridView gridview = (GridView) findViewById(R.id.gridView);
      gridview.setAdapter(new ImageAdapter(this));


      gridview.setOnItemClickListener(new OnItemClickListener() 
      {
          public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) 
          {
			// Send intent to SingleViewActivity
            Intent intent = new Intent(getApplicationContext(), SingleViewActivity.class);
             startActivityForResult(intent,0);
			
        }
      });
   }

  
   
}