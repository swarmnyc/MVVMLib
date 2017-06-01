package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.databinding.ObservableArrayList;
import android.os.Parcel;
import com.swarmnyc.mvvmlib.MvvmListViewModel;
import com.swarmnyc.mvvmlib.MvvmViewModel;
import com.swarmnyc.mvvmlib.binding.BindableString;

/**
 * Created by somya on 5/31/17.
 */

public class TextListItemViewModel extends MvvmViewModel
{
	BindableString m_title = new BindableString();

	public static final Creator<TextListItemViewModel> CREATOR = new Creator<TextListItemViewModel>()
	{
		@Override
		public TextListItemViewModel createFromParcel( Parcel source )
		{
			return new TextListItemViewModel( source );
		}

		@Override
		public TextListItemViewModel[] newArray( int size )
		{
			return new TextListItemViewModel[size];
		}
	};

	public TextListItemViewModel(  )
	{
	}

	public BindableString getTitle()
	{
		return m_title;
	}

	protected TextListItemViewModel( Parcel in) {
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
