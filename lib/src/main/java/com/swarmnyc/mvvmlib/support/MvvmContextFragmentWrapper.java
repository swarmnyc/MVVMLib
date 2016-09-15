package com.swarmnyc.mvvmlib.support;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.swarmnyc.mvvmlib.FragmentWrapper;
import com.swarmnyc.mvvmlib.MvvmContext;
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
            getNavigationManager().closeFragment((FragmentWrapper) targetFragment, fragment.getTargetRequestCode() , result, args);
        } else {
            getNavigationManager().closeFragment(null, null, null, null);
        }
    }

    @Override
    public void close() {
        getNavigationManager().closeFragment(null, null, null, null);
    }

    @Override
    public void register( final String key, final Object data )
    {
        parentContext.register( key, data );
    }

    @Override
    public <T> T resolve( final String key )
    {
        return parentContext.resolve( key );
    }
}
