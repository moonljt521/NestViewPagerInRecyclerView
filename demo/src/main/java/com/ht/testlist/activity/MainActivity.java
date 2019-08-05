package com.ht.testlist.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.ht.testlist.adapter.MainAdapter;
import com.ht.testlist.fragment.PagerFragment;
import com.ht.testlist.R;
import com.ht.testlist.weight.OutRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 2018/8/22 0022
 * My email : logisong@163.com
 * The role of this :
 */
public class MainActivity extends AppCompatActivity implements MainAdapter.PagerChangeListener {

    private List<String> data = new ArrayList<>();
    private List<PagerFragment> fragments = new ArrayList<>();
    public boolean innerCanScroll = true;
    private PagerFragment currentFragment;

    private float downX;    //按下时 的X坐标
    private float downY;    //按下时 的Y坐标
//    public OutRecyclerView recyclerView;
    public RecyclerView recyclerView;
    private DelegateAdapter delegateAdapter;
    public boolean isStick = false;
    private RelativeLayout rootView;
    private int yCriticalPoint;
    public static int rvYPosition = -10000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data.add("tab1");
        data.add("tab2");
        data.add("tab3");
        data.add("tab4");
        recyclerView =  findViewById(R.id.rv);
        rootView = findViewById(R.id.rl_root);

        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(virtualLayoutManager);
        delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        recyclerView.setAdapter(delegateAdapter);
        recyclerView.setNestedScrollingEnabled(true);
        //状态栏高度
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        //屏幕高度
        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        for (int i = 0; i < data.size(); i++) {
            fragments.add(PagerFragment.newInstance(data.get(i)));
        }
        final float scale = dm.density;
        int i = (int) (54 * scale + 0.5f);
        yCriticalPoint = statusBarHeight + i;
        currentFragment = fragments.get(0);
        MainAdapter mainAdapter = new MainAdapter(this, getSupportFragmentManager(), data, fragments, dm.heightPixels - statusBarHeight - i);
        delegateAdapter.addAdapter(mainAdapter);
        mainAdapter.setPagerChangeListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        adjustIntercept(true);
    }

    public void adjustIntercept(boolean b) {
//        recyclerView.setNeedIntercept(b);
    }

    @Override
    public void pagerChange(int position) {
        currentFragment = fragments.get(position);
    }


}
