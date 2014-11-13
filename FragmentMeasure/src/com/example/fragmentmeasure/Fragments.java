package com.example.fragmentmeasure;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragments extends Fragment {
    private final static String strText = "TEXT";

    public static Fragments newInstance(CharSequence pageText) {
        Fragments fragments = new Fragments();
        Bundle bundle = new Bundle();
        bundle.putCharSequence(strText, pageText);
        fragments.setArguments(bundle);
        return fragments;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CharSequence text = getArguments().getCharSequence(strText);
        TextView pageView = (TextView) inflater.inflate(R.layout.page, container, false);
        pageView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size));
        pageView.setText(text);
        return pageView;
    }
}