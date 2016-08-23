package com.swarmnyc.android.mvvmlib.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.swarmnyc.android.mvvmlib.Keys;

import java.util.Map;

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

    public void setArgs(Intent intent) {}

    public void navigate(Activity activity, Bundle args) {
        Intent intent = new Intent(activity, this.activity);
        if (args != null) {
            intent.putExtra(Keys.ARGS, args);
        }

        setArgs(intent);
        if (requestCode == null) {
            activity.startActivity(intent);
        } else {
            activity.startActivityForResult(intent, requestCode);
        }
    }
}
