package com.swarmnyc.android.mvvmlib;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.swarmnyc.android.mvvmlib.navigation.DefaultNavigationManager;
import com.swarmnyc.android.mvvmlib.navigation.NavigationManager;

import java.lang.reflect.ParameterizedType;

public abstract class MvvmActivity<T extends MvvmViewModel> extends Activity {
    private T viewModel;
    private MvvmContext mvvmContext;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mvvmContext = new MvvmContext(this);
        mvvmContext.setNavigationManager(createNavigationManager());
        buildNavigation(mvvmContext.getNavigationManager());

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

        ViewDataBinding viewDataBinding = DataBindingUtil.setContentView(this, getLayoutResourceId());

        if (viewDataBinding == null) {
            throw new RuntimeException("MVVMLib Cannot do binding when ViewDataBinding is null");
        }

        viewDataBinding.setVariable(com.swarmnyc.android.mvvmlib.BR.viewmodel, viewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = this.getIntent();
        Bundle args = null;
        if (intent == null) {
        } else {
            args = intent.getBundleExtra(Keys.ARGS);
        }

        viewModel.onInit(args);
        onModelBinding(viewModel, args);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(Keys.VIEW_MODEL, viewModel);
    }

    @Override
    protected void onDestroy() {
        mvvmContext.destroy();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Bundle args = data.getBundleExtra(Keys.ARGS);
            if (args != null) {
                viewModel.onResult(requestCode, resultCode, args);
            }
        }
    }

    public T getViewModel() {
        return viewModel;
    }

    @LayoutRes
    protected abstract int getLayoutResourceId();

    protected NavigationManager createNavigationManager() {
        return new DefaultNavigationManager();
    }

    protected void onModelBinding(T viewModel, Bundle args) {
    }

    protected void buildNavigation(NavigationManager manager) {
    }
}
