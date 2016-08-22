package com.swarmnyc.android.mvvmlib.navigation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.swarmnyc.android.mvvmlib.MvvmContext;

import java.util.HashMap;

public class DefaultNavigationManager implements NavigationManager {
    private HashMap<String, NavigationHandler> maps = new HashMap<>();
    private MvvmContext mvvmAppContext;
    private MvvmContext mvvmContext;

    public DefaultNavigationManager() {
    }

    @Override
    public void setMvvmContext(MvvmContext mvvmContext) {
        this.mvvmContext = mvvmContext;
        this.mvvmAppContext = mvvmContext.getMvvmApplicationContent();
    }

    @Override
    public void navigateTo(String path) {
        navigateTo(mvvmContext.getAndroidContext(), path, null);
    }

    @Override
    public void navigateTo(String path, Bundle args) {
        navigateTo(mvvmContext.getAndroidContext(), path, args);
    }

    @Override
    public void navigateTo(Context context, String path, Bundle args) {
        NavigationHandler navigationHandler = maps.get(path);

        if (navigationHandler == null) {
            if (mvvmAppContext != null)
                // pass to application level
                mvvmAppContext.getNavigationManager().navigateTo(context, path, args);
        } else {
            if (navigationHandler instanceof ActivityNavigationHandler) {
                ((ActivityNavigationHandler) navigationHandler).navigate((Activity) context, args);
            }
        }
    }

    @Override
    public void addNavigation(String path, NavigationHandler handler) {
        maps.put(path, handler);
    }
}
