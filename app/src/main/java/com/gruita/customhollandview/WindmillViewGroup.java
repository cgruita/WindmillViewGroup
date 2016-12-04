package com.gruita.customhollandview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class WindmillViewGroup extends ViewGroup {
    public WindmillViewGroup(Context context) {
        super(context);
    }

    public WindmillViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WindmillViewGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {

//        Log.v(TAG, "onMeasure");
        measureChildren(widthSpec, heightSpec);
        View first = getChildAt(0);
        int size = first.getMeasuredWidth() + first.getMeasuredHeight();
        int width = ViewGroup.resolveSize(size, widthSpec);
        int height = ViewGroup.resolveSize(size, heightSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // We are assuming all children are of the same dimensions
        // Order: landscape, portrait, landscape, portrait

//        Log.v(TAG, "onLayout");

        View first = getChildAt(0);
        final int childWidth = first.getMeasuredWidth();
        final int childHeight = first.getMeasuredHeight();



        for (int i = 0; i < getChildCount(); ++i) {
//            Log.v(TAG, "    i: " + i);
            View child = getChildAt(i);
            int x = childHeight - childWidth;
            int y = 0;
            switch (i) {
                case 1:
                    x = childHeight;
                    y = childHeight - childWidth;
                    break;
                case 2:
                    x = childHeight;
                    y = childHeight;
                    break;
                case 3:
                    x = 0;
                    y = childHeight;
                    break;
            }
            int right = x + child.getMeasuredWidth();
            int bottom = y + child.getMeasuredHeight();
            child.layout(x, y, right, bottom);

//            Log.v(C.TAG, "    left|top|right|bottom: " + x + "|" + y + "|" + right + "|" + bottom);
        }
    }
}
