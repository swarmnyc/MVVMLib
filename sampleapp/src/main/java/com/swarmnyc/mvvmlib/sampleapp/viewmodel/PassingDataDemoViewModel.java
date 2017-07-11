package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.os.Bundle;
import android.os.Parcel;
import com.swarmnyc.mvvmlib.MvvmViewModel;
import com.swarmnyc.mvvmlib.binding.BindableString;

/**
 * Created by Tao on 7/6/17.
 */
public class PassingDataDemoViewModel extends MvvmViewModel {
	private BindableString welcome = new BindableString("welcome");

	public static final Creator<PassingDataDemoViewModel> CREATOR = new Creator<PassingDataDemoViewModel>() {
		@Override
		public PassingDataDemoViewModel createFromParcel(Parcel source) {
			return new PassingDataDemoViewModel(source);
		}

		@Override
		public PassingDataDemoViewModel[] newArray(int size) {
			return new PassingDataDemoViewModel[size];
		}
	};

	public PassingDataDemoViewModel() {

	}

	protected PassingDataDemoViewModel(Parcel in) {
	}

	@Override public int describeContents() {
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags) {

	}

	@Override public void onResult( int requestCode, int resultCode, Bundle args ) {
		String name;
		if (args == null) {
			welcome.set("Operation Cancelled.");
		} else {
			welcome.set("Welcome, " + args.getString("name"));
		}
	}

	public void navToDialog() {
		Bundle bundle = new Bundle();
		bundle.putString("name", "unknown name");
		navigateTo(DataDialogViewModel.class, bundle);
	}

	public BindableString getWelcome() {
		return welcome;
	}
}
