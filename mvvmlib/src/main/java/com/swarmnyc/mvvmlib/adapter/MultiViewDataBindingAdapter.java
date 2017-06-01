package com.swarmnyc.mvvmlib.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import com.swarmnyc.mvvmlib.MvvmListViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Supports binding multiple view model types for a Recycle View Adapter
 */
public class MultiViewDataBindingAdapter extends BaseDataBindingAdapter
{
	private final List<Class>         viewModelTypes     = new ArrayList<>();
	private  Map<Class, Integer> m_viewModelLayoutMap;


	public MultiViewDataBindingAdapter(
		final Context context, final MvvmListViewModel listViewModel, Map<Class, Integer> viewModelLayoutMap
	)
	{
		super( context, listViewModel );
		m_viewModelLayoutMap = viewModelLayoutMap;

		viewModelTypes.addAll( viewModelLayoutMap.keySet() );
	}

	@Override
	protected int getLayoutId( final int viewType )
	{
		return m_viewModelLayoutMap.get( viewModelTypes.get( viewType ));
	}


	@Override
	public int getItemViewType( int position )
	{
		Parcelable itemViewModel = this.getViewModels().get( position );
		return viewModelTypes.indexOf( itemViewModel.getClass() );
	}

	/**
	 * Add the layout resource mapping for a ViewModel
	 *
	 * @param viewModelType the view model class
	 * @param resId         the android resource
	 */
	public void addViewModelLayoutMapping(
		final Class viewModelType,
		@LayoutRes
		final int resId
	)
	{
		if (!viewModelTypes.contains( viewModelType ))
		{
			viewModelTypes.add( viewModelType );
			m_viewModelLayoutMap.put( viewModelType, resId );
		}
	}

	/**
	 * Clear the View Model Map
	 */
	public void clearViewModelLayoutMap()
	{
		viewModelTypes.clear();
		m_viewModelLayoutMap.clear();
	}
}
