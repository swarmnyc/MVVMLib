package com.swarmnyc.mvvmlib.sampleapp;

import android.support.annotation.LayoutRes;
import android.util.Log;

import com.swarmnyc.mvvmlib.support.MvvmActivity;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.MainViewModel;

public class MainActivity extends MvvmActivity<MainViewModel> {
    @Override
    @LayoutRes
    public int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onResume()
    {
        Log.i(TAG, "onResume: ");
        super.onResume();
    }

}
