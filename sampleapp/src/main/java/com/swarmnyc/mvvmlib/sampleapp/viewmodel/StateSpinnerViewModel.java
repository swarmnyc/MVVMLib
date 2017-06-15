package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.databinding.ObservableArrayList;
import android.os.Parcel;
import com.swarmnyc.mvvmlib.MvvmSpinnerViewModel;

/**
 * Created by somya on 6/7/17.
 */

public class StateSpinnerViewModel extends MvvmSpinnerViewModel
{

	ObservableArrayList<TextListItemViewModel> m_states = new ObservableArrayList<>();

	public StateSpinnerViewModel()
	{
	}

	protected StateSpinnerViewModel( Parcel in )
	{
	}

	public void setStates( final String [] stateNames ) {
		m_states.clear();

		for ( String s : stateNames )
		{
			m_states.add( new TextListItemViewModel( s ) );
		}

		setSelectedVM( m_states.get( 0 ) );
	}

	public static final Creator<StateSpinnerViewModel> CREATOR = new Creator<StateSpinnerViewModel>()
	{
		@Override
		public StateSpinnerViewModel createFromParcel( Parcel source )
		{
			return new StateSpinnerViewModel( source );
		}

		@Override
		public StateSpinnerViewModel[] newArray( int size )
		{
			return new StateSpinnerViewModel[size];
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
		return m_states;
	}
}
