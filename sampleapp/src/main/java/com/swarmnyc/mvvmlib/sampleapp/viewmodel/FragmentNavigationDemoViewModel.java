package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.os.Parcel;
import com.swarmnyc.mvvmlib.MvvmViewModel;
import com.swarmnyc.mvvmlib.binding.BindableString;

/**
 * Created by somya on 5/31/17.
 */

public class FragmentNavigationDemoViewModel extends MvvmViewModel
{

	public static final Creator<FragmentNavigationDemoViewModel> CREATOR = new Creator<FragmentNavigationDemoViewModel>()
	{
		@Override
		public FragmentNavigationDemoViewModel createFromParcel( Parcel source )
		{
			return new FragmentNavigationDemoViewModel( source );
		}

		@Override
		public FragmentNavigationDemoViewModel[] newArray( int size )
		{
			return new FragmentNavigationDemoViewModel[size];
		}
	};

	public FragmentNavigationDemoViewModel(  )
	{
	}

	protected FragmentNavigationDemoViewModel( Parcel in) {
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
