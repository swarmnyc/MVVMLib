package com.swarmnyc.mvvmlib.sampleapp.ui;

import android.os.Bundle;

import com.swarmnyc.mvvmlib.navigation.NavigationManager;
import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.FirstViewModel;
import com.swarmnyc.mvvmlib.support.MvvmActivity;
import com.swarmnyc.mvvmlib.support.navigation.FragmentNavigationHandler;

public class OneFragmentActivity extends MvvmActivity {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_one_fragment;
    }

    @Override
    protected void buildNavigation(NavigationManager manager) {
        //Activity Level Navigation are used before Application Level

        manager.add( FirstViewModel.class, new FragmentNavigationHandler( FirstFragment.class, R.id.fragment_container){
            @Override
            protected boolean isReuseIfInBackStack() {
                return true;
            }
        });
        manager.add(SecondFragment.class, new FragmentNavigationHandler(SecondFragment.class, R.id.fragment_container, 2));
        manager.add(ThirdFragment.class, new FragmentNavigationHandler(ThirdFragment.class, R.id.fragment_container, 3){
            @Override
            public void setArgs(Bundle args) {
                args.putString("title", "Pass from OneFragmentActivity");
            }
        });

        navigateTo(FirstViewModel.class);
    }
}
