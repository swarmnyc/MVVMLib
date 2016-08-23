package com.swarmnyc.mvvmlib.sampleapp.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.swarmnyc.mvvmlib.MvvmAppCompatActivity;
import com.swarmnyc.mvvmlib.navigation.NavigationManager;
import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.ThirdViewModel;

public class ThirdActivity extends MvvmAppCompatActivity<ThirdViewModel> {
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_third;
    }
}
