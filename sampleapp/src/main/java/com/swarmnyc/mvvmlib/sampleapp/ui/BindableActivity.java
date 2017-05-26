package com.swarmnyc.mvvmlib.sampleapp.ui;

import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.BindableViewViewModel;
import com.swarmnyc.mvvmlib.support.MvvmActivity;

public class BindableActivity extends MvvmActivity<BindableViewViewModel> {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_bindable_view;
    }
}
