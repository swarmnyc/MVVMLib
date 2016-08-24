package com.swarmnyc.mvvmlib;

import android.os.Bundle;
import android.os.Parcelable;

public abstract class MvvmViewModel implements Parcelable {
    private MvvmContext context;

    public MvvmContext getContext() {
        return context;
    }

    public void setContext(MvvmContext context) {
        this.context = context;
    }

    protected void onResult(int requestCode, int resultCode, Bundle args) {
    }

    protected void onInit(Bundle args) {

    }

    protected void navigateTo(String path) {
        context.getNavigationManager().navigateTo(path);
    }

    protected void navigateTo(String path, Bundle bundle) {
        context.getNavigationManager().navigateTo(path, bundle);
    }
}
