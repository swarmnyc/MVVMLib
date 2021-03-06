package com.swarmnyc.mvvmlib.support.navigation;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.swarmnyc.mvvmlib.Errors;
import com.swarmnyc.mvvmlib.navigation.NavigationHandler;
import com.swarmnyc.mvvmlib.support.MvvmFragment;
import java.security.InvalidParameterException;

import static com.swarmnyc.mvvmlib.support.navigation.FragmentNavigationHandler.getLastFragment;

public class DialogFragmentNavigationHandler implements NavigationHandler
{
	private final Class fragmentClass;
	private boolean m_cancelable;


	public <T extends Fragment> DialogFragmentNavigationHandler(
		Class<T> fragmentClass, final boolean cancelable )

	{
		this.fragmentClass = fragmentClass;
		m_cancelable = cancelable;
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
		if ( !( context instanceof AppCompatActivity) )
		{
			throw new InvalidParameterException( Errors.FNH_NEED_ACTIVITY );
		}

		AppCompatActivity activity = (AppCompatActivity) context;
		DialogFragment dialogFragment;
		try
		{
			dialogFragment = (DialogFragment) fragmentClass.newInstance();
			dialogFragment.setCancelable( m_cancelable );
		}
		catch ( Exception e )
		{
			throw new InvalidParameterException( Errors.FAIL_CREATE_FRAGMENT );
		}

		FragmentManager fragmentManager = activity.getSupportFragmentManager();
		dialogFragment.show( fragmentManager, getBackStackName() );

		if ( args == null )
		{ args = new Bundle(); }

		setArgs( args );
		dialogFragment.setArguments( args );
		dialogFragment.setTargetFragment( getLastFragment(fragmentManager), 0);
	}

	public void dismiss( Context context, Bundle args )
	{
		if ( !( context instanceof AppCompatActivity ) )
		{
			throw new InvalidParameterException( Errors.FNH_NEED_ACTIVITY );
		}
		AppCompatActivity activity = (AppCompatActivity) context;
		Fragment prev = activity.getSupportFragmentManager().findFragmentByTag( getBackStackName() );
		if ( prev != null && prev instanceof DialogFragment )
		{
			DialogFragment df = (DialogFragment) prev;
			Fragment targetFragment = df.getTargetFragment();
			if ( targetFragment instanceof MvvmFragment )
			{
				((MvvmFragment) targetFragment).onResult( 0, 0, args );
			}
			df.dismiss();
		}
	}
}
