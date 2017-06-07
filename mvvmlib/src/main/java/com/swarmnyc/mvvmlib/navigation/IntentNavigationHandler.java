package com.swarmnyc.mvvmlib.navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by somya on 6/7/17.
 */

public class IntentNavigationHandler implements NavigationHandler
{

	public IntentNavigationHandler( final Intent intent )
	{
		m_intent = intent;
	}

	Intent m_intent ;

	@Override
	public void navigate( final Context context, final Bundle args )
	{
		context.startActivity( m_intent );
	}

}