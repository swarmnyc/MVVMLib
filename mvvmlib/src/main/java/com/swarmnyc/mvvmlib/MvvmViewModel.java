package com.swarmnyc.mvvmlib;

import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public abstract class MvvmViewModel implements Parcelable
{
	private MvvmContext context;
	private List<MvvmViewModel> m_childVMs = new ArrayList<>();
	private Bundle m_args;

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
		m_args = args;
		for ( MvvmViewModel childVM : m_childVMs )
		{
			if (childVM.m_args == null) // avoid multiple registration
			{
				childVM.context = this.context;
				childVM.onInit( args );
			}
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

	void registerChildViewModel( MvvmViewModel child )
	{
		m_childVMs.add( child );
		if (this.m_args != null && child.m_args == null)
		{
			child.context = this.context;
			child.onInit( m_args );
		}
	}

	void registerChildViewModels( List<MvvmViewModel> children )
	{
		m_childVMs.addAll( children );
		// register the children
		if (this.m_args != null)
		{
			for ( MvvmViewModel childVM : children )
			{
				if (childVM.m_args == null) // avoid multiple registration
				{
					childVM.context = this.context;
					childVM.onInit( m_args );
				}
			}
		}
	}
}
