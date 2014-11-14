package com.example.bookshelf_initialpage;

/**
 * @author Anjana Chatta
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SingleViewActivity extends Activity {

	ArrayList<String> arrList = new ArrayList<String>();

	String arr_PageData[]; // Stores the text to swipe.
	LayoutInflater inflater; // Used to create individual pages
	ViewPager vp; // Reference to class to swipe views
	StringBuilder sb_text;
	File myFile;
	FileInputStream fIn;
	BufferedReader myReader;
	String strLine = "";
	String[] mStringArray;

	/**
	 * This method reads a .txt file from SD card of the AVD and dispalys the
	 * contents onto the device text view. The user can scroll the pages across
	 * from left to right and right to left.
	 */

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_view);

		String ExternalStorageDirectoryPath = Environment
				.getExternalStorageDirectory().getAbsolutePath();
		String targetPath = ExternalStorageDirectoryPath + "/SDFILE.txt";

		// bind GUI elements with local controls
		TextView tv;
		tv = (TextView) findViewById(R.id.textView);

		sb_text = new StringBuilder();

		try {

			myFile = new File(targetPath);
			fIn = new FileInputStream(myFile); // FileInputStream
			myReader = new BufferedReader(new InputStreamReader(fIn)); // BufferedInput
			strLine = "";

			while ((strLine = myReader.readLine()) != null) {
				sb_text.append(strLine + "\n");
				arrList.add(strLine); // new
			}

			// new
			mStringArray = new String[arrList.size()];
			mStringArray = arrList.toArray(mStringArray);

			for (int i = 0; i < mStringArray.length; i++) {
				Log.d("string is", (mStringArray[i]));
			}

			// new
			myReader.close();

			Toast.makeText(getBaseContext(), "Done reading SD 'mysdfile.txt'",
					Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT)
					.show();
		}
		tv.setText(sb_text); // onto textView

		// get an inflater to be used to create single pages
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// Reference ViewPager defined in activity
		vp = (ViewPager) findViewById(R.id.viewPager);
		// set the adapter that will create the individual pages
		vp.setAdapter(new Pages_Adapter());

	}

	// another class
	class Pages_Adapter extends PagerAdapter {

		// Implement PagerAdapter Class to handle individual page creation
		@Override
		public int getCount() {
			// Return total pages, here one for each data item
			// return arr_PageData.length;
			return mStringArray.length;
		}

		// Create the given page (indicated by position)
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View page = inflater.inflate(R.layout.single_view, null);
			((TextView) page.findViewById(R.id.textView))
					.setText(mStringArray[position]); // from single_view xml

			// Add the page to the front of the queue
			((ViewPager) container).addView(page, 0);
			return page;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// See if object from instantiateItem is related to the given view
			// required by API
			return arg0 == (View) arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView((View) object);
			object = null;
		}
	}

}
