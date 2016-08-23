package com.swarmnyc.android.mvvmlib.sampleapp;

import com.swarmnyc.android.mvvmlib.MvvmApplication;
import com.swarmnyc.android.mvvmlib.navigation.ActivityNavigationHandler;
import com.swarmnyc.android.mvvmlib.navigation.NavigationManager;
import com.swarmnyc.android.mvvmlib.sampleapp.ui.FirstActivity;
import com.swarmnyc.android.mvvmlib.sampleapp.ui.SecondActivity;
import com.swarmnyc.android.mvvmlib.sampleapp.ui.ThirdActivity;

public class MainApplication extends MvvmApplication {
    @Override
    public void buildNavigation(NavigationManager manager) {
        manager.addNavigation("MainActivity", new ActivityNavigationHandler(MainActivity.class));
        manager.addNavigation("FirstActivity", new ActivityNavigationHandler(FirstActivity.class));
        manager.addNavigation("SecondActivity", new ActivityNavigationHandler(SecondActivity.class, 2));
        manager.addNavigation("ThirdActivity", new ActivityNavigationHandler(ThirdActivity.class, 3));
    }
}
