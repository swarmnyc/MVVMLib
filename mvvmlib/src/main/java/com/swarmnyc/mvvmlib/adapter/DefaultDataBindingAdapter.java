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
public class DefaultDataBindingAdapter extends BaseDataBindingAdapter
{
	@LayoutRes private final int layoutId;
	private final     List<Class>         viewModelTypes     = new ArrayList<>();
	private final Map<Class, Integer> viewModelLayoutMap = new HashMap<>();

	public DefaultDataBindingAdapter(
		Context context,
		MvvmListViewModel listViewModel,
		@LayoutRes int layoutId
	)
	{
		super(context, listViewModel);

		this.layoutId = layoutId;
	}


	@Override
	protected int getLayoutId( final int viewType )
	{
		return layoutId;
	}


}
