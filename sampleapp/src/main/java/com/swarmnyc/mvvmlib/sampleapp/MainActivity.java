package com.swarmnyc.mvvmlib.sampleapp;

import android.support.annotation.LayoutRes;

import com.swarmnyc.mvvmlib.support.MvvmActivity;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.MainViewModel;

public class MainActivity extends MvvmActivity<MainViewModel> {
    @Override
    @LayoutRes
    public int getLayoutResourceId() {
        return R.layout.activity_main;
    }
}
