package com.epapyrus.plugpdf;

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
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

import com.epapyrus.plugpdf.core.annotation.tool.AnnotSetting;

public class AnnotSettingMenu {
	private Context mContext;
	private LayoutInflater mInflater;
	private PopupWindow mPopupWindow;

	private static final String PX = "px";
	private static final String PERCENT = "%";

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

	public AnnotSettingMenu(Context context) {
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

	private void createPopupWindow() {
		View menuView = mInflater.inflate(R.layout.annot_setting, null);

		mPopupWindow = new PopupWindow(menuView, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		mPopupWindow.setOutsideTouchable(true);
		mPopupWindow.setBackgroundDrawable(new BitmapDrawable(mContext
				.getResources()));

		final AnnotSetting setting = AnnotSetting.instance();
		// line color
		Spinner lineColorSpinner = (Spinner) menuView
				.findViewById(R.id.annot_line_color_value);
		lineColorSpinner.setSelection(getSpinnerPos(setting.getLineColor()));
		lineColorSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						setting.setLineColor(getLineColorFromSpinner(position));
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});

		// line opacity
		final TextView lineOpacityValueDisplay = (TextView) menuView
				.findViewById(R.id.annot_line_opacity_value_display);
		lineOpacityValueDisplay.setText(Integer.toString((int) (AnnotSetting
				.instance().getLineOpacity() / 255.0f * 100)) + PERCENT);

		SeekBar lineOpacityValue = (SeekBar) menuView
				.findViewById(R.id.annot_line_opacity_value);
		lineOpacityValue.setProgress(setting.getLineOpacity());
		lineOpacityValue
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						lineOpacityValueDisplay.setText(Integer
								.toString((int) (progress / 255.0f * 100))
								+ PERCENT);
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
					}

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						setting.setLineOpacity(seekBar.getProgress());
						lineOpacityValueDisplay.setText(Integer
								.toString((int) (setting.getLineOpacity() / 255.0f * 100))
								+ PERCENT);
					}
				});

		// line width
		final TextView lineWidthValueDisplay = (TextView) menuView
				.findViewById(R.id.annot_line_width_value_display);
		lineWidthValueDisplay.setText(Integer.toString(setting.getLineWidth())
				+ PX);

		SeekBar lineWidthValue = (SeekBar) menuView
				.findViewById(R.id.annot_line_width_value);
		lineWidthValue.setProgress(setting.getLineWidth());
		lineWidthValue
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						lineWidthValueDisplay.setText(Integer
								.toString(progress) + PX);
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
					}

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						setting.setLineWidth(seekBar.getProgress());
						lineWidthValueDisplay.setText(Integer.toString(setting
								.getLineWidth()) + PX);
					}
				});

		// line straight
		final CheckBox straight = (CheckBox) menuView
				.findViewById(R.id.annot_line_straight);
		straight.setChecked(setting.getLineStraight());
		straight.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				straight.setChecked(isChecked);
				setting.setLineStraight(isChecked);
			}
		});
	}

	public synchronized void show(final View anchor, final int x, final int y) {
		if (mPopupWindow == null) {
			createPopupWindow();
		}

		mPopupWindow.showAtLocation(anchor, Gravity.CENTER, x, y);
	}

	public synchronized void close() {
		if (mPopupWindow != null) {
			mPopupWindow.dismiss();
		}
	}

}
