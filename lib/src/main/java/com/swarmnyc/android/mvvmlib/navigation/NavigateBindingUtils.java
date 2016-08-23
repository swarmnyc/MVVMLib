package com.swarmnyc.android.mvvmlib.navigation;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.view.View;

import com.swarmnyc.android.mvvmlib.AndroidUtils;
import com.swarmnyc.android.mvvmlib.Keys;
import com.swarmnyc.android.mvvmlib.MvvmContext;

public class NavigateBindingUtils {
    @BindingAdapter("mvvm:navTo")
    public static void navTo(View view, final String path) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context androidContext = AndroidUtils.getContext(view);
                MvvmContext.getContext(androidContext).getNavigationManager().navigateTo(androidContext, path, null);
            }
        });
    }

    @BindingAdapter({"mvvm:navTo", "mvvm:navArg"})
    public static void navTo(View view, final String path, final String arg) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context androidContext = AndroidUtils.getContext(view);
                Bundle args = new Bundle();
                args.putString(Keys.PASS_VALUE, arg);
                MvvmContext.getContext(androidContext).getNavigationManager().navigateTo(androidContext, path, args);
            }
        });
    }

    @BindingAdapter("mvvm:close")
    public static void close(View view, final int result) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MvvmContext.getContext(view).close(result, null);
            }
        });
    }
}
