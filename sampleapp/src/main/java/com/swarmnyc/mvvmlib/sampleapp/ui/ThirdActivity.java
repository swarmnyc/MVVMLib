package com.swarmnyc.mvvmlib.sampleapp.ui;

import com.swarmnyc.mvvmlib.support.MvvmActivity;
import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.ThirdViewModel;

public class ThirdActivity extends MvvmActivity<ThirdViewModel> {
    @Override
    protected int getLayoutResourceId() {
        return R.layout.layout_third;
    }
}
