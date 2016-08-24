package com.swarmnyc.mvvmlib;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;

public abstract class MvvmAppCompatFragment<T extends MvvmViewModel> extends Fragment implements MvvmFragmentWrapper {
    private T viewModel;
    private MvvmFragmentContext mvvmContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mvvmContext = new MvvmFragmentContext(this);
        if (savedInstanceState == null) {
            try {
                viewModel = (T) ((Class) ((ParameterizedType) this.getClass().
                        getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
            } catch (Exception e) {
                throw new RuntimeException("MVVMLib Cannot create ViewModel", e);
            }
        } else {
            viewModel = savedInstanceState.getParcelable(Keys.VIEW_MODEL);
        }

        viewModel.setContext(mvvmContext);

        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), getLayoutResourceId(), null, false);

        if (viewDataBinding == null) {
            throw new RuntimeException("MVVMLib Cannot do binding when ViewDataBinding is null");
        }

        viewDataBinding.setVariable(com.swarmnyc.mvvmlib.BR.viewmodel, viewModel);

        return viewDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Bundle args = getArguments();

        viewModel.onInit(args);
        onModelBinding(viewModel, args);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(Keys.VIEW_MODEL, viewModel);
    }

    @Override
    public void onDestroy() {
        mvvmContext.destroy();
        super.onDestroy();
    }

    public T getViewModel() {
        return viewModel;
    }

    @LayoutRes
    protected abstract int getLayoutResourceId();

    protected void onModelBinding(T viewModel, Bundle args) {
    }

    protected void navigateTo(String path) {
        mvvmContext.getNavigationManager().navigateTo(path);
    }

    protected void navigateTo(String path, Bundle bundle) {
        mvvmContext.getNavigationManager().navigateTo(path, bundle);
    }
}
