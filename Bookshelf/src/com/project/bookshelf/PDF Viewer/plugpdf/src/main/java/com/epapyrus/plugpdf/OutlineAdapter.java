package com.epapyrus.plugpdf;

import com.epapyrus.plugpdf.core.OutlineItem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class OutlineAdapter extends BaseAdapter {
	private final OutlineItem mItems[];
	private final LayoutInflater mInflater;

	private final int MAX_DEPS = 8;

	public OutlineAdapter(LayoutInflater inflater, OutlineItem items[]) {
		mInflater = inflater;
		mItems = items;
	}

	public int getCount() {
		return mItems.length;
	}

	public Object getItem(int arg0) {
		return mItems[arg0];
	}

	public long getItemId(int arg0) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v;
		if (convertView == null) {
			v = mInflater.inflate(R.layout.panel_outline_item, null);
		} else {
			v = convertView;
		}

		int deps = mItems[position].getDeps();
		if (deps > MAX_DEPS) {
			deps = MAX_DEPS;
		}

		String space = "";
		for (int i = 0; i < deps; i++) {
			space += "   ";
		}

		String title = space + mItems[position].getTitle();
		String pageNumber = String.valueOf(mItems[position].getPageIdx() + 1);

		((TextView)v.findViewById(R.id.title)).setText(title);
		((TextView)v.findViewById(R.id.page)).setText(pageNumber);
		
		
		/*
		((TextView) v.findViewById(v.getResources().getIdentifier("title",
				"id", v.getContext().getPackageName()))).setText(title);

		((TextView) v.findViewById(v.getResources().getIdentifier("page", "id",
				v.getContext().getPackageName()))).setText(pageNumber);
				*/

		return v;
	}
}
