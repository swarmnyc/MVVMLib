package com.swarmnyc.mvvmlib.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.swarmnyc.mvvmlib.BR;

/**
 * Created by somya on 6/7/17.
 */

public abstract class BaseBindingAdapter extends android.widget.BaseAdapter
{
	protected final LayoutInflater inflater;
	private final ObservableList m_list;

	public BaseBindingAdapter(
		final Context context, final ObservableList list
	)
	{
		this.inflater = LayoutInflater.from( context );
		this.m_list = list;

		this.m_list.addOnListChangedCallback( new ListChangedCallbackForSpinner<>( this ));
	}

	@Override
	public int getCount()
	{
		return m_list.size();
	}

	@Override
	public Object getItem( final int position )
	{
		return m_list.get( position );
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
		// Bindings cannot be delayed for a spinner as the size of the view determines the size of the spinner when using
		// wrap content
		vdb.executePendingBindings();

		return v;
	}

	protected abstract int getLayoutId( final int position );
}
