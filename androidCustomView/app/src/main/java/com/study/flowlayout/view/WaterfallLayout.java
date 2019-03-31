package com.study.flowlayout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;


public class WaterfallLayout extends ViewGroup {


    private static final String TAG = WaterfallLayout.class.getSimpleName();


    private int mColumns = 3;

    private int  mVervitcalSpacel = 20;

    private int mHorizontalSpace = 20;

    private int mChildWidth = 0;

    private int mTop[];


    public WaterfallLayout(Context context) {
        this(context,null);
    }

    public WaterfallLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WaterfallLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTop = new int[mColumns];
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //得到总宽度
        int measureWidth = 0;
        int measureHeight = 0;

        if (widthMode== MeasureSpec.EXACTLY){
            measureWidth = widthSize;
            measureHeight = heightSize;
        }else{
            measureChildren(widthMeasureSpec,heightMeasureSpec);
            //得到单个Item的宽度
            mChildWidth = (widthSize - (mColumns - 1) * mHorizontalSpace) / mColumns;
            int childCount = getChildCount();
            if (childCount<mColumns){
                measureWidth = childCount * mChildWidth + (childCount - 1) * mHorizontalSpace;
            }else{
                measureWidth = widthSize;
            }

            clearTop();

            for (int i = 0;i<childCount;i++){
                View child = this.getChildAt(i);
                int childHeight = child.getMeasuredHeight()*mChildWidth/child.getMeasuredWidth();
                int minColum = getMinHeightColum();
                WaterfalllayoutParams lParams = (WaterfalllayoutParams) child.getLayoutParams();
                lParams.left = minColum*(mChildWidth+mHorizontalSpace);
                lParams.top = mTop[minColum];
                lParams.right = lParams.left + mChildWidth;
                lParams.bottom = lParams.top + childHeight;
                mTop[minColum] += mVervitcalSpacel + childHeight;
            }
            measureHeight = getMaxHeight();
        }
        setMeasuredDimension(measureWidth,measureHeight);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        clearTop();

        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);
            WaterfalllayoutParams lParams = (WaterfalllayoutParams)child.getLayoutParams();
            child.layout(lParams.left, lParams.top, lParams.right, lParams.bottom);
        }
    }


    private void clearTop() {
        for (int i = 0; i < mColumns; i++) {
            mTop[i] = 0;
        }
    }

    private int getMinHeightColum() {
        int minColum = 0;
        for (int i = 0; i < mColumns; i++) {
            if (mTop[i] < mTop[minColum]) {
                minColum = i;
            }
        }
        return minColum;
    }

    private int getMaxHeight() {
        int maxHeight = 0;
        for (int i = 0; i < mColumns; i++) {
            if (mTop[i] > maxHeight) {
                maxHeight = mTop[i];
            }
        }
        return maxHeight;
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new WaterfalllayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new WaterfalllayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new WaterfalllayoutParams(WaterfalllayoutParams.WRAP_CONTENT,WaterfalllayoutParams.WRAP_CONTENT);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof WaterfalllayoutParams;
    }

    public static class WaterfalllayoutParams extends ViewGroup.LayoutParams{


        public int left= 0;

        public int top = 0;

        public int right = 0;

        public int bottom = 0;


        public WaterfalllayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public WaterfalllayoutParams(int width, int height) {
            super(width, height);
        }

        public WaterfalllayoutParams(LayoutParams source) {
            super(source);
        }
    }
}
