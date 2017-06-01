package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.databinding.ObservableArrayList;
import android.os.Parcel;
import com.swarmnyc.mvvmlib.MvvmListViewModel;
import com.swarmnyc.mvvmlib.MvvmViewModel;
import com.swarmnyc.mvvmlib.binding.BindableString;

/**
 * Created by somya on 5/31/17.
 */

public class TextListViewModel extends MvvmListViewModel
{
	ObservableArrayList<MvvmViewModel> items = new ObservableArrayList<>();

	public static final Creator<TextListViewModel> CREATOR = new Creator<TextListViewModel>()
	{
		@Override
		public TextListViewModel createFromParcel( Parcel source )
		{
			return new TextListViewModel( source );
		}

		@Override
		public TextListViewModel[] newArray( int size )
		{
			return new TextListViewModel[size];
		}
	};

	public TextListViewModel(  )
	{
		for ( int i = 0; i < 100; i++ )
		{
			if (i%2==0)
			{
				final TextListItemViewModel itemViewModel = new TextListItemViewModel();
				itemViewModel.m_title.set( "item: " + String.valueOf( i ) );
				items.add( itemViewModel );
			}
			else {
				items.add( new ImageListItemViewModel(  ) );
			}
		}
	}

	protected TextListViewModel(Parcel in) {
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
