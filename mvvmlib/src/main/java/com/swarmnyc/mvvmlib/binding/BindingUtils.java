package com.swarmnyc.mvvmlib.binding;

import android.databinding.*;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.*;
import com.swarmnyc.mvvmlib.MvvmContext;
import com.swarmnyc.mvvmlib.MvvmListViewModel;
import com.swarmnyc.mvvmlib.R;
import com.swarmnyc.mvvmlib.adapter.DefaultDataBindingAdapter;
import com.swarmnyc.mvvmlib.adapter.DefaultSpinnerBindingAdapter;
import com.swarmnyc.mvvmlib.binding.image.ImageCropType;
import com.swarmnyc.mvvmlib.layout.BaseRecyclerViewLayout;
import com.swarmnyc.mvvmlib.layout.GridRecyclerViewLayout;
import com.swarmnyc.mvvmlib.layout.LinearRecyclerViewLayout;

public class BindingUtils
{

	@BindingConversion
	public static String convertBindableToString( BindableString bindableString )
	{
		return bindableString.get();
	}

	@BindingConversion
	public static boolean convertBindableToBoolean( BindableBoolean bindableBoolean )
	{
		return bindableBoolean.get();
	}


	@BindingConversion
	public static Uri convertStringToUri( BindableString string )
	{
		if (string != null && !string.isEmpty())
		{
			return Uri.parse( string.get() );
		}
		else
		{
			return null;
		}
	}

	@BindingConversion
	public static Uri convertStringToUri( String string )
	{
		if (string != null && !string.isEmpty())
		{
			return Uri.parse( string );
		}
		else
		{
			return null;
		}
	}

	@BindingConversion
	public static Uri convertBindableUriToUri( BindableUri uri )
	{
		if (uri != null)
		{
			return uri.get();
		}
		else
		{
			return null;
		}
	}

	@BindingAdapter( {"mvvm:visibility"} )
	public static void bindVisibility( final View view, final boolean value )
	{
		if (value)
		{
			view.setVisibility( View.VISIBLE );
		}
		else
		{
			view.setVisibility( View.GONE );
		}
	}

	@BindingAdapter( {"mvvm:refreshing"} )
	public static void bindRefreshing( final SwipeRefreshLayout view, final boolean value )
	{
		view.setRefreshing( value );
	}

	@BindingAdapter( {"mvvm:onRefresh"} )
	public static void bindOnRefresh( final SwipeRefreshLayout view, final Runnable runnable )
	{

		view.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener()
		{
			@Override
			public void onRefresh()
			{
				runnable.run();
			}
		} );
	}


	@BindingAdapter( {"mvvm:text"} )
	public static void bindEditText( EditText view, final BindableString observableString )
	{
		Pair<BindableString, TextWatcherAdapter> pair = (Pair) view.getTag( R.id.bound_observable );

		if (pair == null || pair.first != observableString)
		{
			if (pair != null)
			{
				view.removeTextChangedListener( pair.second );
			}

			TextWatcherAdapter watcher = new TextWatcherAdapter()
			{
				@Override
				public void onTextChanged( CharSequence s, int start, int before, int count )
				{
					observableString.set( s.toString() );
				}
			};

			view.setTag( R.id.bound_observable, new Pair<>( observableString, watcher ) );
			view.addTextChangedListener( watcher );
		}

		String newValue = observableString.get();
		if (!view.getText().toString().equals( newValue ))
		{
			view.setText( newValue );
			view.setSelection( newValue.length() );
		}
	}

	@BindingAdapter( {"mvvm:htmlText"} )
	public static void bindHtmlText( final TextView textView, final String text )
	{
		if (!TextUtils.isEmpty( text ))
		{
			textView.setText( Html.fromHtml( text ) );
		}
	}

	@BindingAdapter( {"mvvm:linkTo"} )
	public static void bindLinkTo(
		final View view,
		@StringRes
		final int resId
	)
	{
		view.setOnClickListener( new View.OnClickListener()
		{
			@Override
			public void onClick( View v )
			{
				MvvmContext.getContext( view ).getNavigationManager().linkTo( view.getContext().getString( resId ) );

			}
		} );
	}

	@BindingAdapter( {"mvvm:linkTo"} )
	public static void bindLinkTo( final View view, final String url )
	{
		view.setOnClickListener( new View.OnClickListener()
		{
			@Override
			public void onClick( View v )
			{
				MvvmContext.getContext( view ).getNavigationManager().linkTo( url );
			}
		} );
	}

	@BindingAdapter( {"mvvm:image"} )
	public static void bindImage( final ImageView view, final Uri imageUri )
	{
		if (imageUri != null)
		{
			MvvmContext.getContext( view ).getImageBinder().bind( view, imageUri, ImageCropType.Center, 0 );
		}
	}

	@BindingAdapter( {"mvvm:image", "mvvm:imageError"} )
	public static void bindImage(
		final ImageView view,
		final Uri imageUri,
		@DrawableRes
		final int resId
	)
	{
		if (imageUri != null)
		{
			MvvmContext.getContext( view ).getImageBinder().bind( view, imageUri, ImageCropType.Center, resId );
		}
	}

	@BindingAdapter( {"mvvm:image", "mvvm:imageCrop"} )
	public static void bindImage( final ImageView view, final Uri imageUri, final ImageCropType type )
	{
		if (imageUri != null)
		{
			MvvmContext.getContext( view ).getImageBinder().bind( view, imageUri, type, 0 );
		}
	}

	@BindingAdapter( {"mvvm:image", "mvvm:imageCrop", "mvvm:imageError"} )
	public static void bindImage(
		final ImageView view,
		final Uri imageUri,
		final ImageCropType type,
		@DrawableRes
		final int resId
	)
	{
		if (imageUri != null)
		{
			MvvmContext.getContext( view ).getImageBinder().bind( view, imageUri, type, resId );
		}
	}

	@BindingAdapter( {"mvvm:drawable"} )
	public static void bindDrawable(
		final ImageView view,
		final @DrawableRes
			int redId
	)
	{
		view.setImageResource( redId );
	}

	//    @BindingAdapter({"mvvm:list", "mvvm:itemLayoutId"})
	//    public static void bindList(final RecyclerView view, final MvvmListViewModel listViewModel, final @LayoutRes
	//    int layoutId) {
	//        LinearRecyclerViewLayout layout = LinearRecyclerViewLayout.newInstance();
	//        layout.setViewLayout(view);
	//
	//        RecyclerView.Adapter adapter = new DataBindingRecyclerViewAdapter(
	//                view.getContext(), listViewModel, layoutId);
	//        view.setAdapter(adapter);
	//    }

	@BindingAdapter( {"mvvm:list", "mvvm:itemResId", "mvvm:numColumns"} )
	public static void bindList(
		final RecyclerView view, final MvvmListViewModel listViewModel, final int numColumns, final int itemResId
	)
	{
		BaseRecyclerViewLayout layout;
		if (numColumns > 1)
		{
			layout = new GridRecyclerViewLayout( numColumns );
		}
		else
		{
			layout = new LinearRecyclerViewLayout();
		}
		layout.setViewLayout( view );

		RecyclerView.Adapter adapter = new DefaultDataBindingAdapter( view.getContext(), listViewModel, itemResId );
		view.setAdapter( adapter );
	}

	@BindingAdapter( {"mvvm:list", "mvvm:itemResId"} )
	public static void bindList(
		final RecyclerView view, final MvvmListViewModel listViewModel, final int itemResId
	)
	{
		LinearRecyclerViewLayout layout = new LinearRecyclerViewLayout();
		layout.setViewLayout( view );

		DefaultDataBindingAdapter adapter = new DefaultDataBindingAdapter( view.getContext(),
		                                                                   listViewModel,
		                                                                   itemResId
		);
		view.setAdapter( adapter );
	}

	@BindingAdapter( {"mvvm:items", "mvvm:selectedItem", "mvvm:selectedResId", "mvvm:itemResId"} )
	public static void bindSpinner(
		final Spinner view,
		final ObservableList observableList,
		final ObservableField selectedItem,
		final int selectedResId,
		final int itemResId
	)
	{

		DefaultSpinnerBindingAdapter adapter = new DefaultSpinnerBindingAdapter( view.getContext(),
		                                                                         observableList,
		                                                                         selectedResId,
		                                                                         itemResId
		);
		view.setAdapter( adapter );

		// setup the initial selection
		if (selectedItem != null && observableList != null)
		{
			view.setSelection( observableList.indexOf( selectedItem.get() ) );


			// Set up selection changes to update UI
			selectedItem.addOnPropertyChangedCallback( new Observable.OnPropertyChangedCallback()
			{
				@Override
				public void onPropertyChanged( final Observable sender, final int propertyId )
				{
					if (null != selectedItem.get())
					{
						view.setSelection( observableList.indexOf( selectedItem.get() ) );
					}
				}
			} );
		}


		// Set up selection changes to update VM
		view.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(
				final AdapterView<?> parent, final View view, final int position, final long id
			)
			{
				selectedItem.set( observableList.get( position ) );
			}

			@Override
			public void onNothingSelected( final AdapterView<?> parent )
			{
				selectedItem.set( null );
			}
		} );
	}


}
