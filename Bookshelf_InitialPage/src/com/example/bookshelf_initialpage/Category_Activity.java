package com.example.bookshelf_initialpage;

/**
 *@author Anjana Chatta 
 * Category displays all book categories. Two buttons 
 * one to Add the categories and to delete a category enables to 
 * add and delete a category from the database respectively
 */

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Category_Activity extends ActionBarActivity implements
		View.OnClickListener {

	private ListView listView;
	private List<String> fileNameList;
	private FileAdapter mAdapter;
	private File file;
	public Button btnLoadintoBookshelf;
	public Bundle basket;
	String itemValue;
	int itemPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file_list); // has the list

		listView = (ListView) findViewById(R.id.listView1);
		file = Environment.getExternalStorageDirectory();
		// fileNameList = getFileListfromSDCard();
		fileNameList = new ArrayList<String>();

		fileNameList.add("Agriculture");
		fileNameList.add("Art");
		fileNameList.add("Business");
		fileNameList.add("Children's Fiction");
		fileNameList.add("Family & Parenting");
		fileNameList.add("Fiction");
		fileNameList.add("Fun & Games");
		fileNameList.add("History");
		fileNameList.add("Medical");
		fileNameList.add("Transportation");
		fileNameList.add("Travel");

		// Define a new Adapter
		// First parameter - Context
		// Second parameter - Layout for the row
		// Third parameter - ID of the TextView to which the data is written
		// Forth - the Array of data

		mAdapter = new FileAdapter(this, R.layout.file_listitem, fileNameList); // has
																				// the
																				// button
		// Assign adapter to ListView
		listView.setAdapter(mAdapter);

		btnLoadintoBookshelf = (Button) findViewById(R.id.loadintobookshelf);
		btnLoadintoBookshelf.setOnClickListener(this);

		// new
		// ListView Item Click Listener
		listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// ListView Clicked item index
				itemPosition = position;
				// ListView Clicked item value
				itemValue = (String) listView.getItemAtPosition(position);
				// Show Alert
				/*
				 * Toast.makeText(getApplicationContext(),
				 * "Position :"+itemPosition+"  ListItem : " +itemValue ,
				 * Toast.LENGTH_LONG) .show();
				 */
				Toast.makeText(getApplicationContext(),
						"ListItem : " + itemValue, Toast.LENGTH_LONG).show();
				}
		});
	}// new

	/*private List<String> getFileListfromSDCard() {
		// String state = Environment.getExternalStorageState();
		String state = Environment.getExternalStorageState();
		List<String> fileList = new ArrayList<String>();
		// if (Environment.MEDIA_MOUNTED.equals(state) && file.isDirectory()) {
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			File[] fileArr = file.listFiles();
			int length = fileArr.length;
			for (int i = 0; i < length; i++) {
				File f = fileArr[i];
				fileList.add(f.getName());
			}
		}

		return fileList;
	}*/

	public class FileAdapter extends ArrayAdapter<String> {

		private List<String> f_List;
		private Context adapContext;

		public FileAdapter(Context context, int textViewResourceId,
				List<String> f_List) {
			super(context, textViewResourceId, f_List);
			this.f_List = f_List;
			adapContext = context;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View view = convertView;
			FHolder fHolder = null;

			if (convertView == null) {
				view = View.inflate(adapContext, R.layout.file_listitem, null);

				fHolder = new FHolder();
				fHolder.fNameView = (TextView) view.findViewById(R.id.fname);

				view.setTag(fHolder);
			} else {
				fHolder = (FHolder) view.getTag();
			}
			String fileName = f_List.get(position);
			fHolder.fNameView.setText(fileName);

			return view;
		}
	}

	static class FHolder {
		public TextView fNameView;
	}

	public void onClick(View v) {
		int index;
		Intent intent;

		switch (v.getId()) {
		case R.id.loadintobookshelf: // LOAD INTO BOOKSHELF
			// Send intent to MainActivity
			intent = new Intent(getApplicationContext(), MainActivity.class);
			intent.putExtra("ListItemValue", itemValue);
			startActivity(intent);
			break;

		case R.id.deletebookfromshelf:
			break;

		}
	}

}// end

