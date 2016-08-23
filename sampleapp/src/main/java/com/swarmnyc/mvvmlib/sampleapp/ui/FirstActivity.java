package com.swarmnyc.mvvmlib.sampleapp.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.swarmnyc.mvvmlib.Keys;
import com.swarmnyc.mvvmlib.MvvmAppCompatActivity;
import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.FirstViewModel;

public class FirstActivity extends MvvmAppCompatActivity<FirstViewModel> {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_first;
    }
}
