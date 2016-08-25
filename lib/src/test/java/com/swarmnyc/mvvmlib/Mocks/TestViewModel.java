package com.swarmnyc.mvvmlib.Mocks;

import android.os.Parcel;

import com.swarmnyc.mvvmlib.MvvmViewModel;

public class TestViewModel extends MvvmViewModel {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public TestViewModel() {
    }

    protected TestViewModel(Parcel in) {
    }

    public static final Creator<TestViewModel> CREATOR = new Creator<TestViewModel>() {
        @Override
        public TestViewModel createFromParcel(Parcel source) {
            return new TestViewModel(source);
        }

        @Override
        public TestViewModel[] newArray(int size) {
            return new TestViewModel[size];
        }
    };
}
