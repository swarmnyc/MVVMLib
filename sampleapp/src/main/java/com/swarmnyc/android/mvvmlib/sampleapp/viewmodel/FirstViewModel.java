package com.swarmnyc.android.mvvmlib.sampleapp.viewmodel;

import android.os.Parcel;

import com.swarmnyc.android.mvvmlib.MvvmViewModel;
import com.swarmnyc.android.mvvmlib.binding.BindableString;

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
        getContext().getNavigationManager().navigateTo("ThirdActivity");
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
