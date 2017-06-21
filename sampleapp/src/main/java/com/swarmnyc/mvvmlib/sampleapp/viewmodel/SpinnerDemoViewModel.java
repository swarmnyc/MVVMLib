package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.os.Parcel;
import com.swarmnyc.mvvmlib.MvvmViewModel;

/**
 * Created by somya on 6/7/17.
 */

public class SpinnerDemoViewModel extends MvvmViewModel
{


	ObservableArrayList<TextListItemViewModel> m_labels        = new ObservableArrayList<TextListItemViewModel>();
	ObservableField<TextListItemViewModel>     m_selectedLabel = new ObservableField<>();

	ObservableArrayList<TextListItemViewModel> m_countries       = new ObservableArrayList<>();
	ObservableField<TextListItemViewModel>     m_selectedCountry = new ObservableField<>();
	ObservableArrayList<TextListItemViewModel> m_states          = new ObservableArrayList<>();
	ObservableField<TextListItemViewModel>     m_selectedState   = new ObservableField<>();


	public SpinnerDemoViewModel()
	{
		m_labels.add( new TextListItemViewModel( "Label 1" ) );
		m_labels.add( new TextListItemViewModel( "Label 2" ) );
		m_labels.add( new TextListItemViewModel( "Label 3" ) );
		m_labels.add( new TextListItemViewModel( "Label 4" ) );

		m_countries.add( new TextListItemViewModel( "USA" ) );
		m_countries.add( new TextListItemViewModel( "UK" ) );
		m_countries.add( new TextListItemViewModel( "Australia" ) );


		m_selectedCountry.addOnPropertyChangedCallback( new Observable.OnPropertyChangedCallback()
		{
			@Override
			public void onPropertyChanged( final Observable sender, final int propertyId )
			{
				final TextListItemViewModel selectedVM = m_selectedCountry.get();
				switch ( selectedVM.m_title.get() )
				{
					case "USA":
						setStates( new String[]{"NY", "CA", "IN", "AL"} );
						break;
					case "Australia":
						setStates( new String[]{"NSW", "VIC", "QLD", "WA"} );
						break;
					case "UK":
						setStates( new String[]{"Lester", "London", "Wales", "Scotland"} );
						break;
				}
				m_selectedState.set( m_states.get( 0 ) );
			}
		} );
	}


	public void setStates( final String[] stateNames )
	{
		m_states.clear();

		for ( String s : stateNames )
		{
			m_states.add( new TextListItemViewModel( s ) );
		}

		//		setSelectedVM( m_states.get( 0 ) );
	}


	public ObservableArrayList<TextListItemViewModel> getLabels()
	{
		return m_labels;
	}

	public ObservableField<TextListItemViewModel> getSelectedLabel()
	{
		return m_selectedLabel;
	}

	public ObservableArrayList<TextListItemViewModel> getCountries()
	{
		return m_countries;
	}

	public ObservableField<TextListItemViewModel> getSelectedCountry()
	{
		return m_selectedCountry;
	}

	public ObservableArrayList<TextListItemViewModel> getStates()
	{
		return m_states;
	}

	public ObservableField<TextListItemViewModel> getSelectedState()
	{
		return m_selectedState;
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
