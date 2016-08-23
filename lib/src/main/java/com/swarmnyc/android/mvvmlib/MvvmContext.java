package com.swarmnyc.android.mvvmlib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.swarmnyc.android.mvvmlib.navigation.NavigationManager;

import java.security.InvalidParameterException;
import java.util.HashMap;

public class MvvmContext {
    private static Class navigationManagerClass;
    private static HashMap<Context, MvvmContext> contextHashMap = new HashMap<>();

    public static <T extends NavigationManager> void setNavigationManager(Class<T> navigationManagerClass) {
        MvvmContext.navigationManagerClass = navigationManagerClass;
    }

    public static MvvmContext getContext(View view) {
        Context androidContext = AndroidUtils.getContext(view);

        return getContext(androidContext);
    }

    public static MvvmContext getContext(Context androidContext) {
        MvvmContext context = MvvmContext.contextHashMap.get(androidContext);

        if (context == null) {
            context = MvvmContext.contextHashMap.get(androidContext.getApplicationContext());
        }

        if (context == null)
            throw new RuntimeException("In order to use MVVMLib, you have to new a MvvmContext");

        return context;
    }

    private NavigationManager navigationManager;
    private Context androidContext;

    public MvvmContext(Context androidContext) {
        if (androidContext == null) {
            throw new InvalidParameterException("androidContext can't be null");
        }
        this.androidContext = androidContext;
        contextHashMap.put(androidContext, this);
    }

    public void destroy() {
        contextHashMap.remove(androidContext);
    }

    public MvvmContext getMvvmApplicationContent() {
        Context context = androidContext.getApplicationContext();
        if (androidContext == context) {
            return null;
        } else {
            return contextHashMap.get(context);
        }
    }

    public Context getAndroidContext() {
        return androidContext;
    }

    public NavigationManager getNavigationManager() {
        return navigationManager;
    }

    public void setNavigationManager(NavigationManager navigationManager) {
        this.navigationManager = navigationManager;
        this.navigationManager.setMvvmContext(this);
    }

    public void close(int result, Bundle args) {
        if (androidContext instanceof Activity) {
            Activity activity = (Activity) this.androidContext;
            if (args == null) {
                activity.setResult(result);
            } else {
                Intent intent = new Intent();
                intent.putExtra(Keys.ARGS, args);
                activity.setResult(result);
            }
            activity.finish();
        }
    }

    public void close() {
        if (androidContext instanceof Activity) {
            ((Activity) androidContext).finish();
        }
    }
}
