package com.swarmnyc.mvvmlib.sampleapp.ui;


import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.FirstViewModel;
import com.swarmnyc.mvvmlib.support.MvvmFragment;

public class FirstFragment extends MvvmFragment<FirstViewModel> {
    @Override
    protected int getLayoutResourceId() {
        return R.layout.layout_first;
    }
}
