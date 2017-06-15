package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.databinding.ObservableArrayList;
import android.os.Parcel;
import com.swarmnyc.mvvmlib.MvvmSpinnerViewModel;

/**
 * Created by somya on 6/7/17.
 */

public class SimpleSpinnerDemoViewModel extends MvvmSpinnerViewModel
{

	ObservableArrayList<TextListItemViewModel> items = new ObservableArrayList<>();


	public SimpleSpinnerDemoViewModel()
	{
		for ( int i = 0; i < 10; i++ )
		{
			final TextListItemViewModel itemViewModel = new TextListItemViewModel();
			itemViewModel.m_title.set( "title " + i );
			items.add( itemViewModel );

		}

		setSelectedVM( items.get( 4 ) );


	}

	protected SimpleSpinnerDemoViewModel( Parcel in) {
	}

	public static final Creator<SimpleSpinnerDemoViewModel> CREATOR = new Creator<SimpleSpinnerDemoViewModel>()
	{
		@Override
		public SimpleSpinnerDemoViewModel createFromParcel( Parcel source )
		{
			return new SimpleSpinnerDemoViewModel( source );
		}

		@Override
		public SimpleSpinnerDemoViewModel[] newArray( int size )
		{
			return new SimpleSpinnerDemoViewModel[size];
		}
	};


	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel( final Parcel dest, final int flags )
	{

	}

	@Override
	public ObservableArrayList getItemCollection()
	{
		return items;
	}
}
