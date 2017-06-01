package com.swarmnyc.mvvmlib.sampleapp;

import com.swarmnyc.mvvmlib.MvvmApplication;
import com.swarmnyc.mvvmlib.navigation.NavigationManager;
import com.swarmnyc.mvvmlib.sampleapp.ui.FragmentNavigationDemoActivity;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.FirstViewModel;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.FragmentNavigationDemoViewModel;

public class MainApplication extends MvvmApplication {
    @Override
    public void buildNavigation(NavigationManager manager) {

        manager.add( FragmentNavigationDemoViewModel.class, FragmentNavigationDemoActivity.class);
    }
}
