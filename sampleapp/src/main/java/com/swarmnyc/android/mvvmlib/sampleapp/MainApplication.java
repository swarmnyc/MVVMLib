package com.swarmnyc.android.mvvmlib.sampleapp;

import android.content.Intent;

import com.swarmnyc.android.mvvmlib.MvvmApplication;
import com.swarmnyc.android.mvvmlib.navigation.ActivityNavigationHandler;
import com.swarmnyc.android.mvvmlib.navigation.NavigationManager;

public class MainApplication extends MvvmApplication {

    @Override
    public void buildNavigation(NavigationManager manager) {
        manager.addNavigation("MainActivity", new ActivityNavigationHandler(MainActivity.class));
        manager.addNavigation("SecondActivity", new ActivityNavigationHandler(SecondActivity.class) {
            @Override
            public void setArgs(Intent intent) {
                intent.putExtra("Title", "YES");
            }
        });
        manager.addNavigation("ThirdActivity", new ActivityNavigationHandler(ThirdActivity.class, 3));
    }
}
