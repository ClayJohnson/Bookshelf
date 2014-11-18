package com.example.zoomfragment;

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

import android.view.View;

import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ZoomControls;

public class MainActivityViewPager extends FragmentActivity implements
		View.OnClickListener {
	private ViewPager viewPager;
	StringBuilder sb_text;
	File myFile;
	ArrayList<String> arrList;
	String targetPath;
	public Button btnDay, btnNight, btnzoom_in, btnzoom_out;
	TextPaint textPaint;
	Custom_PagerAdpater cpa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.viewpageractivity_main);

		String ExternalStorageDirectoryPath = Environment
				.getExternalStorageDirectory().getAbsolutePath();
		targetPath = ExternalStorageDirectoryPath + "/SDFILE.txt";

		viewPager = (ViewPager) findViewById(R.id.pages); // pages is the id of
															// viewPager in xml
															// file

		// to get ViewPager width and height we have to wait global layout
		viewPager.getViewTreeObserver().addOnGlobalLayoutListener(
				new ViewTreeObserver.OnGlobalLayoutListener() {
					@SuppressLint("NewApi")
					@Override
					public void onGlobalLayout() {
						SplitFragment splitFragments = new SplitFragment(
								viewPager.getWidth(), viewPager.getHeight(), 1,
								0);

						// TextPaint textPaint = new TextPaint();
						textPaint = new TextPaint();
						textPaint.setTextSize(getResources().getDimension(
								R.dimen.text_size));

						arrList = new ArrayList<String>();
						;
						sb_text = new StringBuilder();
						try {
							myFile = new File("/sdcard/SDFILE.txt");
							// FileInputStream
							FileInputStream fIn = new FileInputStream(myFile);
							// BufferedInput
							BufferedReader myReader = new BufferedReader(
									new InputStreamReader(fIn));

							String fileLine = "";
							String strLines = "";
							while ((fileLine = myReader.readLine()) != null) {
								strLines += fileLine + "\n";
								// add all the lines from the text into
								// ArrayList
								// arrList.add(strLines);
								arrList.add(fileLine);
							}

							for (int i = 0; i < arrList.size(); i++) {
								splitFragments.append(arrList.get(i), textPaint);
							}

							myReader.close();

						} catch (Exception e) {
							e.printStackTrace();
						}

						cpa = new Custom_PagerAdpater(
								getSupportFragmentManager(), splitFragments
										.getPages());
						viewPager.setAdapter(cpa);
						viewPager.getViewTreeObserver()
								.removeOnGlobalLayoutListener(this);

					}
				});

		btnDay = (Button) findViewById(R.id.button1);
		btnNight = (Button) findViewById(R.id.button2);
		btnzoom_in = (Button) findViewById(R.id.button3);
		btnzoom_out = (Button) findViewById(R.id.button4);
		
		btnDay.setOnClickListener(this);
		btnNight.setOnClickListener(this);
		btnzoom_in.setOnClickListener(this);
		btnzoom_out.setOnClickListener(this);

	}// onCreate

	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {

		int index;
		Custom_PagerAdpater adapter;
		android.support.v4.app.Fragment frags;

		switch (v.getId()) {
		case R.id.button1: // DAY
			index = viewPager.getCurrentItem();
			adapter = ((Custom_PagerAdpater) viewPager.getAdapter());
			frags = adapter.getFragment(index);
			((Fragments) frags).changeDAYColor();

			break;
		case R.id.button2: // NIGHT
			index = viewPager.getCurrentItem();
			adapter = ((Custom_PagerAdpater) viewPager.getAdapter());
			frags = adapter.getFragment(index);
			((Fragments) frags).changeNIGHTColor();

			break;

		case R.id.button3:
			index = viewPager.getCurrentItem();
			adapter = ((Custom_PagerAdpater) viewPager.getAdapter());
			frags = adapter.getFragment(index);
			((Fragments) frags).zoom_IN();

			break;

		case R.id.button4:
			index = viewPager.getCurrentItem();
			adapter = ((Custom_PagerAdpater) viewPager.getAdapter());
			frags = adapter.getFragment(index);
			((Fragments) frags).zoom_OUT();

			break;
		}

	}// onclick

}// main

