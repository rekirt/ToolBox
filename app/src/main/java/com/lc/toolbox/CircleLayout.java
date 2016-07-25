package com.lc.toolbox;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Author: lc
 * Email: rekirt@163.com
 * Date: 16-3-27.
 *  圆形布局
 */
public class CircleLayout extends ViewGroup {

    private float radius = 300;//半径大小
    private int mDegress ;//度数
    private int offset = -45;//偏移量

    public CircleLayout(Context context) {
        super(context);
        init();
    }

    public CircleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
         */
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(sizeWidth, sizeHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        //获取子view个数
        final int count = getChildCount();
        //计算各个子view之间的角度差
        mDegress = 150/(count-1);
        final int parentLeft = getPaddingLeft();
        final int parentRight = right - left - getPaddingRight();
        final int parentTop = getPaddingTop();
        final int parentBottom = bottom - top - getPaddingBottom();
        if (count < 1 ) {
            return;
        }
        int widthoffset = (parentRight-parentLeft)/3;
        int heightoffset = (parentBottom-parentTop)/4;
        Log.e("test", "widthoffset===" + widthoffset+"heightoffset==="+heightoffset);
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                int width = child.getMeasuredWidth();
                int height = child.getMeasuredHeight();
                int childLeft;
                int childTop;

                if (i == 0) {
                    childLeft = parentLeft + (parentRight - parentLeft - width) / 2;
                    childTop = parentTop + (parentBottom - parentTop - height) / 2 ;
                    child.layout(childLeft+widthoffset, childTop+heightoffset,childLeft+width+widthoffset,childTop+height+heightoffset);
                    Log.e("tst===",+childLeft+"---"+width+"==="+childTop+"----"+height);
                }else{
                    childLeft = (int) (parentLeft + (parentRight - parentLeft-width) / 2-(radius * Math.sin((i*mDegress+offset)*Math.PI/130)));
                    childTop = (int) (parentTop + (parentBottom - parentTop-height) / 2-(radius * Math.cos((i*mDegress+offset)*Math.PI/130))) ;
                    child.layout(childLeft+widthoffset, childTop+heightoffset,childLeft+width+widthoffset,childTop+height+heightoffset);
                    Log.e("tst===",+childLeft+"---"+width+"==="+childTop+"----"+height);
                }
            }
        }
    }
}
