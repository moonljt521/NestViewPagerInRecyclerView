package com.ht.testlist.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ht.testlist.R;


public class PagerListAdapter extends RecyclerView.Adapter {
    private String title;

    public PagerListAdapter(String title) {
        this.title = title;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_normal,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final TextView tv = holder.itemView.findViewById(R.id.tv);
        tv.setText(title + "_" + position );
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    int lastX,lastY;
    float x1,x2,y1,y2;

    private class ViewHolder extends RecyclerView.ViewHolder {
        @SuppressLint("ClickableViewAccessibility")
        public ViewHolder(final View view) {
            super(view);
            TextView textView = view.findViewById(R.id.tv);
            textView.setBackgroundColor(Color.RED);

            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int action = event.getAction();
                    switch (action) {
                        case MotionEvent.ACTION_DOWN:
                            lastX = (int) event.getRawX();
                            lastY = (int) event.getRawY();
                            x1 =  event.getRawX();
                            y1 =  event.getRawY();
                            break;
                        case MotionEvent.ACTION_MOVE:

                            lastX = (int) event.getRawX();
                            lastY = (int) event.getRawY();
                            break;
                        case MotionEvent.ACTION_UP:
                            x2 = event.getRawX();
                            y2 = event.getRawY();
                            double distance = Math.sqrt(Math.abs(x1-x2)*Math.abs(x1-x2)+Math.abs(y1-y2)*Math.abs(y1-y2));//两点之间的距离
                            Log.i("moon",">>>>>>>"+distance +"");
                            if (distance == 0) { // 距离较小，当作click事件来处理
                                Toast.makeText(view.getContext(),title + "_" + getLayoutPosition()  , Toast.LENGTH_LONG) .show();
                                return false;
                            } else {
                                return true ;
                            }
                    }
                    return true;
                }
            });
        }
    }
}
