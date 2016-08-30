package com.swarmnyc.mvvmlib.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import com.swarmnyc.mvvmlib.BR;
import com.swarmnyc.mvvmlib.MvvmViewModel;
import com.swarmnyc.mvvmlib.R;

public class DataBindingViewHolder extends RecyclerView.ViewHolder
{
	private ViewDataBinding dataBinding;
	private MvvmViewModel viewModel;

	public DataBindingViewHolder( ViewDataBinding dataBinding )
	{
		super( dataBinding.getRoot() );
		this.dataBinding = dataBinding;
	}

	@SuppressWarnings( "unchecked" )
	public <T extends ViewDataBinding> T getDataBinding()
	{
		return (T) dataBinding;
	}

	@SuppressWarnings( "unchecked" )
	public <T extends MvvmViewModel> T getDataViewModel()
	{
		return (T) viewModel;
	}

	public static DataBindingViewHolder Create(
		LayoutInflater inflater,
		@LayoutRes
		int layoutId
	)
	{
		return new DataBindingViewHolder( DataBindingUtil.inflate( inflater, layoutId, null, false ) );
	}

	public void setViewModel( MvvmViewModel viewModel )
	{
		this.viewModel = viewModel;
		this.dataBinding.setVariable( BR.viewmodel, viewModel );
		this.itemView.setTag( R.id.viewModel, viewModel );
	}

	public void setData( Object data )
	{
		this.dataBinding.setVariable( BR.viewmodel, data );
		this.itemView.setTag( R.id.viewModel, data );
	}
}
