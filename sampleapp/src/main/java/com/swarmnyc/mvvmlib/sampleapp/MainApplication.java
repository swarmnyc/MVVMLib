package com.swarmnyc.mvvmlib.sampleapp;

import android.os.Bundle;

import com.swarmnyc.mvvmlib.MvvmApplication;
import com.swarmnyc.mvvmlib.navigation.ActivityNavigationHandler;
import com.swarmnyc.mvvmlib.navigation.NavigationManager;
import com.swarmnyc.mvvmlib.sampleapp.ui.BindingActivity;
import com.swarmnyc.mvvmlib.sampleapp.ui.FirstActivity;
import com.swarmnyc.mvvmlib.sampleapp.ui.OneFragmentActivity;
import com.swarmnyc.mvvmlib.sampleapp.ui.SecondActivity;
import com.swarmnyc.mvvmlib.sampleapp.ui.ThirdActivity;
import com.swarmnyc.mvvmlib.sampleapp.ui.TwoFragmentActivity;

public class MainApplication extends MvvmApplication {
    @Override
    public void buildNavigation(NavigationManager manager) {
//        manager.add(MainActivity.class, new ActivityNavigationHandler(MainActivity.class));
//
//
//        manager.add(FirstActivity.class, new ActivityNavigationHandler(FirstActivity.class));
//        manager.add(SecondActivity.class, new ActivityNavigationHandler(SecondActivity.class, 2));
//        manager.add(ThirdActivity.class, new ActivityNavigationHandler(ThirdActivity.class, 3) {
//            @Override
//            public void setArgs(Bundle args) {
//                args.putString("title", "Pass from MainApplication");
//            }
//        });
//
//        manager.add(OneFragmentActivity.class, new ActivityNavigationHandler(OneFragmentActivity.class));
//        manager.add(TwoFragmentActivity.class, new ActivityNavigationHandler(TwoFragmentActivity.class));
//        manager.add(BindingActivity.class, new ActivityNavigationHandler(BindingActivity.class));
    }
}
