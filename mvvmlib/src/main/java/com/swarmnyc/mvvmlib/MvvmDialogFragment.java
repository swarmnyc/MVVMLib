package com.swarmnyc.mvvmlib;

import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class MvvmDialogFragment<T extends MvvmViewModel> extends DialogFragment implements FragmentWrapper
{
	private T viewModel;
	private MvvmContext mvvmContext;
	private boolean viewModelEnabled;
	private ViewDataBinding viewDataBinding;

	@Override
	public void onCreate(
		@Nullable Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		mvvmContext = new MvvmContextFragmentWrapper( this, MvvmContext.getContext( this.getActivity() ) );
		viewModelEnabled = ViewModelUtils.assignFromViewModel( this.getClass() );

		if ( viewModelEnabled )
		{
			if ( savedInstanceState == null )
			{
				viewModel = (T) ViewModelUtils.createViewModel( this.getClass() );
			}
			else
			{
				viewModel = savedInstanceState.getParcelable( Keys.VIEW_MODEL );
			}

			viewModel.setContext( mvvmContext );

			Bundle args = getArguments();

			viewModel.onInit( args );
			onInit( viewModel, args );
		}
	}

	@Nullable
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
	{
		setRetainInstance( true );

		if ( viewModelEnabled )
		{
			viewDataBinding = DataBindingUtil.inflate( getActivity().getLayoutInflater(),
			                                           getLayoutResourceId(),
			                                           container,
			                                           false );

			if ( viewDataBinding == null )
			{
				throw new RuntimeException( Errors.NO_VIEW_DATA_BINDING );
			}

			viewDataBinding.setVariable( com.swarmnyc.mvvmlib.BR.viewmodel, viewModel );

			return viewDataBinding.getRoot();
		}
		else
		{
			return inflater.inflate( getLayoutResourceId(), container, false );
		}
	}


	@Override
	public void onSaveInstanceState( Bundle outState )
	{
		if ( viewModelEnabled )
		{
			outState.putParcelable( Keys.VIEW_MODEL, viewModel );
		}
	}

	protected MvvmContext getMvvmContext()
	{
		return mvvmContext;
	}

	public T getViewModel()
	{
		return viewModel;
	}

	protected <T extends ViewDataBinding> T getViewDataBinding()
	{
		return (T) viewDataBinding;
	}

	@LayoutRes
	protected abstract int getLayoutResourceId();

	protected void onInit( T viewModel, Bundle args )
	{
	}

	protected void navigateTo( Class path )
	{
		mvvmContext.getNavigationManager().navigateTo( path );
	}

	protected void navigateTo( Class path, Bundle bundle )
	{
		mvvmContext.getNavigationManager().navigateTo( path, bundle );
	}

	protected void navigateBack()
	{
		mvvmContext.getNavigationManager().navigateBack();
	}

	@Override
	public void onResult( int requestCode, int resultCode, Bundle bundle )
	{
		if ( viewModelEnabled )
		{
			viewModel.onResult( requestCode, resultCode, bundle );
		}
	}
}
