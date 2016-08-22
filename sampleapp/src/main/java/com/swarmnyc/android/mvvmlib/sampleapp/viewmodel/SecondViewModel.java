package com.swarmnyc.android.mvvmlib.sampleapp.viewmodel;

import android.os.Parcel;

import com.swarmnyc.android.mvvmlib.MvvmViewModel;

public class SecondViewModel extends MvvmViewModel {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public SecondViewModel() {
    }

    protected SecondViewModel(Parcel in) {
    }

    public static final Creator<SecondViewModel> CREATOR = new Creator<SecondViewModel>() {
        @Override
        public SecondViewModel createFromParcel(Parcel source) {
            return new SecondViewModel(source);
        }

        @Override
        public SecondViewModel[] newArray(int size) {
            return new SecondViewModel[size];
        }
    };
}
