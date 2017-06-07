package com.swarmnyc.mvvmlib.sampleapp.ui.fragment;

import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.HomeViewModel;
import com.swarmnyc.mvvmlib.support.MvvmFragment;

/**
 * Created by somya on 5/31/17.
 */

public class FragmentHome extends MvvmFragment<HomeViewModel>
{
	@Override
	protected int getLayoutResourceId()
	{
		return R.layout.fragment_home;
	}


}
