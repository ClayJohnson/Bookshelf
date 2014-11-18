package com.example.bookshelf_initialpage;

import android.support.v7.app.ActionBarActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

/**
 * CLASS DESCRPTION TBI
 * 
<<<<<<< HEAD
 * @author Anjana This is the MainActivity the starting page of the Android app.
 *         Displays a Bookshelf with all books When the user clicks on one item
 *         or Book, it openes up into the Read Book page where the user can read
 *         pages from the book. The onclick of Load, Category and Search buttons
 *         perform their respective actions
=======
 * @author Anjana Chatta
>>>>>>> branch 'master' of https://github.com/ClayJohnson/Bookshelf.git
 * 
 */
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/ClayJohnson/Bookshelf.git
public class MainActivity extends ActionBarActivity implements
		View.OnClickListener {

	public Button btnLoad;
<<<<<<< HEAD
	public Button btnCategory;
	public Button btnSearch;
	public Integer intItem;;
=======

	/*
	 * @Override //Click opens to read a file protected void onCreate(Bundle
	 * savedInstanceState) { super.onCreate(savedInstanceState);
	 * setContentView(R.layout.activity_main);
	 * 
	 * GridView gridview = (GridView) findViewById(R.id.gridView);
	 * gridview.setAdapter(new ImageAdapter(this));
	 * 
	 * gridview.setOnItemClickListener(new OnItemClickListener() { public void
	 * onItemClick(AdapterView<?> parent, View view, int position, long id) { //
	 * Send intent to SingleViewActivity Intent intent = new
	 * Intent(getApplicationContext(), SingleViewActivity.class);
	 * startActivityForResult(intent, 0);
	 * 
	 * } });
	 */
>>>>>>> branch 'master' of https://github.com/ClayJohnson/Bookshelf.git

	// Displays the item number
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		GridView gridview = (GridView) findViewById(R.id.gridView);
		gridview.setAdapter(new ImageAdapter(this));

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Toast.makeText(MainActivity.this, "" + position,
						Toast.LENGTH_SHORT).show();
<<<<<<< HEAD
=======
				// Send intent to SingleViewActivity
>>>>>>> branch 'master' of https://github.com/ClayJohnson/Bookshelf.git
				Intent intent = new Intent(getApplicationContext(),
						MainActivityViewPager.class);
				startActivityForResult(intent, 0);
			}
		});

		btnLoad = (Button) findViewById(R.id.loadbutton);
		btnLoad.setOnClickListener(this);
<<<<<<< HEAD

		btnCategory = (Button) findViewById(R.id.categorybutton);
		btnCategory.setOnClickListener(this);

		btnSearch = (Button) findViewById(R.id.searchbutton);
		btnSearch.setOnClickListener(this);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String value = getIntent().getStringExtra("ListItemValue");
			// String value = extras.getString("ListItemValue");
			Toast.makeText(getApplicationContext(), "ListItem : " + value,
					Toast.LENGTH_LONG).show();
=======
		// btnLoad.setOnClickListener(l)
	} // onCreate

	public void onClick(View v) {
		int index;
		switch (v.getId()) {
		case R.id.loadbutton: // LOAD
			// Send intent to SingleViewActivity
			Intent intent = new Intent(getApplicationContext(),
					Load_Activity.class);
			startActivityForResult(intent, 0);
			break;
>>>>>>> branch 'master' of https://github.com/ClayJohnson/Bookshelf.git
		}
<<<<<<< HEAD

		/*
		 * Toast.makeText(getApplicationContext(), "ListItem : " +
		 * getIntent().getExtras().getString("ListItemValue"),
		 * Toast.LENGTH_LONG) .show();
		 */

	} // onCreate

	
	public void onClick(View v) {
		Intent intent;
		
		switch (v.getId()) {
		case R.id.loadbutton: // LOAD
			// Send intent to SingleViewActivity
			intent = new Intent(getApplicationContext(), Load_Activity.class);
			startActivity(intent);
			break;

		case R.id.categorybutton:
			// Send intent to SingleViewActivity
			intent = new Intent(getApplicationContext(),
					Category_Activity.class);
			startActivity(intent);
			break;

		case R.id.searchbutton:
			// Send intent to SingleViewActivity
			intent = new Intent(getApplicationContext(), Search_Activity.class);
			startActivity(intent);
			break;
		}

	}

	private String getItemValue() {
		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			String value = extras.getString("ListItemValue");
			Toast.makeText(getApplicationContext(), "ListItem : " + value,
					Toast.LENGTH_LONG).show();
		}
		return null;
=======
>>>>>>> branch 'master' of https://github.com/ClayJohnson/Bookshelf.git
	}

}
