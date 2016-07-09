package com.epitech.billy.omega_splicer.presentation.ui.managers;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * Created by Billy on 08/04/2016.
 */
public class ControlScrollingLinearLayoutManager extends LinearLayoutManager {

    private boolean mCanScrollVertically;
    private boolean mCanScrollHorizontally;


    public ControlScrollingLinearLayoutManager(Context context) {
        super(context);
    }

    public ControlScrollingLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public ControlScrollingLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setVerticalScrollEnable(boolean canScroll) {
        mCanScrollVertically = canScroll;
    }

    public void setHorizontalScrollEnable(boolean canScroll) {
        mCanScrollHorizontally = canScroll;
    }

    @Override
    public boolean canScrollHorizontally() {
        return mCanScrollHorizontally && super.canScrollHorizontally();
    }

    @Override
    public boolean canScrollVertically() {
        return mCanScrollVertically && super.canScrollVertically();
    }
}
