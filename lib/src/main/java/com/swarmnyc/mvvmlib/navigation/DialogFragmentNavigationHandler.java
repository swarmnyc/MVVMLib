package com.swarmnyc.mvvmlib.navigation;

import android.app.*;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import com.swarmnyc.mvvmlib.Errors;
import com.swarmnyc.mvvmlib.Keys;

import java.security.InvalidParameterException;

public class DialogFragmentNavigationHandler implements NavigationHandler
{
	public static final String FTAG = "fragmentTag";
	public static final String TAG = "NavigationHandler";
	private final int layoutId;
	private final Class fragmentClass;
	private Integer requestCode;

	public <T extends Fragment> DialogFragmentNavigationHandler( Class<T> fragmentClass,
	                                                             @IdRes
	                                                             int layoutId
	)
	{
		this.fragmentClass = fragmentClass;
		this.layoutId = layoutId;
	}

	public <T extends Fragment> DialogFragmentNavigationHandler( Class<T> fragmentClass,
	                                                             @IdRes
	                                                             int layoutId, int requestCode
	)
	{
		this.fragmentClass = fragmentClass;
		this.layoutId = layoutId;
		this.requestCode = requestCode;
	}

	public void setArgs( Bundle args )
	{

	}

	public DialogFragment newDialogFragment() throws IllegalAccessException, InstantiationException
	{
		return (DialogFragment) fragmentClass.newInstance();
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
		DialogFragment fragment;
		try
		{
			fragment = newDialogFragment();
		}
		catch ( Exception e )
		{
			throw new InvalidParameterException( Errors.FAIL_CREATE_FRAGMENT );
		}

		FragmentManager fragmentManager = activity.getFragmentManager();


		if ( requestCode != null )
		{
			Fragment lastFragment = fragmentManager.findFragmentByTag( FTAG );
			fragment.setTargetFragment( lastFragment, requestCode );
		}

//		FragmentTransaction transaction = fragmentManager.beginTransaction();

//		transaction = transaction.show( fragment );
//
//		int id = transaction.commit();

		fragment.show( fragmentManager, FTAG );

		if ( args == null )
		{ args = new Bundle(); }

//		args.putInt( Keys.TRANSACTION_ID, id );

		setArgs( args );
		fragment.setArguments( args );
	}
}
