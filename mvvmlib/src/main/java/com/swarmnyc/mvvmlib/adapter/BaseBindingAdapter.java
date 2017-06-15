package com.swarmnyc.mvvmlib.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.swarmnyc.mvvmlib.BR;
import com.swarmnyc.mvvmlib.MvvmListViewModel;

/**
 * Created by somya on 6/7/17.
 */

public abstract class BaseBindingAdapter extends android.widget.BaseAdapter
{
	private final LayoutInflater             inflater;
	private final ObservableList<Parcelable> viewModels;

	public BaseBindingAdapter(
		final Context context, final MvvmListViewModel viewModels
	)
	{
		this.inflater = LayoutInflater.from( context );
		this.viewModels = viewModels.getItemCollection();
	}

	@Override
	public int getCount()
	{
		return viewModels.size();
	}

	@Override
	public Object getItem( final int position )
	{
		return viewModels.get( position );
	}

	@Override
	public long getItemId( final int position )
	{
		return position;
	}

	@Override
	public View getView( final int position, final View convertView, final ViewGroup parent )
	{
		ViewDataBinding vdb;
		View v = convertView;
		if (null == v)
		{
			vdb = DataBindingUtil.inflate( inflater, getLayoutId( position ), parent, false );
			v = vdb.getRoot();
			v.setTag( vdb );
		}

		vdb = (ViewDataBinding) v.getTag();
		vdb.setVariable( BR.viewmodel, getItem( position ) );

		return v;
	}

	protected abstract int getLayoutId( final int position );
}
