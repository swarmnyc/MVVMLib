package com.swarmnyc.mvvmlib.support.navigation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.swarmnyc.mvvmlib.Errors;
import com.swarmnyc.mvvmlib.navigation.NavigationHandler;

import java.security.InvalidParameterException;

public class FragmentNavigationHandler implements NavigationHandler {
    public static final String FTAG = "fragmentTag";
    private final int layoutId;
    private final Class fragmentClass;
    private Integer requestCode;

    public <T extends Fragment> FragmentNavigationHandler(Class<T> fragmentClass, @IdRes int layoutId) {
        this.fragmentClass = fragmentClass;
        this.layoutId = layoutId;
    }

    public <T extends Fragment> FragmentNavigationHandler(Class<T> fragmentClass, @IdRes int layoutId, int requestCode) {
        this.fragmentClass = fragmentClass;
        this.layoutId = layoutId;
        this.requestCode = requestCode;
    }

    public void setArgs(Bundle args) {

    }

    public Fragment newFragment() throws IllegalAccessException, InstantiationException {
        return (Fragment) fragmentClass.newInstance();
    }

    public String getBackStackName() {
        return fragmentClass.getName();
    }

    protected boolean customTransaction() {
        return false;
    }

    @Override
    public void navigate(Context context, Bundle args) {
        if (!(context instanceof AppCompatActivity)) {
            throw new InvalidParameterException(Errors.fnh_need_activity);
        }

        AppCompatActivity activity = (AppCompatActivity) context;
        Fragment fragment;
        try {
            fragment = newFragment();
        } catch (Exception e) {
            throw new InvalidParameterException(Errors.fail_create_fragment);
        }

        if (args == null)
            args = new Bundle();

        setArgs(args);
        fragment.setArguments(args);

        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        if (requestCode != null) {
            Fragment lastFragment = fragmentManager.findFragmentByTag(FTAG);
            fragment.setTargetFragment(lastFragment, requestCode);
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (!customTransaction()) {
            transaction.replace(layoutId, fragment, FTAG);
            if (fragmentManager.findFragmentById(layoutId) != null) {
                transaction.addToBackStack(getBackStackName());
            }
        }

        transaction.commit();
    }
}
