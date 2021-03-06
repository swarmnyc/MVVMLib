package com.swarmnyc.mvvmlib.sampleapp.ui;

import android.os.Bundle;

import com.swarmnyc.mvvmlib.navigation.NavigationManager;
import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.FirstViewModel;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.ThirdViewModel;
import com.swarmnyc.mvvmlib.support.MvvmActivity;
import com.swarmnyc.mvvmlib.support.navigation.FragmentNavigationHandler;

public class TwoFragmentActivity extends MvvmActivity {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_two_fragment;
    }

    @Override
    protected void buildNavigation(NavigationManager manager) {
        //Activity Level Navigation are used before Application Level

//        manager
//                .add( FirstViewModel.class, new FragmentNavigationHandler( FirstFragment.class, R.id.fragment_container1){
//                    @Override
//                    protected boolean isReuseIfInBackStack() {
//                        return true;
//                    }
//                })
//                .add(SecondFragment.class, new FragmentNavigationHandler(SecondFragment.class, R.id.fragment_container2, 2) {
//                    @Override
//                    public String getBackStackName() {
//                        return null;
//                    }
//                })
//                .add( ThirdViewModel.class, new FragmentNavigationHandler( ThirdFragment.class, R.id.fragment_container2, 3) {
//                    @Override
//                    public void setArgs(Bundle args) {
//                        args.putString("title", "Pass from TwoFragmentActivity");
//                    }
//
//                    @Override
//                    public String getBackStackName() {
//                        return null;
//                    }
//                });
//        navigateTo(FirstViewModel.class);
    }
}
