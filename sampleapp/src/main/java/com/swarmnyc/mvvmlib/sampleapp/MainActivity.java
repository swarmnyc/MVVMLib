package com.swarmnyc.mvvmlib.sampleapp;

import android.support.annotation.LayoutRes;

import com.swarmnyc.mvvmlib.sampleapp.viewmodel.MainViewModel;
import com.swarmnyc.mvvmlib.support.MvvmActivity;

public class MainActivity extends MvvmActivity<MainViewModel> {
    @Override
    @LayoutRes
    public int getLayoutResourceId() {
        return R.layout.activity_main;
    }
}
