package com.swarmnyc.android.mvvmlib;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.swarmnyc.android.mvvmlib.navigation.DefaultNavigationManager;
import com.swarmnyc.android.mvvmlib.navigation.NavigationManager;

import java.lang.reflect.ParameterizedType;

public abstract class MvvmAppCompatActivity<T extends MvvmViewModel> extends AppCompatActivity {
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

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = this.getIntent();
        if (intent == null) {
            onModelBinding(viewModel, null);
        } else {
            onModelBinding(viewModel, intent.getBundleExtra(Keys.ARGS));
        }
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
                onResult(requestCode, resultCode, args);
            }
        }
    }

    protected void onResult(int requestCode, int resultCode, Bundle data) {
    }

    public T getViewModel() {
        return viewModel;
    }

    protected abstract ViewDataBinding onCreateViewBinding();

    protected NavigationManager createNavigationManager() {
        return new DefaultNavigationManager();
    }

    protected void onModelBinding(T viewModel, Bundle args) {
    }

    protected void buildNavigation(NavigationManager manager) {
    }


}
