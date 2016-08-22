package com.swarmnyc.android.mvvmlib;

import android.os.Parcelable;

public abstract class MvvmViewModel implements Parcelable {
    private MvvmContext context;

    public MvvmContext getContext() {
        return context;
    }

    public void setContext(MvvmContext context) {
        this.context = context;
    }
}
