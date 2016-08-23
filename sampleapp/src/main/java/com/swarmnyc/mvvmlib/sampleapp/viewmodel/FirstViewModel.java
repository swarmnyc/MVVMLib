package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.os.Bundle;
import android.os.Parcel;

import com.swarmnyc.mvvmlib.Keys;
import com.swarmnyc.mvvmlib.MvvmViewModel;
import com.swarmnyc.mvvmlib.binding.BindableString;

public class FirstViewModel extends MvvmViewModel {
    private BindableString dataToPass;
    private BindableString passedData;

    public FirstViewModel() {
        dataToPass = new BindableString();
        passedData = new BindableString();
    }

    public BindableString getDataToPass() {
        return dataToPass;
    }

    public BindableString getPassedData() {
        return passedData;
    }

    public void navToThirdActivity() {
        Bundle args = new Bundle();
        args.putString("data", getDataToPass().get());
        getContext().getNavigationManager().navigateTo("ThirdActivity", args);
    }

    @Override
    public void onResult(int requestCode, int resultCode, Bundle args) {
        if (requestCode == 2) {
            getPassedData().set(args.getString(Keys.PASS_VALUE));
        } else if (requestCode == 3) {
            getPassedData().set(args.getString("data"));
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.dataToPass, flags);
        dest.writeParcelable(this.passedData, flags);
    }

    protected FirstViewModel(Parcel in) {
        this.dataToPass = in.readParcelable(BindableString.class.getClassLoader());
        this.passedData = in.readParcelable(BindableString.class.getClassLoader());
    }

    public static final Creator<FirstViewModel> CREATOR = new Creator<FirstViewModel>() {
        @Override
        public FirstViewModel createFromParcel(Parcel source) {
            return new FirstViewModel(source);
        }

        @Override
        public FirstViewModel[] newArray(int size) {
            return new FirstViewModel[size];
        }
    };
}
