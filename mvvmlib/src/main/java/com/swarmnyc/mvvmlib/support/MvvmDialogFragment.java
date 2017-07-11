package com.swarmnyc.mvvmlib.support;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.swarmnyc.mvvmlib.BR;
import com.swarmnyc.mvvmlib.Errors;
import com.swarmnyc.mvvmlib.FragmentWrapper;
import com.swarmnyc.mvvmlib.Keys;
import com.swarmnyc.mvvmlib.MvvmContext;
import com.swarmnyc.mvvmlib.MvvmViewModel;
import com.swarmnyc.mvvmlib.ViewModelUtils;

public abstract class MvvmDialogFragment<T extends MvvmViewModel> extends DialogFragment implements FragmentWrapper {
    private T viewModel;
    private MvvmContext mvvmContext;
    private boolean viewModelEnabled;
    private ViewDataBinding viewDataBinding;

    @Override
    public void onCreate(
            @Nullable Bundle savedInstanceState) {
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
            onInit(viewModel, args);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);

        if (viewModelEnabled) {
            viewDataBinding = DataBindingUtil.inflate(getActivity().getLayoutInflater(),
                    getLayoutResourceId(),
                    container,
                    false);

            if (viewDataBinding == null) {
                throw new RuntimeException(Errors.NO_VIEW_DATA_BINDING);
            }

            viewDataBinding.setVariable(BR.viewmodel, viewModel);

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

    protected MvvmContext getMvvmContext() {
        return mvvmContext;
    }

    public T getViewModel() {
        return viewModel;
    }

    protected <T extends ViewDataBinding> T getViewDataBinding() {
        return (T) viewDataBinding;
    }

    @LayoutRes
    protected abstract int getLayoutResourceId();

    protected void onInit(T viewModel, Bundle args) {
    }

    protected <TVM extends MvvmViewModel> void navigateTo(Class<TVM> viewModelClass) {
        mvvmContext.getNavigationManager().navigateTo(viewModelClass);
    }

    protected <TVM extends MvvmViewModel> void navigateTo(Class<TVM> viewModelClass, Bundle bundle) {
        mvvmContext.getNavigationManager().navigateTo(viewModelClass, bundle);
    }

    protected void navigateBack() {
        mvvmContext.getNavigationManager().navigateBack();
    }

    @Override
    public void onResult(int requestCode, int resultCode, Bundle bundle) {
        if (viewModelEnabled) {
            viewModel.onResult(requestCode, resultCode, bundle);
        }
    }
}
