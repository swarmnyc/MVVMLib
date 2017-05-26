package com.swarmnyc.mvvmlib.navigation;

import android.databinding.BindingAdapter;
import android.view.View;

import com.swarmnyc.mvvmlib.MvvmContext;

public class NavigateBindingUtils {
    public interface NavToHandler {
        void action();
    }

    @BindingAdapter("mvvm:navTo")
    public static void navTo(View view, final NavToHandler handler) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.action();
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
