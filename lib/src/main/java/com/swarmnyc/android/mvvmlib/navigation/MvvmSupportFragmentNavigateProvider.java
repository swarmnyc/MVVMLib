package com.swarmnyc.android.mvvmlib.navigation;

public abstract class MvvmSupportFragmentNavigateProvider implements MvvmNavigateProvider {
    private android.support.v4.app.FragmentManager fragmentSupportManager;

    public void setFragmentManager(android.support.v4.app.FragmentManager fragmentSupportManager){

        this.fragmentSupportManager = fragmentSupportManager;
    }

    public void PathHandle(){

    }
}
