package com.swarmnyc.mvvmlib.adapter;

import android.content.Context;
import android.databinding.ObservableList;
import android.support.annotation.LayoutRes;

/**
 * Created by Tao on 9/16/16.
 */
public class DefaultBindingAdapter extends BaseBindingAdapter
{
	@LayoutRes private final int layoutId;

	public DefaultBindingAdapter(
		Context context,
		ObservableList list,
		@LayoutRes
			int layoutId
	)
	{
		super( context, list );

		this.layoutId = layoutId;
	}


	@Override
	protected int getLayoutId( final int viewType )
	{
		return layoutId;
	}


}
