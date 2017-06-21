package com.swarmnyc.mvvmlib.sampleapp.ui.fragment;

import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.SimpleListViewModel;
import com.swarmnyc.mvvmlib.support.MvvmFragment;

/**
 * Created by somya on 5/31/17.
 */

public class FragmentSimpleList extends MvvmFragment<SimpleListViewModel>
{
	@Override
	protected int getLayoutResourceId()
	{
		return R.layout.fragment_simple_list;
	}


}
