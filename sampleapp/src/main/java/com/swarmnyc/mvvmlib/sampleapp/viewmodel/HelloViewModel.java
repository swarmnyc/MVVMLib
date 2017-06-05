package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.os.Parcel;
import com.swarmnyc.mvvmlib.MvvmViewModel;
import com.swarmnyc.mvvmlib.binding.BindableString;

public class HelloViewModel extends MvvmViewModel
{
	private BindableString name;
	private ObservableBoolean showWelcome = new ObservableBoolean( false );

	public HelloViewModel()
	{
		name = new BindableString();
		name.addOnPropertyChangedCallback( new Observable.OnPropertyChangedCallback()
		{
			@Override
			public void onPropertyChanged( final Observable sender, final int propertyId )
			{
				showWelcome.set( name.get().length() > 0 );
			}
		} );
	}

	public BindableString getName()
	{
		return name;
	}

	public ObservableBoolean getShowWelcome()
	{
		return showWelcome;
	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel( Parcel dest, int flags )
	{
		dest.writeParcelable( this.name, flags );
	}

	protected HelloViewModel( Parcel in )
	{
		this.name = in.readParcelable( BindableString.class.getClassLoader() );
	}

	public static final Creator<HelloViewModel> CREATOR = new Creator<HelloViewModel>()
	{
		@Override
		public HelloViewModel createFromParcel( Parcel source )
		{
			return new HelloViewModel( source );
		}

		@Override
		public HelloViewModel[] newArray( int size )
		{
			return new HelloViewModel[size];
		}
	};
}
