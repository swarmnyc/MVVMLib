package com.swarmnyc.mvvmlib.sampleapp.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.BindableViewSubViewModel;
import com.swarmnyc.mvvmlib.view.MvvmFrameLayout;

public class MyFrameLayout extends MvvmFrameLayout<BindableViewSubViewModel> {
    public MyFrameLayout(Context context) {
        super(context);
    }

    public MyFrameLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFrameLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.layout_my_frame;
    }
}
