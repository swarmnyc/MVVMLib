package com.swarmnyc.mvvmlib.navigation;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;

import com.swarmnyc.mvvmlib.Errors;
import com.swarmnyc.mvvmlib.Keys;

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

    protected boolean customTransaction(FragmentTransaction transaction) {
        return false;
    }


    protected boolean reuseIfInBackStack() {
        return false;
    }

    @Override
    public void navigate(Context context, Bundle args) {
        if (!(context instanceof Activity)) {
            throw new InvalidParameterException(Errors.FNH_NEED_ACTIVITY);
        }

        Activity activity = (Activity) context;
        Fragment fragment;
        try {
            fragment = newFragment();
        } catch (Exception e) {
            throw new InvalidParameterException(Errors.FAIL_CREATE_FRAGMENT);
        }

        FragmentManager fragmentManager = activity.getFragmentManager();

        if (reuseIfInBackStack()) {
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

        if (!customTransaction(transaction)) {
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
