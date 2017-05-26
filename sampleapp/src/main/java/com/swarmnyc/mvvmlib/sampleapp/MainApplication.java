package com.swarmnyc.mvvmlib.sampleapp;

import android.os.Bundle;

import com.swarmnyc.mvvmlib.MvvmApplication;
import com.swarmnyc.mvvmlib.navigation.NavigationManager;
import com.swarmnyc.mvvmlib.sampleapp.ui.BindingActivity;
import com.swarmnyc.mvvmlib.sampleapp.ui.FirstActivity;
import com.swarmnyc.mvvmlib.sampleapp.ui.OneFragmentActivity;
import com.swarmnyc.mvvmlib.sampleapp.ui.SecondActivity;
import com.swarmnyc.mvvmlib.sampleapp.ui.ThirdActivity;
import com.swarmnyc.mvvmlib.sampleapp.ui.TwoFragmentActivity;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.FirstViewModel;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.ThirdViewModel;
import com.swarmnyc.mvvmlib.support.navigation.ActivityNavigationHandler;

public class MainApplication extends MvvmApplication {
    @Override
    public void buildNavigation(NavigationManager manager) {

        manager.add(FirstViewModel.class, FirstActivity.class);
//        manager.add(SecondActivity.class, new ActivityNavigationHandler(SecondActivity.class, 2));
//        manager.add(ThirdViewModel.class, new ActivityNavigationHandler(ThirdActivity.class, 3) {
//            @Override
//            public void setArgs(Bundle args) {
//                args.putString("title", "Pass from MainApplication");
//            }
//        });
//
//        manager.add(OneFragmentActivity.class, new ActivityNavigationHandler(OneFragmentActivity.class));
//        manager.add(TwoFragmentActivity.class, new ActivityNavigationHandler(TwoFragmentActivity.class));
//        manager.add(BindingActivity.class, new ActivityNavigationHandler(BindingActivity.class));

//        manager.add("MainActivity", new ActivityNavigationHandler(MainActivity.class));
//        manager.add("First", new ActivityNavigationHandler(FirstActivity.class));
//        manager.add("Second", new ActivityNavigationHandler(SecondActivity.class, 2));
//        manager.add("Third", new ActivityNavigationHandler(ThirdActivity.class, 3) {
//            @Override
//            public void setArgs(Bundle args) {
//                args.putString("title", "Pass from MainApplication");
//            }
//        });
//
//        manager.add("OneFragment", new ActivityNavigationHandler(OneFragmentActivity.class));
//        manager.add("TwoFragment", new ActivityNavigationHandler(TwoFragmentActivity.class));
//        manager.add("Binding", new ActivityNavigationHandler(BindingActivity.class));
//        manager.add("BindableView", new ActivityNavigationHandler(BindableActivity.class));
    }
}
