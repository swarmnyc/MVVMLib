package com.swarmnyc.mvvmlib.support.navigation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.swarmnyc.mvvmlib.Errors;
import com.swarmnyc.mvvmlib.Keys;
import com.swarmnyc.mvvmlib.navigation.NavigationHandler;

import java.security.InvalidParameterException;

public class FragmentNavigationHandler implements NavigationHandler {
    public static final String FTAG = "fragmentTag";
    public static final String TAG = "NavigationHandler";
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


    protected boolean isReuseIfInBackStack() {
        return false;
    }

    @Override
    public void navigate(Context context, Bundle args) {
        if (!(context instanceof AppCompatActivity)) {
            throw new InvalidParameterException(Errors.FNH_NEED_ACTIVITY);
        }

        AppCompatActivity activity = (AppCompatActivity) context;
        Fragment fragment;
        try {
            fragment = newFragment();
        } catch (Exception e) {
            throw new InvalidParameterException(Errors.FAIL_CREATE_FRAGMENT);
        }

        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        if (isReuseIfInBackStack()) {
            Fragment currentFragment;
            while ((currentFragment = fragmentManager.findFragmentById(layoutId)) != null) {
                if (fragmentClass.isInstance(currentFragment)) {
                    return;
                }

                Bundle fargs = currentFragment.getArguments();
                if (fargs != null && fargs.getInt(Keys.TRANSACTION_ID, -1) >= 0) {
                    fragmentManager.popBackStackImmediate(fargs.getInt(Keys.TRANSACTION_ID), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } else {
                    Log.w(TAG, Errors.UNEXPECTED);
                    break;
                }
            }
        }

        if (requestCode != null) {
            Fragment lastFragment = fragmentManager.findFragmentByTag(FTAG);
            fragment.setTargetFragment(lastFragment, requestCode);
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (!customTransaction()) {
            transaction.replace(layoutId, fragment, FTAG);
            if (getBackStackName() != null && fragmentManager.findFragmentById(layoutId) != null) {
                transaction.addToBackStack(getBackStackName());
            }
        }

        int id = transaction.commit();

        if (args == null)
            args = new Bundle();

        args.putInt(Keys.TRANSACTION_ID, id);
        args.putInt(Keys.LAYOUT_ID, layoutId);

        setArgs(args);
        fragment.setArguments(args);
    }
}
