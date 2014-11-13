package com.example.fragmentmeasure;
/*Author Anjana Chatta*/

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class Custom_PagerAdpater extends FragmentPagerAdapter {
    private final List<CharSequence> pageTexts;

    public Custom_PagerAdpater(FragmentManager fm, List<CharSequence> pageTexts) {
        super(fm);
        this.pageTexts = pageTexts;
    }

    @Override
    public Fragment getItem(int i) {
        return Fragments.newInstance(pageTexts.get(i));
    }

    @Override
    public int getCount() {
        return pageTexts.size();
    }
}