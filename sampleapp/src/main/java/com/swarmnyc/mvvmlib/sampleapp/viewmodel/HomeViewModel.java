package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.os.Parcel;
import com.swarmnyc.mvvmlib.MvvmViewModel;
import com.swarmnyc.mvvmlib.binding.BindableString;

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
