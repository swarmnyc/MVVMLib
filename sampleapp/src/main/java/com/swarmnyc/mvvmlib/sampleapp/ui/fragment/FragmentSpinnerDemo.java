package com.swarmnyc.mvvmlib.sampleapp.ui.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.SpinnerDemoViewModel;
import com.swarmnyc.mvvmlib.support.MvvmFragment;

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


}
