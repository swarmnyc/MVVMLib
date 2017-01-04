package com.swarmnyc.mvvmlib.binding;

import android.databinding.ObservableBoolean;

import com.google.android.agera.BaseObservable;

/**
 * Created by Tao on 1/3/17.
 */
public class ObservableEvent extends BaseObservable implements Runnable {
    private ObservableBoolean mIsLoading = new ObservableBoolean(false);

    public ObservableBoolean get() {
        return mIsLoading;
    }

    public void start() {
        mIsLoading.set(true);
    }

    public void finish() {
        mIsLoading.set(false);
    }

    @Override
    public void run() {
        start();
        this.dispatchUpdate();
    }
}
