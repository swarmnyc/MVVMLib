package com.swarmnyc.android.mvvmlib.navigation;

import android.content.Context;
import android.os.Bundle;

import com.swarmnyc.android.mvvmlib.MvvmContext;

import java.util.Map;

public interface NavigationManager {
    void setMvvmContext(MvvmContext mvvmContext);

    void navigateTo(String path);

    void navigateTo(String path, Bundle args);

    void navigateTo(Context context, String path, Bundle args);

    void addNavigation(String path, NavigationHandler handler);

    void linkTo(String url);
}

