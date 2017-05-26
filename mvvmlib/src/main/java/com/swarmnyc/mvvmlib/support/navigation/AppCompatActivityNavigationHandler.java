package com.swarmnyc.mvvmlib.support.navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.swarmnyc.mvvmlib.Keys;
import com.swarmnyc.mvvmlib.navigation.NavigationHandler;

import java.security.InvalidParameterException;

public class AppCompatActivityNavigationHandler implements NavigationHandler {
    private Class activity;
    private Integer requestCode;

    public <T extends AppCompatActivity> AppCompatActivityNavigationHandler( Class<T> activity) {
        this.activity = activity;
    }

    public <T extends AppCompatActivity> AppCompatActivityNavigationHandler( Class<T> activity, Integer requestCode) {
        this.activity = activity;
        this.requestCode = requestCode;
    }

    public void setArgs(Bundle args) {
    }

    public void navigate(Context context, Bundle args) {
        AppCompatActivity activity = (AppCompatActivity) context;
        if (activity==null)
            throw new InvalidParameterException("ActivityNavigationHandler needs Activity to navigate");

        Intent intent = new Intent(activity, this.activity);

        if (args == null)
            args = new Bundle();

        setArgs(args);

        intent.putExtra(Keys.ARGS, args);

        if (requestCode == null) {
            activity.startActivity(intent);
        } else {
            activity.startActivityForResult(intent, requestCode);
        }
    }

}
