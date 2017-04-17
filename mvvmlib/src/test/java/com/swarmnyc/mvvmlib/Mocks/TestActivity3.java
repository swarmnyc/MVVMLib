package com.swarmnyc.mvvmlib.Mocks;

import android.content.Context;

import com.swarmnyc.mvvmlib.MvvmActivity;
import com.swarmnyc.mvvmlib.MvvmContext;
import com.swarmnyc.mvvmlib.MvvmViewModel;

public class TestActivity3<T extends MvvmViewModel> extends MvvmActivity<T> {
    @Override
    protected int getLayoutResourceId() {
        return 0;
    }
}
