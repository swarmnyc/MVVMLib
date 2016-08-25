package com.swarmnyc.mvvmlib.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.swarmnyc.mvvmlib.MvvmContext;

import java.security.InvalidParameterException;
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
    public boolean navigateTo(String path) {
        return navigateTo(mvvmContext.getAndroidContext(), path, null);
    }

    @Override
    public boolean navigateTo(String path, Bundle args) {
        return navigateTo(mvvmContext.getAndroidContext(), path, args);
    }

    @Override
    public boolean navigateTo(Context context, String path, Bundle args) {
        if (path == null || path.isEmpty())
            throw new InvalidParameterException("Path can't be null or empty");

        String lowerCasePath = path.toLowerCase();

        NavigationHandler navigationHandler = maps.get(lowerCasePath);

        if (navigationHandler == null) {
            if (mvvmAppContext == null) {
                Log.e("NavigationManager", "The Path " + path + " has't be registered");
                return false;
            } else {
                // pass to application level
                return mvvmAppContext.getNavigationManager().navigateTo(context, path, args);
            }
        } else {
            navigationHandler.navigate(context, args);
            return true;
        }
    }

    @Override
    public void navigateBack() {
        Context androidContext = mvvmContext.getAndroidContext();
        if (androidContext instanceof Activity) {
            ((Activity) androidContext).onBackPressed();
        }
    }

    @Override
    public NavigationManager add(String path, NavigationHandler handler) {
        if (path == null || path.isEmpty())
            throw new InvalidParameterException("Path can't be null or empty");

        if (handler == null)
            throw new InvalidParameterException("NavigationHandler can't be null or empty");

        String lowerCasePath = path.toLowerCase();

        maps.put(lowerCasePath, handler);

        return this;
    }

    @Override
    public void remove(String path) {
        if (path == null || path.isEmpty())
            throw new InvalidParameterException("Path can't be null or empty");

        String lowerCasePath = path.toLowerCase();
        maps.remove(lowerCasePath);
    }

    @Override
    public void linkTo(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        mvvmContext.getAndroidContext().startActivity(i);
    }
}
