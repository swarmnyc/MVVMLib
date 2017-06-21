package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.databinding.ObservableArrayList;
import android.os.Parcel;
import com.swarmnyc.mvvmlib.MvvmListViewModel;
import com.swarmnyc.mvvmlib.MvvmViewModel;

/**
 * Created by somya on 5/31/17.
 */

public class MultiItemListViewModel extends MvvmListViewModel
{
	ObservableArrayList<MvvmViewModel> items = new ObservableArrayList<>();

	public static final Creator<MultiItemListViewModel> CREATOR = new Creator<MultiItemListViewModel>()
	{
		@Override
		public MultiItemListViewModel createFromParcel( Parcel source )
		{
			return new MultiItemListViewModel( source );
		}

		@Override
		public MultiItemListViewModel[] newArray( int size )
		{
			return new MultiItemListViewModel[size];
		}
	};

	public MultiItemListViewModel(  )
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

	protected MultiItemListViewModel( Parcel in) {
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
