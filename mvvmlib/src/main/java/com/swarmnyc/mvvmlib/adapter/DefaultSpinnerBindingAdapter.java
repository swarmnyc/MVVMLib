package com.swarmnyc.mvvmlib.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import com.swarmnyc.mvvmlib.BR;

/**
 * Created by Tao on 9/16/16.
 */
public class DefaultSpinnerBindingAdapter extends BaseBindingAdapter implements SpinnerAdapter
{
	@LayoutRes private final int layoutId;
	@LayoutRes private final int itemLayoutId;

	public DefaultSpinnerBindingAdapter(
		Context context,
		ObservableList list,
		@LayoutRes
			int layoutId,
		@LayoutRes
			int itemLayoutId
	)
	{

		super( context, list );

		this.layoutId = layoutId;
		this.itemLayoutId = itemLayoutId;
	}


	@Override
	protected int getLayoutId( final int viewType )
	{
		return layoutId;
	}


	public View getDropDownView( int position, View convertView, ViewGroup parent )
	{
		ViewDataBinding vdb;
		View v = convertView;
		if (null == v)
		{
			vdb = DataBindingUtil.inflate( inflater, itemLayoutId, parent, false );
			v = vdb.getRoot();
			v.setTag( vdb );
		}

		vdb = (ViewDataBinding) v.getTag();
		vdb.setVariable( BR.viewmodel, getItem( position ) );

		return v;
	}

}
