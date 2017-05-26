package com.swarmnyc.mvvmlib.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.swarmnyc.mvvmlib.MvvmViewModel;

public class MvvmView<T extends MvvmViewModel> extends View implements BindableView<T> {
    T viewModel;

    public MvvmView(Context context) {
        super(context);
    }

    public MvvmView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MvvmView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MvvmView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public T getViewModel() {
        return viewModel;
    }

    @Override
    public void setViewModel(T viewModel) {
        this.viewModel = viewModel;
        this.invalidate();
    }
}
