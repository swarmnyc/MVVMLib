package com.swarmnyc.mvvmlib.sampleapp.viewmodel;

import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.os.Parcel;

import com.swarmnyc.mvvmlib.MvvmViewModel;
import com.swarmnyc.mvvmlib.binding.BindableString;

public class BindableViewSubViewModel extends MvvmViewModel {
    private ObservableInt value = new ObservableInt();
    private BindableString valueText = new BindableString();

    public static final Creator<BindableViewSubViewModel> CREATOR = new Creator<BindableViewSubViewModel>() {
        @Override
        public BindableViewSubViewModel createFromParcel(Parcel source) {
            return new BindableViewSubViewModel(source);
        }

        @Override
        public BindableViewSubViewModel[] newArray(int size) {
            return new BindableViewSubViewModel[size];
        }
    };

    public BindableViewSubViewModel() {
        value.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                valueText.set(String.valueOf(value.get()));
            }
        });
    }

    public ObservableInt getValue() {
        return value;
    }

    public BindableString getValueText() {
        return valueText;
    }

    protected BindableViewSubViewModel(Parcel in) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
