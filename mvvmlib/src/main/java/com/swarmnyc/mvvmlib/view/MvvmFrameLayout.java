package com.swarmnyc.mvvmlib.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.swarmnyc.mvvmlib.BR;
import com.swarmnyc.mvvmlib.MvvmContext;
import com.swarmnyc.mvvmlib.MvvmViewModel;
import com.swarmnyc.mvvmlib.ViewModelUtils;

public abstract class MvvmFrameLayout<T extends MvvmViewModel> extends FrameLayout implements BindableView<T> {
    private ViewDataBinding viewDataBinding;
    private T viewModel;

    public MvvmFrameLayout(Context context) {
        super(context);
        init();
    }

    public MvvmFrameLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MvvmFrameLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MvvmFrameLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @SuppressWarnings("unchecked")
    private void init() {
        if (isInEditMode()) {
            inflate(getContext(), getLayoutResourceId(), this);
        }
    }

    @LayoutRes
    protected abstract int getLayoutResourceId();

    @Override
    public T getViewModel() {
        return viewModel;
    }

    @Override
    public void setViewModel(T viewModel) {
        this.viewModel = viewModel;
        if (this.viewModel.getContext() == null) {
            this.viewModel.setContext(new MvvmContext(this.getContext()));
        }

        if (viewDataBinding == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), this, true);
        }

        this.viewDataBinding.setVariable(BR.viewmodel, viewModel);
    }


}
