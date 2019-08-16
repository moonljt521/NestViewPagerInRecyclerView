package com.ht.testlist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.ht.testlist.R;
import com.ht.testlist.fragment.ItemFragment;
import com.ht.testlist.holder.PageViewHolder;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends DelegateAdapter.Adapter {

    private FragmentManager fragmentManager;
    private Context context;
    private int height;
    private PagerAdapter adapter;
    //记录上次展示的tab位置
    private int lastItem;
    private boolean isStick = false;
    private int statusBarHeight;
    private List<ItemFragment> fragments = new ArrayList<>();
    private PagerChangeListener pagerChangeListener;

    private List<String> data = new ArrayList<>();

    public MainAdapter(Context context, FragmentManager fragmentManager, int height) {
        this.height = height;
        this.fragmentManager = fragmentManager;

        data.add("tab1");
        data.add("tab2");
        data.add("tab3");
        data.add("tab4");

        for (int i = 0; i < data.size(); i++) {
            fragments.add(ItemFragment.newInstance(i,data.get(i)));
        }
    }

    private int TOP_COUNT = 20;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new MainViewHolder(View.inflate(parent.getContext(), R.layout.rv_item_normal, null));
        } else {
            return new PageViewHolder(View.inflate(parent.getContext(), R.layout.rv_item_pager, null));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        if (position < TOP_COUNT) {
            TextView tv = holder.itemView.findViewById(R.id.tv);
            tv.setText(MessageFormat.format("test{0}", position));
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(holder.itemView.getContext(),MessageFormat.format("test{0}", position),Toast.LENGTH_LONG).show();
                }
            });
        } else {
            PageViewHolder pageViewHolder = (PageViewHolder) holder;

            if (pageViewHolder.getPagerChangeListener() == null){
                pageViewHolder.setPagerChangeListener(pagerChangeListener);
            }

            if (adapter == null) {
                adapter = new MainPagerAdapter(fragmentManager, data, pageViewHolder.mViewPager ,fragments );
            }
            pageViewHolder.mViewPager.setAdapter(adapter);
            pageViewHolder.mViewPager.setOffscreenPageLimit(4);
            adapter.notifyDataSetChanged();
            pageViewHolder.tabLayout.setupWithViewPager(pageViewHolder.mViewPager);
            if (lastItem > 0) {
                pageViewHolder.mViewPager.setCurrentItem(lastItem);
            }
            //RecyclerView嵌套ViewPager会出现高度为0的bug,这里给ViewPager设置的高度为屏幕高度-状态栏高度
            ViewGroup.LayoutParams layoutParams = pageViewHolder.mViewPager.getLayoutParams();
            layoutParams.height = height;
            pageViewHolder.mViewPager.setLayoutParams(layoutParams);
        }

    }


    @Override
    public int getItemCount() {
        return TOP_COUNT + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < TOP_COUNT) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        public MainViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface PagerChangeListener {
        void pagerChange(int position);
    }

    public void setPagerChangeListener(PagerChangeListener pagerChangeListener) {
        this.pagerChangeListener = pagerChangeListener;
    }
}
