package com.swarmnyc.mvvmlib.adapter;

import android.databinding.ObservableList;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;

public class ListChangedCallbackForViewPager<T> extends ObservableList.OnListChangedCallback<ObservableList<T>>
{
	private PagerAdapter adapter;
	private int offset;

	public ListChangedCallbackForViewPager( PagerAdapter adapter )
	{
		this.adapter = adapter;
	}

	public ListChangedCallbackForViewPager( PagerAdapter adapter, int offset )
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
