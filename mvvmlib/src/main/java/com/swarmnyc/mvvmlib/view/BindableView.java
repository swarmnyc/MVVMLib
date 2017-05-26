package com.swarmnyc.mvvmlib.view;

import com.swarmnyc.mvvmlib.MvvmViewModel;

public interface BindableView<T extends MvvmViewModel> {
    T getViewModel();
    void setViewModel(T viewModel);
}
