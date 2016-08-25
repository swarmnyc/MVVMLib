package com.swarmnyc.mvvmlib.sampleapp.ui;

import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.ThirdViewModel;
import com.swarmnyc.mvvmlib.support.MvvmFragment;

public class ThirdFragment extends MvvmFragment<ThirdViewModel> {
    @Override
    protected int getLayoutResourceId() {
        return R.layout.layout_third;
    }
}
