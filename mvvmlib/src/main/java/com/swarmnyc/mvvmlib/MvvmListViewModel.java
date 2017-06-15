package com.swarmnyc.mvvmlib;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;

import java.util.Collection;
import java.util.List;

public abstract class MvvmListViewModel<T> extends MvvmViewModel {
    public void onResult(int requestCode, int resultCode, Bundle args) {
    }

    public void onInit(Bundle args) {
    }

    abstract public ObservableList<T> getItemCollection();
}
