package com.swarmnyc.mvvmlib.binding;

import android.databinding.BaseObservable;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class BindableUri extends BaseObservable implements Parcelable {
    Uri value;

    public BindableUri() {
    }

    public BindableUri(Uri b) {
        value = b;
    }

    public Uri get() {
        return value;
    }

    public void set(Uri value) {
        if (this.value != value) {
            this.value = value;
            notifyChange();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.value, flags);
    }

    protected BindableUri(Parcel in) {
        this.value = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<BindableUri> CREATOR = new Creator<BindableUri>() {
        @Override
        public BindableUri createFromParcel(Parcel source) {
            return new BindableUri(source);
        }

        @Override
        public BindableUri[] newArray(int size) {
            return new BindableUri[size];
        }
    };
}

