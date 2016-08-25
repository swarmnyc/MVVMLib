package com.swarmnyc.mvvmlib.sampleapp;

import android.os.Bundle;

import com.swarmnyc.mvvmlib.MvvmApplication;
import com.swarmnyc.mvvmlib.navigation.ActivityNavigationHandler;
import com.swarmnyc.mvvmlib.navigation.NavigationManager;
import com.swarmnyc.mvvmlib.sampleapp.ui.FirstActivity;
import com.swarmnyc.mvvmlib.sampleapp.ui.OneFragmentActivity;
import com.swarmnyc.mvvmlib.sampleapp.ui.SecondActivity;
import com.swarmnyc.mvvmlib.sampleapp.ui.ThirdActivity;

public class MainApplication extends MvvmApplication {
    @Override
    public void buildNavigation(NavigationManager manager) {
        manager.add("MainActivity", new ActivityNavigationHandler(MainActivity.class));


        manager.add("First", new ActivityNavigationHandler(FirstActivity.class));
        manager.add("Second", new ActivityNavigationHandler(SecondActivity.class, 2));
        manager.add("Third", new ActivityNavigationHandler(ThirdActivity.class, 3) {
            @Override
            public void setArgs(Bundle args) {
                args.putString("title", "Pass from MainApplication");
            }
        });

        manager.add("OneFragment", new ActivityNavigationHandler(OneFragmentActivity.class));
    }
}
