package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.databinding.ObservableArrayList;
import android.os.Parcel;
import com.swarmnyc.mvvmlib.MvvmViewModel;

/**
 * Created by somya on 5/31/17.
 */

public class SimpleGridViewModel extends MvvmViewModel
{
	ObservableArrayList<MvvmViewModel> items = new ObservableArrayList<>();

	public static final Creator<SimpleGridViewModel> CREATOR = new Creator<SimpleGridViewModel>()
	{
		@Override
		public SimpleGridViewModel createFromParcel( Parcel source )
		{
			return new SimpleGridViewModel( source );
		}

		@Override
		public SimpleGridViewModel[] newArray( int size )
		{
			return new SimpleGridViewModel[size];
		}
	};

	public SimpleGridViewModel()
	{
		for ( int i = 0; i < 100; i++ )
		{

			final TextListItemViewModel itemViewModel = new TextListItemViewModel();
			itemViewModel.m_title.set( "item: " + String.valueOf( i ) );
			items.add( itemViewModel );

		}
	}

	public ObservableArrayList<MvvmViewModel> getItems()
	{
		return items;
	}

	protected SimpleGridViewModel( Parcel in )
	{
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
