package com.swarmnyc.mvvmlib;

import android.app.Application;

import com.swarmnyc.mvvmlib.navigation.DefaultNavigationManager;
import com.swarmnyc.mvvmlib.navigation.DefaultNotificationProvider;
import com.swarmnyc.mvvmlib.navigation.NavigationManager;

public class MvvmApplication extends Application {
    private MvvmContext mvvmContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mvvmContext = new MvvmContext(this);
        mvvmContext.setNavigationManager(createNavigationManager());
        mvvmContext.setNotificationProvider( new DefaultNotificationProvider( this ) );
        buildNavigation(mvvmContext.getNavigationManager());
    }

    @Override
    public void onTerminate() {
        mvvmContext.destroy();
        super.onTerminate();
    }

    protected NavigationManager createNavigationManager() {
        return new DefaultNavigationManager();
    }

    protected void buildNavigation(NavigationManager manager) {

    }
}
