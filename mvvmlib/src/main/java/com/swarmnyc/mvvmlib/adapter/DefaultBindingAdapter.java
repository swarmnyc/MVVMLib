package com.swarmnyc.mvvmlib.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import com.swarmnyc.mvvmlib.MvvmListViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tao on 9/16/16.
 */
public class DefaultBindingAdapter extends BaseBindingAdapter
{
	@LayoutRes private final int layoutId;

	public DefaultBindingAdapter(
		Context context,
		MvvmListViewModel listViewModel,
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
