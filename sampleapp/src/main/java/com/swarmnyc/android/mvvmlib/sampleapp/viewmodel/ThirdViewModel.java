package com.swarmnyc.android.mvvmlib.sampleapp.viewmodel;

import android.os.Parcel;

import com.swarmnyc.android.mvvmlib.MvvmViewModel;

public class ThirdViewModel extends MvvmViewModel {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public ThirdViewModel() {
    }

    protected ThirdViewModel(Parcel in) {
    }

    public void close(){
        this.getContext().close();
    }

    public static final Creator<ThirdViewModel> CREATOR = new Creator<ThirdViewModel>() {
        @Override
        public ThirdViewModel createFromParcel(Parcel source) {
            return new ThirdViewModel(source);
        }

        @Override
        public ThirdViewModel[] newArray(int size) {
            return new ThirdViewModel[size];
        }
    };
}
