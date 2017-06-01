package com.swarmnyc.mvvmlib.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.swarmnyc.mvvmlib.MvvmListViewModel;
import com.swarmnyc.mvvmlib.MvvmViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tao on 9/16/16.
 */
public abstract class BaseDataBindingAdapter extends RecyclerView.Adapter
{
	private final            LayoutInflater inflater;
	private final            ObservableArrayList<Parcelable> viewModels;

	public BaseDataBindingAdapter(
		Context context,
		MvvmListViewModel listViewModel
	)
	{
		this.inflater = LayoutInflater.from( context );
		this.viewModels = listViewModel.getItemCollection();

		viewModels.addOnListChangedCallback( new ListChangedCallbackForRecyclerView<MvvmViewModel>( this ) );
	}

	public ObservableArrayList<Parcelable> getViewModels()
	{
		return viewModels;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType )
	{
		DataBindingViewHolder bindingViewHolder;
		ViewDataBinding vdb = DataBindingUtil.inflate( inflater, getLayoutId(viewType), parent, false );
		bindingViewHolder = new DataBindingViewHolder( vdb );
		return bindingViewHolder;
	}

	protected abstract int getLayoutId( final int viewType );


	@Override
	public void onBindViewHolder( RecyclerView.ViewHolder holder, int position )
	{
		DataBindingViewHolder viewHolder = (DataBindingViewHolder) holder;
		Parcelable itemViewModel = this.viewModels.get( position );
		viewHolder.setData( itemViewModel );
	}

	@Override
	public int getItemCount()
	{
		return this.viewModels.size();
	}




}
