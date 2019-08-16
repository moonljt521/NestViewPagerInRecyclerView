package com.ht.testlist.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.ht.testlist.fragment.ItemFragment;
import com.ht.testlist.widget.WrapContentHeightViewPager;

import java.util.List;


public class MainPagerAdapter extends FragmentPagerAdapter {

    private List<String> data;
    private List<ItemFragment> fragments;

    private WrapContentHeightViewPager viewPager;

    MainPagerAdapter(FragmentManager fm, List<String> data, WrapContentHeightViewPager viewPager ,List<ItemFragment> fragments) {
        super(fm);
        this.data = data;
        this.fragments = fragments;
        this.viewPager = viewPager;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position);
    }
}
