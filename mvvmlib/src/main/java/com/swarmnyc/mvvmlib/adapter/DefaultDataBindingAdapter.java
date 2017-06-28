package com.swarmnyc.mvvmlib.adapter;

import android.content.Context;
import android.databinding.ObservableList;
import android.support.annotation.LayoutRes;

/**
 * Created by Tao on 9/16/16.
 */
public class DefaultDataBindingAdapter extends BaseRecycleViewBindingAdapter
{
	@LayoutRes private final int layoutId;

	public DefaultDataBindingAdapter(
		Context context,
		ObservableList listViewModel,
		@LayoutRes
			int layoutId
	)
	{
		super( context, listViewModel );

		this.layoutId = layoutId;
	}


	@Override
	protected int getLayoutId( final int viewType )
	{
		return layoutId;
	}


}
