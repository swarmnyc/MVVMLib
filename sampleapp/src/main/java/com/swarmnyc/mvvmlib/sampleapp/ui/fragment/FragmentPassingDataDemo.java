package com.swarmnyc.mvvmlib.sampleapp.ui.fragment;

import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.PassingDataDemoViewModel;
import com.swarmnyc.mvvmlib.support.MvvmFragment;

/**
 * Created by Tao on 7/6/17.
 */
public class FragmentPassingDataDemo extends MvvmFragment<PassingDataDemoViewModel> {
	@Override protected int getLayoutResourceId() {
		return R.layout.fragment_passing_data_demo;
	}
}
