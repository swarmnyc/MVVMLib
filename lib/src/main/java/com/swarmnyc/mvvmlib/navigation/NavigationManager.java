package com.swarmnyc.mvvmlib.navigation;

import android.content.Context;
import android.os.Bundle;

import com.swarmnyc.mvvmlib.MvvmContext;

import java.util.Map;

public interface NavigationManager {
    void setMvvmContext(MvvmContext mvvmContext);

    boolean navigateTo(String path);

    boolean navigateTo(String path, Bundle args);

    boolean navigateTo(Context context, String path, Bundle args);

    void navigateBack();

    NavigationManager add(String path, NavigationHandler handler);

    void remove(String path);

    void linkTo(String url);
}

