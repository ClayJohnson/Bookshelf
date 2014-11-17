package com.example.bookshelf_initialpage;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * DESCRIPTION OF CLASS TO BE FILLED IN
 * 
 * @author Anjana Chatta
 * 
 */
@SuppressLint("NewApi")
public class Fragments extends Fragment implements View.OnClickListener {
	private final static String strText = "TEXT";
	TextView textView;
	public Button btnDay, btnNight;

	/**
	 * DESCRIPTION OF METHOD TO BE FILLED IN
	 * 
	 * @param pageText
	 *            DESCRIPTON OF PARAMETER TO BE FILLED IN
	 * @return DESCRIPTION OF RETURN VALUE TO BE FILLED IN
	 */
	public static Fragments newInstance(CharSequence pageText) {
		Fragments fragments = new Fragments();
		Bundle bundle = new Bundle();
		bundle.putCharSequence(strText, pageText);
		fragments.setArguments(bundle);
		return fragments;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		CharSequence pagetext = getArguments().getCharSequence(strText);
		View view = inflater.inflate(R.layout.page, container, false);
		textView = (TextView) inflater.inflate(R.layout.page, container, false);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources()
				.getDimension(R.dimen.text_size));
		textView.setText(pagetext);
		return textView;
	}

	/**
	 * DESCRIPTION OF METHOD TO BE FILLED IN
	 */
	public void changeDAYColor() {
		textView.setTextColor(Color.BLACK);
		textView.setBackgroundColor(Color.WHITE);
	}

	/**
	 * DESCRIPTION OF METHOD TO BE FILLED IN
	 */
	public void changeNIGHTColor() {

		textView.setTextColor(Color.WHITE);
		textView.setBackgroundColor(Color.BLACK);

	}

	/**
	 * DESCRIPTION OF METHOD TO BE FILLED IN
	 */
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}

	/**
	 * DESCRIPTION OF METHOD TO BE FILLED IN
	 */
	@SuppressLint("NewApi")
	public void zoom_IN() {
		// TODO Auto-generated method stub

		float x = textView.getScaleX();
		float y = textView.getScaleY();

		textView.setScaleX((float) (x + 1));
		textView.setScaleY((float) (y + 1));

	}

	/**
	 * DESCRIPTION OF METHOD TO BE FILLED IN
	 */
	public void zoom_OUT() {
		// TODO Auto-generated method stub

		float x = textView.getScaleX();
		float y = textView.getScaleY();

		textView.setScaleX((float) (x - 1));
		textView.setScaleY((float) (y - 1));

	}

}