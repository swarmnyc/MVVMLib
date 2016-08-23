package com.swarmnyc.android.mvvmlib.sampleapp;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import com.swarmnyc.android.mvvmlib.MvvmAppCompatActivity;
import com.swarmnyc.android.mvvmlib.navigation.NavigationManager;
import com.swarmnyc.android.mvvmlib.sampleapp.viewmodel.MainViewModel;

public class MainActivity extends MvvmAppCompatActivity<MainViewModel> {

    @Override
    public ViewDataBinding onCreateViewBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public void buildNavigation(NavigationManager manager) {

    }
}
