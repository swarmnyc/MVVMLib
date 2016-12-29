package com.swarmnyc.mvvmlib.sampleapp.ui;

import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.BindingViewModel;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.RecyclerViewModel;
import com.swarmnyc.mvvmlib.support.MvvmActivity;

/**
 * Created by Tao on 12/29/16.
 */
public class RecyclerViewActivity extends MvvmActivity<RecyclerViewModel> {
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_recycler_view;
    }
}
