package com.swarmnyc.mvvmlib.sampleapp.ui;

import com.swarmnyc.mvvmlib.support.MvvmActivity;
import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.FirstViewModel;

public class FirstActivity extends MvvmActivity<FirstViewModel> {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.layout_first;
    }
}
