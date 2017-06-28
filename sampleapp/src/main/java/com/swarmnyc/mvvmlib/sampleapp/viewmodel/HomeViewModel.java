package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.os.Parcel;
import com.swarmnyc.mvvmlib.MvvmViewModel;

public class HomeViewModel extends MvvmViewModel
{
	public HomeViewModel()
	{
	}

	public void navToHelloDemo()
	{
		navigateTo( HelloViewModel.class );
	}

	public void navToFragmentNavDemo()
	{
		navigateTo( FragmentNavigationDemoViewModel.class );
	}

	public void navToSpinnerDemo()
	{
		navigateTo( SpinnerDemoViewModel.class );
	}

	public void navToSimpleList()
	{
		navigateTo( SimpleListViewModel.class );
	}

	public void navToSimpleGrid()
	{
		navigateTo( SimpleGridViewModel.class );
	}

	public void navToMultiItemList()
	{
		navigateTo( MultiItemListViewModel.class );
	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel( Parcel dest, int flags )
	{
	}

	protected HomeViewModel( Parcel in )
	{
	}

	public static final Creator<HomeViewModel> CREATOR = new Creator<HomeViewModel>()
	{
		@Override
		public HomeViewModel createFromParcel( Parcel source )
		{
			return new HomeViewModel( source );
		}

		@Override
		public HomeViewModel[] newArray( int size )
		{
			return new HomeViewModel[size];
		}
	};
}
