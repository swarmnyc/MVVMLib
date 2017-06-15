package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.databinding.Observable;
import android.os.Parcel;
import com.swarmnyc.mvvmlib.MvvmViewModel;

/**
 * Created by somya on 6/7/17.
 */

public class SpinnerDemoViewModel extends MvvmViewModel
{

	SimpleSpinnerDemoViewModel m_simpleSpinnerVM  = new SimpleSpinnerDemoViewModel();
	CountrySpinnerViewModel    m_countrySpinnerVM = new CountrySpinnerViewModel();
	StateSpinnerViewModel      m_stateSpinnerVM   = new StateSpinnerViewModel();


	public SpinnerDemoViewModel()
	{
		m_countrySpinnerVM.getSelectedItem().addOnPropertyChangedCallback( new Observable.OnPropertyChangedCallback()
		{
			@Override
			public void onPropertyChanged( final Observable sender, final int propertyId )
			{
				final TextListItemViewModel selectedVM = (TextListItemViewModel) m_countrySpinnerVM.getSelectedVM();
				switch ( selectedVM.m_title.get() )
				{
					case "USA":
						m_stateSpinnerVM.setStates( new String[]{"NY", "CA", "IN", "AL"} );
						break;
					case "Australia":
						m_stateSpinnerVM.setStates( new String[]{"NSW", "VIC", "QLD", "WA"} );
						break;
					case "UK":
						m_stateSpinnerVM.setStates( new String[]{"Lester", "London", "Wales", "Scotland"} );

						break;
				}
				m_stateSpinnerVM.setSelectedVM( (MvvmViewModel) m_stateSpinnerVM.getItemCollection().get( 0 ) );
			}
		} );
	}


	public SimpleSpinnerDemoViewModel getSimpleSpinnerVM()
	{
		return m_simpleSpinnerVM;
	}

	public CountrySpinnerViewModel getCountrySpinnerVM()
	{
		return m_countrySpinnerVM;
	}

	public StateSpinnerViewModel getStateSpinnerVM()
	{
		return m_stateSpinnerVM;
	}

	protected SpinnerDemoViewModel( Parcel in )
	{
	}

	public static final Creator<SpinnerDemoViewModel> CREATOR = new Creator<SpinnerDemoViewModel>()
	{
		@Override
		public SpinnerDemoViewModel createFromParcel( Parcel source )
		{
			return new SpinnerDemoViewModel( source );
		}

		@Override
		public SpinnerDemoViewModel[] newArray( int size )
		{
			return new SpinnerDemoViewModel[size];
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

}
