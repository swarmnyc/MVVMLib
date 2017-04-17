package com.swarmnyc.mvvmlib.binding;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.agera.ActivationHandler;
import com.google.android.agera.Observable;
import com.google.android.agera.Observables;
import com.google.android.agera.Updatable;
import com.google.android.agera.UpdateDispatcher;

/**
 * Two way bindable string, ObservableField<String> is one way.
 * because the official library doesn't support two way binding, so this class is for it.
 */
public class BindableString extends BaseObservable implements Parcelable, Observable {
    private String value;
    private final UpdateDispatcher updateDispatcher = Observables.updateDispatcher();;

    public BindableString() {
    }

    public BindableString(String value) {
        this.value = value;
    }

    public String get() {
        return value != null ? value : "";
    }

    public void set(String value) {
        if ((this.value == null && value != null) || (this.value != null && !this.value.equals(value))) {
            this.value = value;
            notifyChange();
            updateDispatcher.update();
        }
    }

    public boolean isEmpty() {
        return value == null || value.isEmpty();
    }

    public boolean equals(String s) {
        if (value == null)
            return s == null;

        return value.equals(s);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.value);
    }

    protected BindableString(Parcel in) {
        this.value = in.readString();
    }

    public static final Creator<BindableString> CREATOR = new Creator<BindableString>() {
        @Override
        public BindableString createFromParcel(Parcel source) {
            return new BindableString(source);
        }

        @Override
        public BindableString[] newArray(int size) {
            return new BindableString[size];
        }
    };

    @Override
    public void addUpdatable(Updatable updatable) {
        updateDispatcher.addUpdatable(updatable);
    }

    @Override
    public void removeUpdatable(Updatable updatable) {
        updateDispatcher.removeUpdatable(updatable);
    }
}