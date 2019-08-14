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
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.ht.testlist.R;
import com.ht.testlist.adapter.PagerListAdapter;
import com.ht.testlist.recyclerview.InnerNestedRecyclerView;
import com.ht.testlist.utils.Util;


public class PagerFragment extends Fragment  {
//       implements InnerNestedRecyclerView.NeedIntercepectListener {

    private InnerNestedRecyclerView mRv;
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

    public static PagerFragment newInstance(String title) {
        PagerFragment pagerFragment = new PagerFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        pagerFragment.setArguments(args);
        return pagerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_pager, null);
        mRv = (InnerNestedRecyclerView) view.findViewById(R.id.inner_rv);
        mRv.setNestedScrollingEnabled(true);
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
        height = statusBarHeight + i ;
        mRv.setMaxY(height);
//        mRv.setNeedIntercepectListener(this);

//        // TODO: 2019/8/5
//        mRv.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
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

        initView();
        return view;
    }

    private void initView() {
        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        PagerListAdapter adapter = new PagerListAdapter(title);
        mRv.setAdapter(adapter);
    }

//    @Override
//    public void needIntercepect(boolean needIntercepect) {
//        ((MainActivity) mActivity).adjustIntercept(!needIntercepect);
//    }
}
