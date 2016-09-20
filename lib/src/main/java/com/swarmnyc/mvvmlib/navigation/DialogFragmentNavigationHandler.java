package com.swarmnyc.mvvmlib.navigation;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import com.swarmnyc.mvvmlib.Errors;

import java.security.InvalidParameterException;

public class DialogFragmentNavigationHandler implements NavigationHandler
{
	private final Class fragmentClass;


	public <T extends Fragment> DialogFragmentNavigationHandler(
		Class<T> fragmentClass
	)

	{
		this.fragmentClass = fragmentClass;
	}


	public void setArgs( Bundle args )
	{

	}

	public String getBackStackName()
	{
		return fragmentClass.getName();
	}


	@Override
	public void navigate( Context context, Bundle args )
	{
		if ( !( context instanceof Activity ) )
		{
			throw new InvalidParameterException( Errors.FNH_NEED_ACTIVITY );
		}

		Activity activity = (Activity) context;
		DialogFragment dialogFragment;
		try
		{
			dialogFragment = (DialogFragment) fragmentClass.newInstance();
			dialogFragment.setCancelable( false );
		}
		catch ( Exception e )
		{
			throw new InvalidParameterException( Errors.FAIL_CREATE_FRAGMENT );
		}

		FragmentManager fragmentManager = activity.getFragmentManager();
		dialogFragment.show( fragmentManager, getBackStackName() );

		if ( args == null )
		{ args = new Bundle(); }


		setArgs( args );
		dialogFragment.setArguments( args );
	}

	public void dismiss( Context context, final String tag )
	{
		if ( !( context instanceof Activity ) )
		{
			throw new InvalidParameterException( Errors.FNH_NEED_ACTIVITY );
		}
		Activity activity = (Activity) context;
		Fragment prev = activity.getFragmentManager().findFragmentByTag( getBackStackName() );
		if ( prev != null && prev instanceof DialogFragment )
		{
			DialogFragment df = (DialogFragment) prev;
			df.dismiss();
		}
	}
}
