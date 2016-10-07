package com.swarmnyc.mvvmlib.navigation;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by somya on 10/6/16.
 */

public class DefaultNotificationProvider implements INotificationProvider
{
	private final Context m_context;

	public DefaultNotificationProvider( Context context )
	{
		m_context = context;
	}

	@Override
	public void showNotification( final String message )
	{
		Toast.makeText( m_context, message, Toast.LENGTH_SHORT ).show();
	}


}
