package com.example.bookshelf_initialpage;

import android.support.v7.app.ActionBarActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

/**
 * 
 * @author Anjana
 *
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener 
{

	public Button btnLoad;
	
	/*@Override  //Click opens to read a file
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		GridView gridview = (GridView) findViewById(R.id.gridView);
		gridview.setAdapter(new ImageAdapter(this));
		
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// Send intent to SingleViewActivity
				Intent intent = new Intent(getApplicationContext(),
						SingleViewActivity.class);
				startActivityForResult(intent, 0);

			}
		});  */
	
	//Displays the item number 
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);

	    GridView gridview = (GridView) findViewById(R.id.gridView);
	    gridview.setAdapter(new ImageAdapter(this));

	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
	         // Send intent to SingleViewActivity
				Intent intent = new Intent(getApplicationContext(),
						MainActivityViewPager.class);
				startActivityForResult(intent, 0);
	        }
	    });
	
	    
	    btnLoad = (Button) findViewById(R.id.loadbutton);
		btnLoad.setOnClickListener(this);
		//btnLoad.setOnClickListener(l)
	 } //onCreate

	  
		public void onClick(View v) {
			int index;
			switch (v.getId()) {
			case R.id.loadbutton: // LOAD
				// Send intent to SingleViewActivity
				Intent intent = new Intent(getApplicationContext(), Load_Activity.class);
				startActivityForResult(intent, 0);
				break;
			}
		}
	
}
