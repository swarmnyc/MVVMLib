package com.swarmnyc.mvvmlib.sampleapp.ui;

import android.os.Bundle;
import android.view.View;

import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.databinding.ActivityBindingBinding;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.BindingViewModel;
import com.swarmnyc.mvvmlib.support.MvvmActivity;

public class BindingActivity extends MvvmActivity<BindingViewModel> {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_binding;
    }

    @Override
    protected void onInit(BindingViewModel viewModel, Bundle args) {
        ActivityBindingBinding viewDataBinding = getViewDataBinding();
        //do some thing
        View view = viewDataBinding.getRoot();
    }
}
