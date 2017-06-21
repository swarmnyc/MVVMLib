package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.databinding.ObservableArrayList;
import android.os.Parcel;
import com.swarmnyc.mvvmlib.MvvmListViewModel;
import com.swarmnyc.mvvmlib.MvvmViewModel;

/**
 * Created by somya on 5/31/17.
 */

public class SimpleListViewModel extends MvvmListViewModel
{
	ObservableArrayList<MvvmViewModel> items = new ObservableArrayList<>();

	public static final Creator<SimpleListViewModel> CREATOR = new Creator<SimpleListViewModel>()
	{
		@Override
		public SimpleListViewModel createFromParcel( Parcel source )
		{
			return new SimpleListViewModel( source );
		}

		@Override
		public SimpleListViewModel[] newArray( int size )
		{
			return new SimpleListViewModel[size];
		}
	};

	public SimpleListViewModel()
	{
		for ( int i = 0; i < 100; i++ )
		{

			final TextListItemViewModel itemViewModel = new TextListItemViewModel();
			itemViewModel.m_title.set( "item: " + String.valueOf( i ) );
			items.add( itemViewModel );

		}
	}

	protected SimpleListViewModel( Parcel in )
	{
	}

	@Override
	public ObservableArrayList getItemCollection()
	{
		return items;
	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel( final Parcel dest, final int flags )
	{

	}
}
