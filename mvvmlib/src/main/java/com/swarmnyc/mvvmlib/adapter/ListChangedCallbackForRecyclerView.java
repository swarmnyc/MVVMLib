package com.swarmnyc.mvvmlib.adapter;

import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;

public class ListChangedCallbackForRecyclerView<T> extends ObservableList.OnListChangedCallback<ObservableList<T>>
{
	private RecyclerView.Adapter adapter;
	private int offset;

	public ListChangedCallbackForRecyclerView( RecyclerView.Adapter adapter )
	{
		this.adapter = adapter;
	}

	public ListChangedCallbackForRecyclerView( RecyclerView.Adapter adapter, int offset )
	{
		this.adapter = adapter;
		this.offset = offset;
	}

	@Override
	public void onChanged( ObservableList<T> sender )
	{
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onItemRangeChanged( ObservableList<T> sender, int positionStart, int itemCount )
	{
		adapter.notifyItemRangeChanged( offset + positionStart, itemCount );
	}

	@Override
	public void onItemRangeInserted( ObservableList<T> sender, int positionStart, int itemCount )
	{
		adapter.notifyItemInserted( offset + positionStart );
	}

	@Override
	public void onItemRangeMoved( ObservableList<T> sender, int fromPosition, int toPosition, int itemCount )
	{
		adapter.notifyItemMoved( offset + fromPosition, toPosition );
	}

	@Override
	public void onItemRangeRemoved( ObservableList<T> sender, int positionStart, int itemCount )
	{
		adapter.notifyItemRangeRemoved( offset + positionStart, itemCount );
	}
}
