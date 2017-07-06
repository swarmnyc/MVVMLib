package com.swarmnyc.mvvmlib.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;

import com.swarmnyc.mvvmlib.FragmentWrapper;
import com.swarmnyc.mvvmlib.IMvvmActivity;
import com.swarmnyc.mvvmlib.IMvvmFragment;
import com.swarmnyc.mvvmlib.Keys;
import com.swarmnyc.mvvmlib.MvvmActivity;
import com.swarmnyc.mvvmlib.MvvmContext;
import com.swarmnyc.mvvmlib.MvvmFragment;
import com.swarmnyc.mvvmlib.MvvmViewModel;

import java.security.InvalidParameterException;
import java.util.HashMap;

public class DefaultNavigationManager implements NavigationManager {
    private HashMap<Class, NavigationHandler> maps = new HashMap<>();
    private MvvmContext mvvmAppContext;
    private MvvmContext mvvmContext;

    public DefaultNavigationManager() {
    }

    @Override
    public void setMvvmContext(MvvmContext mvvmContext) {
        this.mvvmContext = mvvmContext;
        this.mvvmAppContext = mvvmContext.getMvvmApplicationContext();
    }

    @Override
    public <T extends MvvmViewModel> boolean navigateTo(Class<T> viewModelClass) {
        return navigateTo(mvvmContext.getAndroidContext(), viewModelClass, null);
    }

    @Override
    public <T extends MvvmViewModel> boolean navigateTo(Class<T> viewModelClass, Bundle args) {
        return navigateTo(mvvmContext.getAndroidContext(), viewModelClass, args);
    }

    @Override
    public <T extends MvvmViewModel> boolean navigateTo(Context context, Class<T> viewModelClass, Bundle args) {
        if (viewModelClass == null) {
            throw new InvalidParameterException("Path can't be null or empty");
        }


        NavigationHandler navigationHandler = maps.get(viewModelClass);

        if (navigationHandler == null) {
            if (mvvmAppContext == null) {
                Log.e("NavigationManager", "The Path " + viewModelClass + " has't be registered");
                return false;
            } else {
                // pass to application level
                return mvvmAppContext.getNavigationManager().navigateTo(context, viewModelClass, args);
            }
        } else {
            navigationHandler.navigate(context, args);
            return true;
        }
    }

    @Override
    public <T extends MvvmViewModel> void dismiss(final Class<T> viewModelClass) {
        Context context = mvvmContext.getAndroidContext();

        NavigationHandler navigationHandler = maps.get(viewModelClass);

        if (navigationHandler instanceof DialogFragmentNavigationHandler) {
            ((DialogFragmentNavigationHandler) navigationHandler).dismiss(context);
        }
        if (navigationHandler instanceof com.swarmnyc.mvvmlib.support.navigation.DialogFragmentNavigationHandler) {
            ((com.swarmnyc.mvvmlib.support.navigation.DialogFragmentNavigationHandler) navigationHandler).dismiss(context, null);
        }
    }

    @Override public <T extends MvvmViewModel> void dismiss(Class<T> viewModelClass, Bundle args) {
        Context context = mvvmContext.getAndroidContext();

        NavigationHandler navigationHandler = maps.get(viewModelClass);

        if (navigationHandler instanceof DialogFragmentNavigationHandler) {
            ((DialogFragmentNavigationHandler) navigationHandler).dismiss(context);
        }
        if (navigationHandler instanceof com.swarmnyc.mvvmlib.support.navigation.DialogFragmentNavigationHandler) {
            ((com.swarmnyc.mvvmlib.support.navigation.DialogFragmentNavigationHandler) navigationHandler).dismiss(context, args);
        }

    }

    @Override
    public void closeFragment(FragmentWrapper fragment, Integer targetRequestCode, Integer result, Bundle args) {
        if (fragment != null) {
            fragment.onResult(targetRequestCode, result, args);
        }

        navigateBack();
    }

    @Override
    public <T extends MvvmViewModel> void add(Class<T> viewModelClass, NavigationHandler handler) {
        if (viewModelClass == null) {
            throw new InvalidParameterException("ViewModel can't be null or empty");
        }

        if (handler == null) {
            throw new InvalidParameterException("NavigationHandler can't be null or empty");
        }


        maps.put(viewModelClass, handler);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T1 extends MvvmViewModel, T2 extends IMvvmActivity> NavigationHandler add(Class<T1> viewModelClass, Class<T2> activity) {
        NavigationHandler handler = null;
        Class superClass = activity.getSuperclass();
        if (superClass.isAssignableFrom(MvvmActivity.class)) {
            handler = new ActivityNavigationHandler((Class<MvvmActivity>) activity);
        } else if (superClass.isAssignableFrom(com.swarmnyc.mvvmlib.support.MvvmActivity.class)) {
            handler = new com.swarmnyc.mvvmlib.support.navigation.ActivityNavigationHandler((Class<com.swarmnyc.mvvmlib.support.MvvmActivity>) activity);
        }

        if (handler != null) {
            add(viewModelClass, handler);
        } else {
            throw new InvalidParameterException("Unsupported Activity");
        }

        return handler;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T1 extends MvvmViewModel, T2 extends IMvvmFragment> NavigationHandler add(Class<T1> viewModelClass, Class<T2> fragment, @IdRes int layoutId) {
        NavigationHandler handler = null;
        Class superClass = fragment.getSuperclass();
        if (superClass.isAssignableFrom(MvvmFragment.class)) {
            handler = new FragmentNavigationHandler((Class<MvvmFragment>) fragment, layoutId);
        } else if (superClass.isAssignableFrom(com.swarmnyc.mvvmlib.support.MvvmFragment.class)) {
            handler = new com.swarmnyc.mvvmlib.support.navigation.FragmentNavigationHandler((Class<com.swarmnyc.mvvmlib.support.MvvmFragment>) fragment, layoutId);
        }

        if (handler != null) {
            add(viewModelClass, handler);
        } else {
            throw new InvalidParameterException("Unsupported Activity");
        }

        return handler;
    }

    @Override
    public <T extends MvvmViewModel> void remove(Class<T> viewModelClass) {
        if (viewModelClass == null) {
            throw new InvalidParameterException("Path can't be null or empty");
        }

        maps.remove(viewModelClass);
    }

    @Override
    public void navigateBack() {
        Context androidContext = mvvmContext.getAndroidContext();
        if (androidContext instanceof Activity) {
            ((Activity) androidContext).onBackPressed();
        }
    }

    @Override
    public void closeActivity(Integer result, Bundle args) {
        Context androidContext = mvvmContext.getAndroidContext();
        if (androidContext instanceof Activity) {
            Activity activity = (Activity) androidContext;
            if (result != null) {
                if (args == null) {
                    activity.setResult(result);
                } else {
                    Intent intent = new Intent();
                    intent.putExtra(Keys.ARGS, args);
                    activity.setResult(result, intent);
                }
            }

            activity.finish();
        }
    }

    @Override
    public void linkTo(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        mvvmContext.getAndroidContext().startActivity(i);
    }
}
