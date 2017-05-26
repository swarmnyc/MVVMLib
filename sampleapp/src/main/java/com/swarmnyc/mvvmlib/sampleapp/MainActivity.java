package com.swarmnyc.mvvmlib.sampleapp;

import android.os.Bundle;
import android.support.annotation.LayoutRes;

import com.swarmnyc.mvvmlib.navigation.ActivityNavigationHandler;
import com.swarmnyc.mvvmlib.navigation.NavigationManager;
import com.swarmnyc.mvvmlib.sampleapp.ui.*;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.FirstViewModel;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.ThirdViewModel;
import com.swarmnyc.mvvmlib.support.MvvmActivity;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.MainViewModel;
import com.swarmnyc.mvvmlib.support.navigation.AppCompatActivityNavigationHandler;

public class MainActivity extends MvvmActivity<MainViewModel> {
    @Override
    @LayoutRes
    public int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    public void buildNavigation(NavigationManager manager) {


        manager.add( FirstViewModel.class, new AppCompatActivityNavigationHandler( FirstActivity.class));
        manager.add( SecondActivity.class, new AppCompatActivityNavigationHandler( SecondActivity.class, 2));
        manager.add( ThirdViewModel.class, new AppCompatActivityNavigationHandler( ThirdActivity.class, 3) {
            @Override
            public void setArgs(Bundle args) {
                args.putString("title", "Pass from MainApplication");
            }
        });

        manager.add( OneFragmentActivity.class, new AppCompatActivityNavigationHandler( OneFragmentActivity.class));
        manager.add( TwoFragmentActivity.class, new AppCompatActivityNavigationHandler( TwoFragmentActivity.class));
        manager.add(BindingActivity.class, new AppCompatActivityNavigationHandler(BindingActivity.class));
    }
}
