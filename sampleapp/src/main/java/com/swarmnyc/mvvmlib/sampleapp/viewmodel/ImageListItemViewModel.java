package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.os.Parcel;
import com.swarmnyc.mvvmlib.MvvmViewModel;
import com.swarmnyc.mvvmlib.binding.BindableString;

/**
 * Created by somya on 5/31/17.
 */

public class ImageListItemViewModel extends MvvmViewModel
{
	BindableString m_url = new BindableString();

	public static final Creator<ImageListItemViewModel> CREATOR = new Creator<ImageListItemViewModel>()
	{
		@Override
		public ImageListItemViewModel createFromParcel( Parcel source )
		{
			return new ImageListItemViewModel( source );
		}

		@Override
		public ImageListItemViewModel[] newArray( int size )
		{
			return new ImageListItemViewModel[size];
		}
	};

	public ImageListItemViewModel(  )
	{
		m_url.set( "https://placeimg.com/100/30/any" );
	}

	public BindableString getUrl()
	{
		return m_url;
	}


	public void setUrl( final BindableString url )
	{
		m_url = url;
	}

	protected ImageListItemViewModel( Parcel in) {
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
