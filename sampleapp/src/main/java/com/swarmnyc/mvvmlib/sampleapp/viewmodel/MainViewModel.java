package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.os.Parcel;
import com.swarmnyc.mvvmlib.MvvmViewModel;

public class MainViewModel extends MvvmViewModel
{

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel( Parcel dest, int flags )
	{
	}

	public MainViewModel()
	{
	}


	public void navToFragmentNavDemo()
	{
		navigateTo( FragmentNavigationDemoViewModel.class );
	}

	public void navToOneFragment()
	{
		navigateTo( FirstViewModel.class );
	}

	protected MainViewModel( Parcel in )
	{
	}

	public static final Creator<MainViewModel> CREATOR = new Creator<MainViewModel>()
	{
		@Override
		public MainViewModel createFromParcel( Parcel source )
		{
			return new MainViewModel( source );
		}

		@Override
		public MainViewModel[] newArray( int size )
		{
			return new MainViewModel[size];
		}
	};
}
