package com.swarmnyc.mvvmlib.adapter;

import android.databinding.ObservableList;

public class ListChangedCallbackForSpinner<T> extends ObservableList.OnListChangedCallback<ObservableList<T>>
{
	private BaseBindingAdapter adapter;
	private int                offset;

	public ListChangedCallbackForSpinner( BaseBindingAdapter adapter )
	{
		this.adapter = adapter;
	}


	@Override
	public void onChanged( ObservableList<T> sender )
	{
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onItemRangeChanged( ObservableList<T> sender, int positionStart, int itemCount )
	{
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onItemRangeInserted( ObservableList<T> sender, int positionStart, int itemCount )
	{
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onItemRangeMoved( ObservableList<T> sender, int fromPosition, int toPosition, int itemCount )
	{
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onItemRangeRemoved( ObservableList<T> sender, int positionStart, int itemCount )
	{
		adapter.notifyDataSetChanged();
	}
}
