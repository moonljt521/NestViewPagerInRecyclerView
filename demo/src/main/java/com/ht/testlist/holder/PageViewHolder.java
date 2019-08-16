package com.ht.testlist.holder;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.ht.testlist.R;
import com.ht.testlist.adapter.MainAdapter;
import com.ht.testlist.widget.WrapContentHeightViewPager;

/**
 * Created by Administrator on 2018/8/29.
 * Description : PageViewHolder
 */
public class PageViewHolder extends RecyclerView.ViewHolder {

//    public ViewPager mViewPager;
    public WrapContentHeightViewPager mViewPager;
    public LinearLayout rlVpContainer;
    public TabLayout tabLayout;


    public void setPagerChangeListener(MainAdapter.PagerChangeListener pagerChangeListener) {
        this.pagerChangeListener = pagerChangeListener;
    }

    public MainAdapter.PagerChangeListener getPagerChangeListener() {
        return pagerChangeListener;
    }

    private MainAdapter.PagerChangeListener pagerChangeListener;

    public PageViewHolder(View view) {
        super(view);
        mViewPager = view.findViewById(R.id.viewpager);
        rlVpContainer = view.findViewById(R.id.rl_vp_container);
        tabLayout = view.findViewById(R.id.tablayout);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                mViewPager.setAnimStarted(false);
                mViewPager.requestLayout();

                if (pagerChangeListener != null) {
                    pagerChangeListener.pagerChange(position);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}