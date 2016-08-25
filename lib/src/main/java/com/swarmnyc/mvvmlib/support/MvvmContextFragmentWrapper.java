package com.swarmnyc.mvvmlib.support;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.swarmnyc.mvvmlib.MvvmContext;
import com.swarmnyc.mvvmlib.navigation.NavigationManager;

import kotlin.NotImplementedError;


public class MvvmContextFragmentWrapper extends MvvmContext {
    private Fragment fragment;
    private MvvmContext parentContext;

    public MvvmContextFragmentWrapper(Fragment fragment, MvvmContext context) {
        this.fragment = fragment;
        parentContext = context;
    }


    @Override
    public NavigationManager getNavigationManager() {
        return parentContext.getNavigationManager();
    }

    @Override
    public void setNavigationManager(NavigationManager navigationManager) {
        throw new NotImplementedError();
    }

    @Override
    public void close(int result, Bundle args) {
        Fragment targetFragment = this.fragment.getTargetFragment();
        if (targetFragment != null && fragment instanceof MvvmFragment) {
            ((MvvmFragment) targetFragment).onResult(fragment.getTargetRequestCode(), result, args);
        }
        getNavigationManager().navigateBack();
    }

    @Override
    public void close() {
        getNavigationManager().navigateBack();
    }
}
