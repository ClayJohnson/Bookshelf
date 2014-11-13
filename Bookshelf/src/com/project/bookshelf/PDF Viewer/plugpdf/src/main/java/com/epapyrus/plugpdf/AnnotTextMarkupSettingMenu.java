package com.epapyrus.plugpdf;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.PopupWindow;
import android.widget.Spinner;

import com.epapyrus.plugpdf.core.annotation.tool.AnnotSetting;
import com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType;

public class AnnotTextMarkupSettingMenu {
	private Context mContext;
	private LayoutInflater mInflater;
	private PopupWindow mPopupWindow;
	private AnnotToolType mAnnotType;
	private Spinner mLineColorSpinner;
	private CheckBox mSquiggly;
	
	public enum LineColorType {
		BLACK(0), RED(1), YELLOW(2), GREEN(3), BLUE(4), WHITE(5);

		private int mValue;

		LineColorType(int value) {
			mValue = value;
		}

		public int value() {
			return mValue;
		}
	}

	public AnnotTextMarkupSettingMenu(Context context) {
		mContext = context;
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	private int getSpinnerPos(int color) {
		switch (color) {
		case 0x000000:
			return 0;
		case 0xFF0000:
			return 1;
		case 0xFFFF00:
			return 2;
		case 0x00FF00:
			return 3;
		case 0x0000FF:
			return 4;
		case 0xFFFFFF:
			return 5;
		}
		return 0;
	}

	private int getLineColorFromSpinner(int pos) {
		switch (pos) {
		case 0:
			return 0x000000;
		case 1:
			return 0xFF0000;
		case 2:
			return 0xFFFF00;
		case 3:
			return 0x00FF00;
		case 4:
			return 0x0000FF;
		case 5:
			return 0xFFFFFF;
		}
		return 0x000000;
	}

	@SuppressLint("InflateParams")
	private void createPopupWindow() {
		View menuView = mInflater.inflate(R.layout.annot_text_markup_setting, null);

		mPopupWindow = new PopupWindow(menuView, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		mPopupWindow.setOutsideTouchable(true);
		mPopupWindow.setBackgroundDrawable(new BitmapDrawable(mContext
				.getResources()));

		final AnnotSetting setting = AnnotSetting.instance();
		// line color
		mLineColorSpinner = (Spinner) menuView
				.findViewById(R.id.annot_line_color_value);
		mLineColorSpinner.setSelection(getSpinnerPos(setting.getLineColor()));
		mLineColorSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						setting.setLineColor(getLineColorFromSpinner(position), mAnnotType);
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});

		// line squiggly
		mSquiggly = (CheckBox) menuView
				.findViewById(R.id.annot_line_squiggly);
		mSquiggly.setChecked(setting.getLineStraight());
		mSquiggly.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				mSquiggly.setChecked(isChecked);
				setting.setSquiggly(isChecked);
			}
		});
	}

	public synchronized void show(final View anchor, final int x, final int y) {
		if (mPopupWindow == null) {
			createPopupWindow();
		}

		mPopupWindow.showAtLocation(anchor, Gravity.CENTER, x, y);
	}
	
	public synchronized void show(final View anchor, final int x, final int y, final AnnotToolType type) {
		if (mPopupWindow == null) {
			createPopupWindow();
		}
		
		mAnnotType = type;
		AnnotSetting setting = AnnotSetting.instance();
		
		mLineColorSpinner.setSelection(getSpinnerPos(setting.getLineColor(type)));
		if (type == AnnotToolType.UNDERLINE) {
			mSquiggly.setVisibility(View.VISIBLE);
			mSquiggly.setChecked(setting.isSquiggly());
		} else {
			mSquiggly.setVisibility(View.GONE);
		}
		
		
		mPopupWindow.showAtLocation(anchor, Gravity.CENTER, x, y);
	}

	public synchronized void close() {
		if (mPopupWindow != null) {
			mPopupWindow.dismiss();
		}
	}

	public void setAnnotType(AnnotToolType annotType) {
		this.mAnnotType = annotType;
	}
}
