package com.swarmnyc.mvvmlib.sampleapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.swarmnyc.mvvmlib.adapter.MultiViewDataBindingAdapter;
import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.databinding.SimpleListViewBinding;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.ImageListItemViewModel;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.TextListItemViewModel;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.TextListViewModel;
import com.swarmnyc.mvvmlib.support.MvvmFragment;

import java.util.HashMap;

/**
 * Created by somya on 5/31/17.
 */

public class FragmentSimpleList extends MvvmFragment<TextListViewModel>
{
	@Override
	protected int getLayoutResourceId()
	{
		return R.layout.fragment_text_list;
	}

	@Override
	public void onViewCreated(
		final View view,
		@Nullable
		final Bundle savedInstanceState
	)
	{
		super.onViewCreated( view, savedInstanceState );

		final SimpleListViewBinding viewDataBinding = getViewDataBinding();

		final LinearLayoutManager layout = new LinearLayoutManager( getContext(), LinearLayoutManager.VERTICAL,
		                                                            false );
		viewDataBinding.recycleView.setLayoutManager(layout);

		final HashMap<Class, Integer> viewModelLayoutMap = new HashMap<>();
		viewModelLayoutMap.put( TextListItemViewModel.class, R.layout.view_text_list_item );
		viewModelLayoutMap.put( ImageListItemViewModel.class, R.layout.view_image_list_item );

		final MultiViewDataBindingAdapter adapter = new MultiViewDataBindingAdapter(
			getContext(),
			getViewModel(),
			viewModelLayoutMap
		);

		viewDataBinding.recycleView.setAdapter( adapter );
	}
}
