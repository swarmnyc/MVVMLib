package com.swarmnyc.mvvmlib;

import android.os.Bundle;
import android.os.Parcelable;

public abstract class MvvmViewModel implements Parcelable
{
	private MvvmContext context;

	public MvvmContext getContext()
	{
		return context;
	}

	public void setContext( MvvmContext context )
	{
		this.context = context;
	}

	public void onResult( int requestCode, int resultCode, Bundle args )
	{
	}

	public void onInit( Bundle args )
	{
	}

	protected void navigateTo( Class path )
	{
		context.getNavigationManager().navigateTo( path );
	}

	protected void navigateTo( Class path, Bundle bundle )
	{
		context.getNavigationManager().navigateTo( path, bundle );
	}

	protected void navigateBack()
	{
		context.getNavigationManager().navigateBack();
	}
}
