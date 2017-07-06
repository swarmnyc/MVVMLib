package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.os.Bundle;
import android.os.Parcel;
import com.swarmnyc.mvvmlib.MvvmViewModel;
import com.swarmnyc.mvvmlib.binding.BindableString;

/**
 * Created by Tao on 7/6/17.
 */
public class DataDialogViewModel extends MvvmViewModel {
	private BindableString name = new BindableString("mvvmlib");

	public static final Creator<DataDialogViewModel> CREATOR = new Creator<DataDialogViewModel>() {
		@Override
		public DataDialogViewModel createFromParcel(Parcel source) {
			return new DataDialogViewModel(source);
		}

		@Override
		public DataDialogViewModel[] newArray(int size) {
			return new DataDialogViewModel[size];
		}
	};

	public DataDialogViewModel() {

	}

	protected DataDialogViewModel(Parcel in) {
	}

	@Override public int describeContents() {
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags) {

	}

	@Override public void onInit( Bundle args ) {
		String name = args.getString("name");
		setName(name);
	}

	public void onDone() {
		Bundle bundle = new Bundle();
		bundle.putString("name", name.get());
		getContext().getNavigationManager().dismiss(this.getClass(), bundle);
	}

	public void onCancel() {
		getContext().getNavigationManager().dismiss(this.getClass(), null);
	}

	public BindableString getName() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}
}
