package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.databinding.ObservableInt;
import android.net.Uri;
import android.os.Parcel;

import com.swarmnyc.mvvmlib.MvvmViewModel;
import com.swarmnyc.mvvmlib.binding.BindableUri;
import com.swarmnyc.mvvmlib.sampleapp.R;

public class BindingViewModel extends MvvmViewModel {
    public static final Creator<BindingViewModel> CREATOR = new Creator<BindingViewModel>() {
        @Override
        public BindingViewModel createFromParcel(Parcel source) {
            return new BindingViewModel(source);
        }

        @Override
        public BindingViewModel[] newArray(int size) {
            return new BindingViewModel[size];
        }
    };

    public BindingViewModel() {
        userUri = new BindableUri(getUser1ImageUri());
        drawableId = new ObservableInt(R.drawable.swarm_logo);
    }

    protected BindingViewModel(Parcel in) {
    }

    private BindableUri userUri;
    private ObservableInt drawableId;

    public String getImageString() {
        return "http://s3.amazonaws.com/swarm-website-uploads/swarm-200px-logo-file-red-07.png";
    }

    public Uri getImageUri() {
        return Uri.parse("http://s3.amazonaws.com/swarm-website-uploads/swarm-200px-logo-file-red-07.png");
    }

    public Uri getLocalImageUri() {
        return Uri.parse("android.resource://com.swarmnyc.mvvmlib.sampleapp/drawable/swarm_logo");
    }

    public BindableUri getUserImageUri() {
        return userUri;
    }

    public ObservableInt getDrawableId() {
        return drawableId;
    }

    public Uri getUser1ImageUri() {
        return Uri.parse("https://swarm-website-uploads.s3.amazonaws.com/teammember-575ae2a527f37e153015b9fc");
    }

    public Uri getUser2ImageUri() {
        return Uri.parse("https://swarm-website-uploads.s3.amazonaws.com/teammember-575ae2a527f37e153015b9fd.jpg");
    }

    public boolean switchUser(boolean value) {
        if (value) {
            userUri.set(getUser2ImageUri());
        } else {
            userUri.set(getUser1ImageUri());
        }

        return false;
    }

    public boolean switchDrawable(boolean value) {
        if (value) {
            drawableId.set(R.drawable.android_logo);
        } else {
            drawableId.set(R.drawable.swarm_logo);
        }

        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
