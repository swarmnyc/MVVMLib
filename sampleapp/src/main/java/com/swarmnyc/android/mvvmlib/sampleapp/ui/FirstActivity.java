package com.swarmnyc.android.mvvmlib.sampleapp.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.swarmnyc.android.mvvmlib.Keys;
import com.swarmnyc.android.mvvmlib.MvvmAppCompatActivity;
import com.swarmnyc.android.mvvmlib.sampleapp.R;
import com.swarmnyc.android.mvvmlib.sampleapp.viewmodel.FirstViewModel;

public class FirstActivity extends MvvmAppCompatActivity<FirstViewModel> {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_first;
    }
}
