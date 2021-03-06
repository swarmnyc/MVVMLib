package com.swarmnyc.mvvmlib;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;

import com.swarmnyc.mvvmlib.navigation.DefaultNavigationManager;
import com.swarmnyc.mvvmlib.navigation.DefaultNotificationProvider;
import com.swarmnyc.mvvmlib.navigation.NavigationManager;

public abstract class MvvmActivity<T extends MvvmViewModel> extends Activity implements IMvvmActivity {
    public static final String TAG = "MvvmActivity";
    private T viewModel;
    private MvvmContext mvvmContext;
    private boolean viewModelEnabled;
    private ViewDataBinding viewDataBinding;

    public boolean isViewModelEnabled() {
        return viewModelEnabled;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvvmContext = new MvvmContext(this);
        mvvmContext.setNavigationManager(createNavigationManager());
        mvvmContext.setNotificationProvider(new DefaultNotificationProvider(this));
        buildNavigation(mvvmContext.getNavigationManager());

        viewModelEnabled = ViewModelUtils.assignFromViewModel(this.getClass());
        if (viewModelEnabled) {
            // Use ViewModel
            if (savedInstanceState == null) {
                viewModel = (T) ViewModelUtils.createViewModel(this.getClass());
            } else {
                viewModel = savedInstanceState.getParcelable(Keys.VIEW_MODEL);
            }

            viewModel.setContext(mvvmContext);

            viewDataBinding = DataBindingUtil.setContentView(this, getLayoutResourceId());

            if (viewDataBinding == null) {
                throw new RuntimeException(Errors.NO_VIEW_DATA_BINDING);
            }

            viewDataBinding.setVariable(com.swarmnyc.mvvmlib.BR.viewmodel, viewModel);
        } else {
            Log.w(TAG, Errors.NO_VIEWMODEL_TYPE);
            this.setContentView(getLayoutResourceId());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (viewModelEnabled) {
            Intent intent = this.getIntent();
            Bundle args = null;
            if (intent != null) {
                args = intent.getBundleExtra(Keys.ARGS);
            }

            viewModel.onInit(args);
            onInit(viewModel, args);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (viewModelEnabled) {
            outState.putParcelable(Keys.VIEW_MODEL, viewModel);
        }
    }

    @Override
    protected void onDestroy() {
        mvvmContext.destroy();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (viewModelEnabled && data != null) {
            Bundle args = data.getBundleExtra(Keys.ARGS);
            if (args != null) {
                viewModel.onResult(requestCode, resultCode, args);
            }
        }
    }

    public T getViewModel() {
        return viewModel;
    }

    protected MvvmContext getMvvmContext() {
        return mvvmContext;
    }

    protected <T extends ViewDataBinding> T getViewDataBinding() {
        return (T) viewDataBinding;
    }

    @LayoutRes
    protected abstract int getLayoutResourceId();

    protected NavigationManager createNavigationManager() {
        return new DefaultNavigationManager();
    }

    protected void onInit(T viewModel, Bundle args) {
    }

    protected void buildNavigation(NavigationManager manager) {
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
}
