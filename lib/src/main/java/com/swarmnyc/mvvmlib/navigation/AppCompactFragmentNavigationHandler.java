package com.swarmnyc.mvvmlib.navigation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.security.InvalidParameterException;

public class AppCompactFragmentNavigationHandler implements NavigationHandler {
    private final int targetId;
    private final Class fragmentClass;

    public <T extends Fragment> AppCompactFragmentNavigationHandler(Class<T> fragmentClass, @IdRes int targetId) {
        this.fragmentClass = fragmentClass;
        this.targetId = targetId;
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
            throw new InvalidParameterException("ActivityNavigationHandler needs Activity to navigate");
        }

        AppCompatActivity activity = (AppCompatActivity) context;
        Fragment fragment;
        try {
            fragment = newFragment();
        } catch (Exception e) {
            throw new InvalidParameterException("Fragment can't be created");
        }

        if (args == null)
            args = new Bundle();

        setArgs(args);
        fragment.setArguments(args);

        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (!customTransaction()) {
            transaction.replace(targetId, fragment);
            if (fragmentManager.getBackStackEntryCount() != 0) {
                transaction.addToBackStack(getBackStackName());
            }
        }

        transaction.commit();
    }
}
