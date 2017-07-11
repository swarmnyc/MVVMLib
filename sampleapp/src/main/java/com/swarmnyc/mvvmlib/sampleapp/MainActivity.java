package com.swarmnyc.mvvmlib.sampleapp;

import android.support.annotation.LayoutRes;
import com.swarmnyc.mvvmlib.navigation.NavigationManager;
import com.swarmnyc.mvvmlib.sampleapp.ui.fragment.FragmentDataDialog;
import com.swarmnyc.mvvmlib.sampleapp.ui.fragment.FragmentHello;
import com.swarmnyc.mvvmlib.sampleapp.ui.fragment.FragmentHome;
import com.swarmnyc.mvvmlib.sampleapp.ui.fragment.FragmentMultiItemList;
import com.swarmnyc.mvvmlib.sampleapp.ui.fragment.FragmentPassingDataDemo;
import com.swarmnyc.mvvmlib.sampleapp.ui.fragment.FragmentSimpleList;
import com.swarmnyc.mvvmlib.sampleapp.ui.fragment.FragmentSpinnerDemo;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.DataDialogViewModel;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.HelloViewModel;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.HomeViewModel;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.MainViewModel;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.MultiItemListViewModel;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.PassingDataDemoViewModel;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.SimpleListViewModel;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.SpinnerDemoViewModel;
import com.swarmnyc.mvvmlib.support.MvvmActivity;
import com.swarmnyc.mvvmlib.support.navigation.DialogFragmentNavigationHandler;
import com.swarmnyc.mvvmlib.support.navigation.FragmentNavigationHandler;

public class MainActivity extends MvvmActivity<MainViewModel>
{
	@Override
	@LayoutRes
	public int getLayoutResourceId()
	{
		return R.layout.activity_main;
	}


	@Override
	protected void buildNavigation( final NavigationManager manager )
	{
		super.buildNavigation( manager );


		// Home
		manager.add( HomeViewModel.class,
		             new FragmentNavigationHandler( FragmentHome.class, R.id.fragment_container )
		);

		// Hello
		manager.add( HelloViewModel.class,
		             new FragmentNavigationHandler( FragmentHello.class, R.id.fragment_container )
		);

		// Spinner
		manager.add( SpinnerDemoViewModel.class,
		             new FragmentNavigationHandler( FragmentSpinnerDemo.class, R.id.fragment_container )
		);

		// Simple List
		manager.add( SimpleListViewModel.class,
		             new FragmentNavigationHandler( FragmentSimpleList.class, R.id.fragment_container )
		);

		// Multi Item List
		manager.add( MultiItemListViewModel.class,
		             new FragmentNavigationHandler( FragmentMultiItemList.class, R.id.fragment_container )
		);

		// Passing data between Fragment and Dialog demo.
		manager.add(PassingDataDemoViewModel.class,
					new FragmentNavigationHandler(FragmentPassingDataDemo.class, R.id.fragment_container));
		manager.add(DataDialogViewModel.class,
					new DialogFragmentNavigationHandler(FragmentDataDialog.class, true));


		manager.navigateTo( HomeViewModel.class );
	}
}
