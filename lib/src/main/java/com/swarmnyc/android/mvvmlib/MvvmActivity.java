package com.swarmnyc.android.mvvmlib;

import android.app.Activity;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
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

        ViewDataBinding viewDataBinding = onCreateViewBinding();
        if (viewDataBinding == null) {
            throw new RuntimeException("MVVMLib Cannot do binding when ViewDataBinding is null");
        }

        viewDataBinding.setVariable(com.swarmnyc.android.mvvmlib.BR.viewmodel, viewModel);
    }

    public T getViewModel() {
        return viewModel;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(Keys.VIEW_MODEL, viewModel);
    }

    public abstract ViewDataBinding onCreateViewBinding();

    @Override
    protected void onDestroy() {
        mvvmContext.destroy();
        super.onDestroy();
    }

    public NavigationManager createNavigationManager() {
        return new DefaultNavigationManager();
    }

    public abstract void buildNavigation(NavigationManager manager);
}
