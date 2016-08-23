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
    public ViewDataBinding onCreateViewBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_first);
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Bundle data) {
        if (requestCode == 2) {
            getViewModel().getPassedData().set(data.getString(Keys.PASS_VALUE));
        }
    }
}
