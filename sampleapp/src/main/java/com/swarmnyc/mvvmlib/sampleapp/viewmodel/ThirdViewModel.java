package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.os.Bundle;
import android.os.Parcel;

import com.swarmnyc.mvvmlib.MvvmViewModel;
import com.swarmnyc.mvvmlib.binding.BindableString;

public class ThirdViewModel extends MvvmViewModel {
    private BindableString data;
    private String title;

    public ThirdViewModel() {
        data = new BindableString();
    }

    @Override
    public void onInit(Bundle args) {
        title = args.getString("title");
        data.set(args.getString("data"));
    }

    public BindableString getData() {
        return data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void close() {
        Bundle bundle = new Bundle();
        bundle.putString("data", data.get());
        this.getContext().close(1, bundle);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.data, flags);
    }

    protected ThirdViewModel(Parcel in) {
        this.data = in.readParcelable(BindableString.class.getClassLoader());
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
