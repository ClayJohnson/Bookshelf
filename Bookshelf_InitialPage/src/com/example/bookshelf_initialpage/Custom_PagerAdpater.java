package com.example.bookshelf_initialpage;

/*Author Anjana Chatta*/

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Hashtable;
import java.util.List;

public class Custom_PagerAdpater extends FragmentPagerAdapter {

	private final List<CharSequence> pageTexts;

	Hashtable<Integer, Fragment> mPageReferenceMap = new Hashtable<Integer, Fragment>();

	public Custom_PagerAdpater(FragmentManager fm, List<CharSequence> pageTexts) {
		super(fm);
		this.pageTexts = pageTexts;

	}

	@Override
	public Fragment getItem(int i) {
		// return Fragments.newInstance(pageTexts.get(i));

		Fragment frag = Fragments.newInstance(pageTexts.get(i));
		mPageReferenceMap.put(i, frag);
		return frag;
	}

	// new
	public Fragment getFragment(int key) {

		return mPageReferenceMap.get(key);

	}

	//

	@Override
	public int getCount() {
		return pageTexts.size();
	}

}