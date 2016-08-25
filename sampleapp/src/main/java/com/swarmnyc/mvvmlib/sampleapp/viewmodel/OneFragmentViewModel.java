package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.os.Parcel;

import com.swarmnyc.mvvmlib.MvvmViewModel;

public class OneFragmentViewModel extends MvvmViewModel {
    public static final Creator<OneFragmentViewModel> CREATOR = new Creator<OneFragmentViewModel>() {
        @Override
        public OneFragmentViewModel createFromParcel(Parcel source) {
            return new OneFragmentViewModel(source);
        }

        @Override
        public OneFragmentViewModel[] newArray(int size) {
            return new OneFragmentViewModel[size];
        }
    };

    public OneFragmentViewModel() {
    }

    protected OneFragmentViewModel(Parcel in) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
