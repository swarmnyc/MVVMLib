package com.swarmnyc.mvvmlib.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.swarmnyc.mvvmlib.Keys;

import java.security.InvalidParameterException;

public class ActivityNavigationHandler implements NavigationHandler {
    private Class activity;
    private Integer requestCode;

    public <T extends Activity> ActivityNavigationHandler(Class<T> activity) {
        this.activity = activity;
    }

    public <T extends Activity> ActivityNavigationHandler(Class<T> activity, Integer requestCode) {
        this.activity = activity;
        this.requestCode = requestCode;
    }

    public void setArgs(Bundle args) {
    }

    public void navigate(Context context, Bundle args) {
        Activity activity = (Activity) context;
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
