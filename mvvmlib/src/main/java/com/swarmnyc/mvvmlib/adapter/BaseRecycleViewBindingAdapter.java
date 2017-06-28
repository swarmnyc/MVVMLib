package com.swarmnyc.mvvmlib.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by Tao on 9/16/16.
 */
public abstract class BaseRecycleViewBindingAdapter<T> extends RecyclerView.Adapter
{
	private final LayoutInflater    inflater;
	private final ObservableList<T> viewModels;

	public BaseRecycleViewBindingAdapter(
		Context context, ObservableList<T> observableList
	)
	{
		this.inflater = LayoutInflater.from( context );
		this.viewModels = observableList;

		viewModels.addOnListChangedCallback( new ListChangedCallbackForRecyclerView<T>( this ) );


	}

	public ObservableList<T> getViewModels()
	{
		return viewModels;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType )
	{
		DataBindingViewHolder bindingViewHolder;
		ViewDataBinding vdb = DataBindingUtil.inflate( inflater, getLayoutId( viewType ), parent, false );
		bindingViewHolder = new DataBindingViewHolder( vdb );
		return bindingViewHolder;
	}

	protected abstract int getLayoutId( final int viewType );


	@Override
	public void onBindViewHolder( RecyclerView.ViewHolder holder, int position )
	{
		DataBindingViewHolder viewHolder = (DataBindingViewHolder) holder;
		T itemViewModel = this.viewModels.get( position );
		viewHolder.setData( itemViewModel );
	}

	@Override
	public int getItemCount()
	{
		return this.viewModels.size();
	}


}
