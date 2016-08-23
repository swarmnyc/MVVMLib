package com.swarmnyc.mvvmlib.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.swarmnyc.mvvmlib.MvvmContext;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

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
        if (path == null || path.isEmpty())
            throw new InvalidParameterException("Path can't be null or empty");

        path = path.toLowerCase();

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
        if (path == null || path.isEmpty())
            throw new InvalidParameterException("Path can't be null or empty");

        if (handler == null)
            throw new InvalidParameterException("NavigationHandler can't be null or empty");

        path = path.toLowerCase();

        maps.put(path, handler);
    }

    @Override
    public void linkTo(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        mvvmContext.getAndroidContext().startActivity(i);
    }
}
