package com.swarmnyc.mvvmlib;

import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public abstract class MvvmViewModel implements Parcelable
{
	private MvvmContext context;
	private List<MvvmViewModel> m_childVMs = new ArrayList<>(  );

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
		for ( MvvmViewModel childVM : m_childVMs )
		{
			childVM.context = this.context;
		    childVM.onInit( args );
		}
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

	void addChild(MvvmViewModel child) {
		m_childVMs.add( child );
	}

	void addChildren(List<MvvmViewModel> children) {
		m_childVMs.addAll( children );
	}
}
