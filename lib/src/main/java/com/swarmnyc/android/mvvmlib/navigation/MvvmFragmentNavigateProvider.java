package com.swarmnyc.android.mvvmlib.navigation;

import android.app.FragmentManager;

public abstract class MvvmFragmentNavigateProvider implements MvvmNavigateProvider {
    private FragmentManager fragmentManager;

    public void setFragmentManager(FragmentManager fragmentManager){

        this.fragmentManager = fragmentManager;
    }

    public void PathHandle(){

    }
}
