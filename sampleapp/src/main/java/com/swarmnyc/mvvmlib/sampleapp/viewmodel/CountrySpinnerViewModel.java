package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.databinding.ObservableArrayList;
import android.os.Parcel;
import com.swarmnyc.mvvmlib.MvvmSpinnerViewModel;

/**
 * Created by somya on 6/7/17.
 */

public class CountrySpinnerViewModel extends MvvmSpinnerViewModel
{

	ObservableArrayList<TextListItemViewModel> m_countries = new ObservableArrayList<>();

	public CountrySpinnerViewModel()
	{

		TextListItemViewModel itemViewModel = new TextListItemViewModel();
		itemViewModel.m_title.set( "USA" );
		m_countries.add( itemViewModel );


		itemViewModel = new TextListItemViewModel();
		itemViewModel.m_title.set( "UK" );
		m_countries.add( itemViewModel );


		itemViewModel = new TextListItemViewModel();
		itemViewModel.m_title.set( "Australia" );
		m_countries.add( itemViewModel );


	}

	protected CountrySpinnerViewModel( Parcel in )
	{
	}

	public static final Creator<CountrySpinnerViewModel> CREATOR = new Creator<CountrySpinnerViewModel>()
	{
		@Override
		public CountrySpinnerViewModel createFromParcel( Parcel source )
		{
			return new CountrySpinnerViewModel( source );
		}

		@Override
		public CountrySpinnerViewModel[] newArray( int size )
		{
			return new CountrySpinnerViewModel[size];
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
		return m_countries;
	}
}
