package com.ht.testlist.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ht.testlist.R;
import com.ht.testlist.adapter.PagerListAdapter;
import com.ht.testlist.recyclerview.InnerNestedRecyclerView;
import com.ht.testlist.utils.Util;

import java.util.ArrayList;
import java.util.List;


public class ItemFragment extends Fragment {
//       implements InnerNestedRecyclerView.NeedIntercepectListener {

    private InnerNestedRecyclerView recyclerView;
    private float downX;    //按下时 的X坐标
    private float downY;    //按下时 的Y坐标
    private String title;
    private int height;
    private Activity mActivity;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    public static ItemFragment newInstance(int position, String title) {
        ItemFragment pagerFragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        pagerFragment.setArguments(args);
        return pagerFragment;
    }

    List<String> list = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_pager, null);
        recyclerView = (InnerNestedRecyclerView) view.findViewById(R.id.inner_rv);
        recyclerView.setNestedScrollingEnabled(true);
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        //屏幕高度
        DisplayMetrics dm = mActivity.getApplicationContext().getResources().getDisplayMetrics();
        final float scale = dm.density;
        int i = (int) (54 * scale + 0.5f);
        height = statusBarHeight + i + Util.dp2px(100);
        height = statusBarHeight + i;
        recyclerView.setMaxY(height);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final PagerListAdapter adapter = new PagerListAdapter(title);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (beginLoadMore) return;

                beginLoadMore = true;

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                int lastPos = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                Log.i("moon", "lastPos = " + lastPos);
                if (adapter.getItemCount() - 1 == lastPos) {
                    Log.i("moon", "底部。。。");
                    adapter.addData(getData());

                }
                beginLoadMore = false;
            }
        });

        adapter.addData(getData());

//        recyclerView.setNeedIntercepectListener(this);

//        // TODO: 2019/8/5
//        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//
//            float y1,y2,x2,x1;
//
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                if (e.getAction() == MotionEvent.ACTION_DOWN) {
//                    x1 = e.getX();
//                    y1 = e.getY();
//                }
//                if (e.getAction() == MotionEvent.ACTION_UP) {
//                    x2 = e.getX();
//                    y2 = e.getY();
//                    if (Math.abs(x1 - x2) < 6) {
//                        return false;// 距离较小，当作click事件来处理
//                    }
//                    if(Math.abs(x1 - x2) >60){  // 真正的onTouch事件
//                        return true;
//                    }
//                }
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });


        return view;
    }

    private boolean beginLoadMore = false;

    private List<String> getData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            String x = " body ";
            list.add(x);
        }
        return list;
    }

//    @Override
//    public void needIntercepect(boolean needIntercepect) {
//        ((MainActivity) mActivity).adjustIntercept(!needIntercepect);
//    }
}
