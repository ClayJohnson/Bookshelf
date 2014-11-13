/****************************************************************************
 **
 ** Copyright (C) 2013 ePapyrus, Inc.
 ** All rights reserved.
 **
 ** This file is part of PlugPDF for Android project.
 **
 ****************************************************************************/

package com.epapyrus.plugpdf;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.epapyrus.plugpdf.core.OutlineItem;
import com.epapyrus.plugpdf.core.PlugPDFUtility;
import com.epapyrus.plugpdf.core.BaseReaderControl;

/*
 * Class of the sub-menu that is activated in the menu when selecting that exist
 * in SimpleReaderControlView.
 * 
 * It works the function corresponding to the menu where you can view the
 * sub-menu in response to a higher level menu, you have selected.
 */
public class SimpleReaderControlPanel {

	private SimpleReaderControlView mParent;
	private BaseReaderControl mController;

	private LayoutInflater mLayoutInflater;
	private Context mContext;

	private View mPageDisplayPanel;
	private View mBrightnessPanel;
	private View mOutlinePanel;

	private PopupWindow mPopupPanel;

	// panel type
	public enum PanelType {
		DISPLAYMODE, BRIGHTNESS, OUTLINE
	}

	public SimpleReaderControlPanel(Context context,
			SimpleReaderControlView parent, BaseReaderControl controller) {
		mContext = context;
		mParent = parent;

		mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		mController = controller;

		createPageDisplayPanel();
		createBrightnessPanel();
		createOutlinePanel();
	}

	private void createOutlinePanel() {
		mOutlinePanel = mLayoutInflater.inflate(R.layout.panel_outline, null);

		ListView outlineList = (ListView) mOutlinePanel
				.findViewById(R.id.panel_outline_list);

		outlineList.setAdapter(null);
		outlineList
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						OutlineItem item = (OutlineItem) parent.getAdapter()
								.getItem(position);
						mController.goToPage(item.getPageIdx());
						hide();
					}
				});
	}

	private void createPageDisplayPanel() {
		mPageDisplayPanel = mLayoutInflater.inflate(R.layout.panel_doc_flow,
				null);

		Button displayHorizontalButton = (Button) mPageDisplayPanel
				.findViewById(R.id.panel_display_horizontal);
		displayHorizontalButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mParent.setHorizontalMode();
				hide();
			}
		});

		Button displayVerticalButton = (Button) mPageDisplayPanel
				.findViewById(R.id.panel_display_vertical);
		displayVerticalButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mParent.setVerticalMode();
				hide();
			}
		});

		Button displayBilateralButton = (Button) mPageDisplayPanel
				.findViewById(R.id.panel_display_bilateral);
		displayBilateralButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mParent.setBilateralMode();
				hide();
			}
		});

		Button displayThumbnailButton = (Button) mPageDisplayPanel
				.findViewById(R.id.panel_display_thumbnail);
		displayThumbnailButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mParent.setThumbnailMode();
				hide();
			}
		});
	}

	private void createBrightnessPanel() {
		mBrightnessPanel = mLayoutInflater.inflate(R.layout.panel_brightness,
				null);

		SeekBar brightnessSeekBar = (SeekBar) mBrightnessPanel
				.findViewById(R.id.panel_brightness_bar);

		final int minBright = 20;

		brightnessSeekBar
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						float brightness = (float) (progress + minBright) * 0.01f;
						PlugPDFUtility.setDisplayBrightness(
								mParent.mAct.getWindow(), brightness);
					}
				});

		PlugPDFUtility.setDisplayBrightness(mParent.mAct.getWindow(),
				brightnessSeekBar.getProgress() + minBright * (float) 0.01f);
	}

	// It shows anchor point specified the Panel corresponding to PanelType.
	public void show(PanelType panelType, View anchor) {

		View panel = null;
		int offsetW = 0;

		switch (panelType) {
		case DISPLAYMODE:
			panel = mPageDisplayPanel;
			offsetW = (int) TypedValue.applyDimension(
					TypedValue.COMPLEX_UNIT_DIP, 17, mContext.getResources()
							.getDisplayMetrics());

			break;
		case BRIGHTNESS:
			panel = mBrightnessPanel;
			offsetW = (int) TypedValue.applyDimension(
					TypedValue.COMPLEX_UNIT_DIP, 45, mContext.getResources()
							.getDisplayMetrics());
			break;
		case OUTLINE:
			panel = mOutlinePanel;
			OutlineAdapter adapter = new OutlineAdapter(mLayoutInflater,
					mController.getOutlineItem());
			ListView outlineList = (ListView) mOutlinePanel
					.findViewById(R.id.panel_outline_list);
			outlineList.setAdapter(adapter);
			break;
		default:
			break;
		}

		mPopupPanel = new PopupWindow(panel, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, true);
		mPopupPanel.setAnimationStyle(android.R.style.Animation_Dialog);
		mPopupPanel.setOutsideTouchable(true);
		mPopupPanel.setBackgroundDrawable(new BitmapDrawable(mContext
				.getResources()));
		mPopupPanel.showAsDropDown(anchor, -offsetW, 0);
	}

	private void hide() {
		if (mPopupPanel != null) {
			mPopupPanel.dismiss();
			mPopupPanel = null;
		}
	}

	// refresh layout.
	public void refreshLayout() {
		hide();
	}
}
