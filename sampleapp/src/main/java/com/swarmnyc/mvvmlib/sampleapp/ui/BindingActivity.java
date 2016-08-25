package com.swarmnyc.mvvmlib.sampleapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.BindingViewModel;
import com.swarmnyc.mvvmlib.support.MvvmActivity;

public class BindingActivity extends MvvmActivity<BindingViewModel> {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_binding;
    }
}
