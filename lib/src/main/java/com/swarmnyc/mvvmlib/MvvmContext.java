package com.swarmnyc.mvvmlib;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.swarmnyc.mvvmlib.binding.image.GlideImageBinder;
import com.swarmnyc.mvvmlib.binding.image.ImageBinder;
import com.swarmnyc.mvvmlib.navigation.NavigationManager;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class MvvmContext
{
	//<editor-fold desc="Static">
	private static HashMap<Context, MvvmContext> contextHashMap = new HashMap<>();

	public static MvvmContext getContext( View view )
	{
		Context androidContext = AndroidUtils.getContext( view );

		return getContext( androidContext );
	}

	public static MvvmContext getContext( Context androidContext )
	{
		MvvmContext context = MvvmContext.contextHashMap.get( androidContext );

		if ( context == null )
		{
			context = MvvmContext.contextHashMap.get( androidContext.getApplicationContext() );
		}

		if ( context == null )
		{ throw new RuntimeException( Errors.NO_CONTEXT ); }

		return context;
	}
	//</editor-fold>

	protected NavigationManager navigationManager;
	protected Context androidContext;
	protected ImageBinder imageBinder;
	protected Object dataStore;

	private Map<String, Object> m_dataStore = new HashMap<>();


	protected MvvmContext()
	{
	}

	public MvvmContext( Context androidContext )
	{
		if ( androidContext == null )
		{
			throw new InvalidParameterException( Errors.isNull( "androidContext" ) );
		}
		this.androidContext = androidContext;
		contextHashMap.put( androidContext, this );
	}

	public void destroy()
	{
		contextHashMap.remove( androidContext );
	}

	public MvvmContext getMvvmApplicationContext()
	{
		Context context = androidContext.getApplicationContext();
		if ( androidContext == context )
		{
			return null;
		}
		else
		{
			return contextHashMap.get( context );
		}
	}

	public Context getAndroidContext()
	{
		return androidContext;
	}

	public NavigationManager getNavigationManager()
	{
		return navigationManager;
	}

	/**
	 * Set your custom NavigationManager instead of using DefaultNavigationManager
	 *
	 * @param navigationManager The class of your custom NavigationManager
	 */
	public void setNavigationManager( NavigationManager navigationManager )
	{
		this.navigationManager = navigationManager;
		this.navigationManager.setMvvmContext( this );
	}


	/**
	 * Get a image binder, default is GlideImageBinder
	 *
	 * @return A image binder
	 */
	public ImageBinder getImageBinder()
	{
		if ( imageBinder == null )
		{
			imageBinder = new GlideImageBinder();
		}
		return imageBinder;
	}

	/**
	 * Set your custom image binder
	 *
	 * @param imageBinder your custom image binder object
	 */
	public void setImageBinder( ImageBinder imageBinder )
	{
		this.imageBinder = imageBinder;
	}

	/**
	 * Get your data store object
	 *
	 * @param <T> Your data store type
	 * @return Data store object
	 */
	public <T> T getDataStore()
	{
		return (T) dataStore;
	}

	/**
	 * Set your data store object
	 *
	 * @param dataStore your data store object
	 */
	public void setDataStore( Object dataStore )
	{
		this.dataStore = dataStore;
	}

	/**
	 * Get your data store object
	 *
	 * @param <T> Your data store type
	 * @return Data store object
	 */
	public <T> T resolve(final String key)
	{
		if (!m_dataStore.containsKey( key ))
		{
			return null;
		}
		return (T) m_dataStore.get( key );
	}

	/**
	 * Register your data  object
	 *
	 * @param data your data  object
	 */
	public void register( final String key, Object data )
	{
		this.m_dataStore.put( key, data );
	}


	/**
	 * Close the current Activity or Fragment
	 *
	 * @param result The result send to previous Activity or Fragment
	 * @param args   The args send to previous Activity or Fragment
	 */
	public void close( int result, Bundle args )
	{
		this.navigationManager.closeActivity( result, args );
	}

	/**
	 * Close the current Activity or Fragment
	 */
	public void close()
	{
		this.navigationManager.closeActivity( null, null );
	}


}
