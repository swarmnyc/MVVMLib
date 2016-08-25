package com.swarmnyc.mvvmlib;

import android.app.Fragment;
import android.os.Bundle;

import com.swarmnyc.mvvmlib.navigation.NavigationManager;

public class MvvmContextFragmentWrapper extends MvvmContext {
    private Fragment fragment;
    private MvvmContext parentContext;

    public MvvmContextFragmentWrapper(Fragment fragment, MvvmContext context) {
        this.fragment = fragment;
        parentContext = context;
    }

    @Override
    public NavigationManager getNavigationManager() {
        if (navigationManager == null) {
            return parentContext.getNavigationManager();
        } else {
            return navigationManager;
        }
    }

    @Override
    public void close(int result, Bundle args) {
        Fragment targetFragment = this.fragment.getTargetFragment();
        if (targetFragment != null && fragment instanceof FragmentWrapper) {
            getNavigationManager().closeFragment((FragmentWrapper) targetFragment, fragment.getTargetRequestCode(), null, null);
        } else {
            getNavigationManager().navigateBack();
        }
    }

    @Override
    public void close() {
        getNavigationManager().closeFragment(null, fragment.getTargetRequestCode(), null, null);
    }
}
