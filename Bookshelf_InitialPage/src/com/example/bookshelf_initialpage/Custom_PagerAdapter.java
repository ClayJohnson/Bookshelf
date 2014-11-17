package com.example.bookshelf_initialpage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Hashtable;
import java.util.List;

/**
 * COMMENT DESCRIPTION OF CLASS TO BE FILLED IN
 * 
 * @author Anjana Chatta
 * 
 */
public class Custom_PagerAdapter extends FragmentPagerAdapter {

	private final List<CharSequence> pageTexts;

	Hashtable<Integer, Fragment> mPageReferenceMap = new Hashtable<Integer, Fragment>();

	/**
	 * Constructor
	 * 
	 * @param fm
	 * @param pageTexts
	 */
	public Custom_PagerAdapter(FragmentManager fm, List<CharSequence> pageTexts) {
		super(fm);
		this.pageTexts = pageTexts;

	}

	/**
	 * new
	 * 
	 * @param key
	 * @return
	 */
	public Fragment getFragment(int key) {

		return mPageReferenceMap.get(key);

	}

	@Override
	public Fragment getItem(int i) {
		// return Fragments.newInstance(pageTexts.get(i));

		Fragment frag = Fragments.newInstance(pageTexts.get(i));
		mPageReferenceMap.put(i, frag);
		return frag;
	}

	@Override
	public int getCount() {
		return pageTexts.size();
	}

}