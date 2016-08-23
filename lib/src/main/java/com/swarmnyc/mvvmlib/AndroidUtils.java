package com.swarmnyc.mvvmlib;

import android.content.Context;
import android.support.v7.widget.TintContextWrapper;
import android.view.View;

public class AndroidUtils {
    public static Context getContext(View view) {
        Context androidContext = view.getContext();
        if (androidContext instanceof TintContextWrapper) {
            androidContext = ((TintContextWrapper) androidContext).getBaseContext();
        }
        return androidContext;
    }
}
