package com.swarmnyc.mvvmlib.sampleapp.ui.fragment;

import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.HelloViewModel;
import com.swarmnyc.mvvmlib.support.MvvmFragment;

/**
 * Created by somya on 5/31/17.
 */

public class FragmentHello extends MvvmFragment<HelloViewModel>
{
	@Override
	protected int getLayoutResourceId()
	{
		return R.layout.fragment_hello;
	}


}
