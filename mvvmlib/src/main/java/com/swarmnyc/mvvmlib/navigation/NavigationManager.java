package com.swarmnyc.mvvmlib.navigation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;

import com.swarmnyc.mvvmlib.FragmentWrapper;
import com.swarmnyc.mvvmlib.IMvvmActivity;
import com.swarmnyc.mvvmlib.IMvvmFragment;
import com.swarmnyc.mvvmlib.MvvmContext;
import com.swarmnyc.mvvmlib.MvvmViewModel;

public interface NavigationManager {
    void setMvvmContext(MvvmContext mvvmContext);

    <T extends MvvmViewModel> boolean navigateTo(Class<T> viewModelClass);

    <T extends MvvmViewModel> boolean navigateTo(Class<T> viewModelClass, Bundle args);

    <T extends MvvmViewModel> boolean navigateTo(Context context, Class<T> viewModelClass, Bundle args);

    <T extends MvvmViewModel> void add(Class<T> viewModelClass, NavigationHandler handler);

    <T1 extends MvvmViewModel, T2 extends IMvvmActivity> NavigationHandler add(Class<T1> viewModelClass, Class<T2> target);

    <T1 extends MvvmViewModel, T2 extends IMvvmFragment> NavigationHandler add(Class<T1> viewModelClass, Class<T2> target, @LayoutRes int layoutId);

    <T extends MvvmViewModel> void remove(Class<T> viewModelClass);

    <T extends MvvmViewModel> void dismiss(final Class<T> viewModelClass);

    <T extends MvvmViewModel> void dismiss(final Class<T> viewModelClass, Bundle args);

    void navigateBack();

    void closeActivity(Integer result, Bundle args);

    void closeFragment(FragmentWrapper fragment, Integer targetRequestCode, Integer result, Bundle args);

    void linkTo(String url);
}

