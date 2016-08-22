package com.swarmnyc.android.mvvmlib;

import android.app.Application;

import com.swarmnyc.android.mvvmlib.navigation.DefaultNavigationManager;
import com.swarmnyc.android.mvvmlib.navigation.NavigationManager;

public class MvvmApplication extends Application {
    private MvvmContext mvvmContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mvvmContext = new MvvmContext(this);
        mvvmContext.setNavigationManager(createNavigationManager());
        buildNavigation(mvvmContext.getNavigationManager());
    }

    public NavigationManager createNavigationManager() {
        return new DefaultNavigationManager();
    }

    public void buildNavigation(NavigationManager manager) {

    }

    @Override
    public void onTerminate() {
        mvvmContext.destroy();
        super.onTerminate();
    }
}
