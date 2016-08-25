package com.swarmnyc.mvvmlib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;

import com.swarmnyc.mvvmlib.binding.image.ImageBinder;
import com.swarmnyc.mvvmlib.navigation.NavigationManager;

import java.security.InvalidParameterException;
import java.util.HashMap;

public class MvvmContext {
    private static Class navigationManagerClass;
    private static HashMap<Context, MvvmContext> contextHashMap = new HashMap<>();
    private ImageBinder imageBinder;

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
            throw new RuntimeException(Errors.no_context);

        return context;
    }

    protected NavigationManager navigationManager;
    protected Context androidContext;

    protected MvvmContext(){

    }

    public MvvmContext(Context androidContext) {
        if (androidContext == null) {
            throw new InvalidParameterException( Errors.is_null("androidContext"));
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
                activity.setResult(result, intent);
            }
            activity.finish();
        }
    }

    public void close() {
        if (androidContext instanceof Activity) {
            ((Activity) androidContext).finish();
        }
    }

    public ImageBinder getImageBinder() {
        return imageBinder;
    }
}
