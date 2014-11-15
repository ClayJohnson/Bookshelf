 package com.example.bookshelf_initialpage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.internal.widget.AdapterViewCompat.OnItemLongClickListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class Load_Activity extends ActionBarActivity implements
		OnItemLongClickListener, View.OnClickListener {

	private ListView mListView;
	private List<String> fileNameList;
	private FileAdapter mAdapter;
	private File file;
	public Button btnLoadintoBookshelf;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file_list);

		mListView = (ListView) findViewById(R.id.listView1);
		file = Environment.getExternalStorageDirectory();
		fileNameList = getFileListfromSDCard();
		mAdapter = new FileAdapter(this, R.layout.file_listitem, fileNameList);
		mListView.setAdapter(mAdapter);

		btnLoadintoBookshelf = (Button) findViewById(R.id.loadintobookshelf);
		btnLoadintoBookshelf.setOnClickListener(this);

	}

	private List<String> getFileListfromSDCard() {
		String state = Environment.getExternalStorageState();
		List<String> flLst = new ArrayList<String>();
		//if (Environment.MEDIA_MOUNTED.equals(state) && file.isDirectory()) {
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			File[] fileArr = file.listFiles();
			int length = fileArr.length;
			for (int i = 0; i < length; i++) {
				File f = fileArr[i];
				flLst.add(f.getName());
			}
		}

		return flLst;
	}

	public class FileAdapter extends ArrayAdapter<String>
	{

		private List<String> fLst;
		private Context adapContext;

		public FileAdapter(Context context, int textViewResourceId,
				List<String> fLst) {
			super(context, textViewResourceId, fLst);
			this.fLst = fLst;
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
			String fileName = fLst.get(position);
			fHolder.fNameView.setText(fileName);

			return view;
		}
	}

	static class FHolder {
		public TextView fNameView;
	}

	@Override
	public boolean onItemLongClick(AdapterViewCompat<?> arg0, View arg1,
			int arg2, long arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onClick(View v) {
		int index;
		switch (v.getId()) {
		case R.id.loadintobookshelf: // LOAD INTO BOOKSHELF
			// Send intent to SingleViewActivity
			//Intent intent = new Intent(getApplicationContext(), MainActivity.class);
			//startActivityForResult(intent, 0);
			break;
		}
	}
}




