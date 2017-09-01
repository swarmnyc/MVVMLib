package com.swarmnyc.mvvmlib.sampleapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.databinding.FragmentSimpleGridBinding;
import com.swarmnyc.mvvmlib.sampleapp.databinding.FragmentSimpleListBinding;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.SimpleGridViewModel;
import com.swarmnyc.mvvmlib.support.MvvmFragment;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

/**
 * Created by somya on 5/31/17.
 */

public class FragmentSimpleGrid extends MvvmFragment<SimpleGridViewModel>
{
	@Override
	protected int getLayoutResourceId()
	{
		return R.layout.fragment_simple_grid;
	}

	@Override
	public void onViewCreated(
		final View view,
		@Nullable
		final Bundle savedInstanceState
	)
	{
		super.onViewCreated( view, savedInstanceState );

		final FragmentSimpleGridBinding viewDataBinding = getViewDataBinding();

		viewDataBinding.recycleView.addItemDecoration( new HorizontalDividerItemDecoration.Builder( getContext() )
			                                               .color(
			R.color.colorAccent ).sizeResId( R.dimen.divider ).build() );
	}
}
