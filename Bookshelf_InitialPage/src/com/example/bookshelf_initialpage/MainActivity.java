package com.example.bookshelf_initialpage;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ActionBarActivity {

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
