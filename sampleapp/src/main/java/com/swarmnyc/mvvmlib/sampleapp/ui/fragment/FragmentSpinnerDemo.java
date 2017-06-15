package com.swarmnyc.mvvmlib.sampleapp.ui.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.swarmnyc.mvvmlib.adapter.MultiViewDataBindingAdapter;
import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.databinding.SimpleListViewBinding;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.ImageListItemViewModel;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.SpinnerDemoViewModel;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.TextListItemViewModel;
import com.swarmnyc.mvvmlib.support.MvvmFragment;

import java.util.HashMap;

/**
 * Created by somya on 5/31/17.
 */

public class FragmentSpinnerDemo extends MvvmFragment<SpinnerDemoViewModel>
{
	@Override
	protected int getLayoutResourceId()
	{
		return R.layout.fragment_spinner_demo;
	}

	@Override
	public void onViewCreated(
		final View view,
		@Nullable
		final Bundle savedInstanceState
	)
	{
		super.onViewCreated( view, savedInstanceState );

		final ViewDataBinding viewDataBinding = getViewDataBinding();


	}
}
