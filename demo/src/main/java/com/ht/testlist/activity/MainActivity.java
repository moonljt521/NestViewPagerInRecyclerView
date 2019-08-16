package com.ht.testlist.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.ht.testlist.R;
import com.ht.testlist.adapter.MainAdapter;
import com.ht.testlist.fragment.ItemFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainAdapter.PagerChangeListener {



    private ItemFragment currentFragment;

    public RecyclerView recyclerView;
    private DelegateAdapter delegateAdapter;
    public boolean isStick = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView =  findViewById(R.id.main_rv);

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

        final float scale = dm.density;
        int i = (int) (54 * scale + 0.5f);
        int yCriticalPoint = statusBarHeight + i;
        MainAdapter mainAdapter = new MainAdapter(this, getSupportFragmentManager(), dm.heightPixels - statusBarHeight - i);
        delegateAdapter.addAdapter(mainAdapter);
        mainAdapter.setPagerChangeListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (Math.abs(dy) > 50){
//                    Log.i("moon", "外部 recyclerView dy = "+ dy);
                }
            }
        });
        adjustIntercept(true);
    }

    public void adjustIntercept(boolean b) {
//        recyclerView.setNeedIntercept(b);
    }

    @Override
    public void pagerChange(int position) {
//        currentFragment = fragments.get(position);
    }


}
