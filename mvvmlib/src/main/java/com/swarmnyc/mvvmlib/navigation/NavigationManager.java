package com.swarmnyc.mvvmlib.navigation;

import android.content.Context;
import android.os.Bundle;
import com.swarmnyc.mvvmlib.FragmentWrapper;
import com.swarmnyc.mvvmlib.MvvmContext;

public interface NavigationManager
{
	void setMvvmContext( MvvmContext mvvmContext );

	// TODO let' smake this the ViewModel class.
	boolean navigateTo( Class path );

	boolean navigateTo( Class path, Bundle args );

	boolean navigateTo( Context context, Class path, Bundle args );

	void navigateBack();

	void dismiss( final Class lowerCasePath );

	void closeActivity( Integer result, Bundle args );

	void closeFragment( FragmentWrapper fragment, Integer targetRequestCode, Integer result, Bundle args );

	NavigationManager add( Class vmClass, NavigationHandler handler );

	void remove( Class vmClass );

	void linkTo( String url );
}

