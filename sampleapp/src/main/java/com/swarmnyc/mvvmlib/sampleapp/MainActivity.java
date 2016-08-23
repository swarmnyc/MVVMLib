package com.swarmnyc.mvvmlib.sampleapp;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;

import com.swarmnyc.mvvmlib.MvvmAppCompatActivity;
import com.swarmnyc.mvvmlib.navigation.NavigationManager;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.MainViewModel;

public class MainActivity extends MvvmAppCompatActivity<MainViewModel> {
    @Override
    @LayoutRes
    public int getLayoutResourceId() {
        return R.layout.activity_main;
    }
}
