package com.swarmnyc.mvvmlib.sampleapp;

import android.os.Bundle;

import com.swarmnyc.mvvmlib.MvvmApplication;
import com.swarmnyc.mvvmlib.navigation.ActivityNavigationHandler;
import com.swarmnyc.mvvmlib.navigation.NavigationManager;
import com.swarmnyc.mvvmlib.sampleapp.ui.FirstActivity;
import com.swarmnyc.mvvmlib.sampleapp.ui.SecondActivity;
import com.swarmnyc.mvvmlib.sampleapp.ui.ThirdActivity;

public class MainApplication extends MvvmApplication {
    @Override
    public void buildNavigation(NavigationManager manager) {
        manager.addNavigation("MainActivity", new ActivityNavigationHandler(MainActivity.class));
        manager.addNavigation("FirstActivity", new ActivityNavigationHandler(FirstActivity.class));
        manager.addNavigation("SecondActivity", new ActivityNavigationHandler(SecondActivity.class, 2));
        manager.addNavigation("ThirdActivity", new ActivityNavigationHandler(ThirdActivity.class, 3) {
            @Override
            public void setArgs(Bundle args) {
                args.putString("title", "Args Test");
            }
        });
    }
}
