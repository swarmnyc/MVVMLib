package com.swarmnyc.android.mvvmlib.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.swarmnyc.android.mvvmlib.Keys;

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

    public void setArgs(Intent intent) {

    }

    public void navigate(Activity context, Bundle args) {
        Intent intent = new Intent(context, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (args != null) {
            intent.putExtra(Keys.ARGS, args);
        }

        setArgs(intent);
        if (requestCode == null) {
            context.startActivity(intent);
        } else {
            context.startActivityForResult(intent, requestCode);
        }
    }
}
