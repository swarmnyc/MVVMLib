package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.os.Parcel;

import com.swarmnyc.mvvmlib.MvvmViewModel;

public class BindableViewViewModel extends MvvmViewModel {
    private BindableViewSubViewModel subViewModel = new BindableViewSubViewModel();

    public static final Creator<BindableViewViewModel> CREATOR = new Creator<BindableViewViewModel>() {
        @Override
        public BindableViewViewModel createFromParcel(Parcel source) {
            return new BindableViewViewModel(source);
        }

        @Override
        public BindableViewViewModel[] newArray(int size) {
            return new BindableViewViewModel[size];
        }
    };

    public BindableViewViewModel() {
        this.subViewModel.getValue().set(50);
    }

    private BindableViewViewModel(Parcel in) {
    }

    public BindableViewSubViewModel getSubViewModel() {
        return subViewModel;
    }

    public void changeValue(int value) {
        this.subViewModel.getValue().set(this.subViewModel.getValue().get() + value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
