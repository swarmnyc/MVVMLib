package com.swarmnyc.mvvmlib.support;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swarmnyc.mvvmlib.Errors;
import com.swarmnyc.mvvmlib.Keys;
import com.swarmnyc.mvvmlib.MvvmContext;
import com.swarmnyc.mvvmlib.MvvmViewModel;
import com.swarmnyc.mvvmlib.FragmentWrapper;
import com.swarmnyc.mvvmlib.ViewModelUtils;

public abstract class MvvmFragment<T extends MvvmViewModel> extends Fragment implements FragmentWrapper {
    private T viewModel;
    private MvvmContext mvvmContext;
    private boolean viewModelEnabled;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvvmContext = new MvvmContextFragmentWrapper(this, MvvmContext.getContext(this.getActivity()));
        viewModelEnabled = ViewModelUtils.assignFromViewModel(this.getClass());

        if (viewModelEnabled) {
            if (savedInstanceState == null) {
                viewModel = (T) ViewModelUtils.createViewModel(this.getClass());
            } else {
                viewModel = savedInstanceState.getParcelable(Keys.VIEW_MODEL);
            }

            viewModel.setContext(mvvmContext);

            Bundle args = getArguments();

            viewModel.onInit(args);
            onModelBinding(viewModel, args);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);

        if (viewModelEnabled) {
            ViewDataBinding viewDataBinding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), getLayoutResourceId(), container, false);

            if (viewDataBinding == null) {
                throw new RuntimeException(Errors.no_view_data_binding);
            }

            viewDataBinding.setVariable(com.swarmnyc.mvvmlib.BR.viewmodel, viewModel);

            return viewDataBinding.getRoot();
        } else {
            return inflater.inflate(getLayoutResourceId(), container, false);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (viewModelEnabled) {
            outState.putParcelable(Keys.VIEW_MODEL, viewModel);
        }
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

    protected void navigateBack() {
        mvvmContext.getNavigationManager().navigateBack();
    }

    public void onResult(int requestCode, int resultCode, Bundle bundle) {
        if (viewModelEnabled) {
            viewModel.onResult(requestCode, resultCode, bundle);
        }
    }
}
