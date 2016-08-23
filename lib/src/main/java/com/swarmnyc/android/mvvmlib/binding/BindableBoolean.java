package com.swarmnyc.android.mvvmlib.binding;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

public class BindableBoolean extends BaseObservable implements Parcelable {
    boolean value;

    public BindableBoolean() {
    }

    public BindableBoolean(boolean b) {
        value = b;
    }

    public boolean get() {
        return value;
    }

    public void set(boolean value) {
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
        dest.writeByte(this.value ? (byte) 1 : (byte) 0);
    }

    protected BindableBoolean(Parcel in) {
        this.value = in.readByte() != 0;
    }

    public static final Creator<BindableBoolean> CREATOR = new Creator<BindableBoolean>() {
        @Override
        public BindableBoolean createFromParcel(Parcel source) {
            return new BindableBoolean(source);
        }

        @Override
        public BindableBoolean[] newArray(int size) {
            return new BindableBoolean[size];
        }
    };
}

