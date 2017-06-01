package com.swarmnyc.mvvmlib.sampleapp.ui;

import com.swarmnyc.mvvmlib.navigation.NavigationManager;
import com.swarmnyc.mvvmlib.sampleapp.R;
import com.swarmnyc.mvvmlib.sampleapp.ui.fragment.FragmentSimpleList;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.FragmentNavigationDemoViewModel;
import com.swarmnyc.mvvmlib.sampleapp.viewmodel.TextListViewModel;
import com.swarmnyc.mvvmlib.support.MvvmActivity;
import com.swarmnyc.mvvmlib.support.navigation.FragmentNavigationHandler;

public class FragmentNavigationDemoActivity extends MvvmActivity<FragmentNavigationDemoViewModel>
{

	@Override
	protected int getLayoutResourceId()
	{
		return R.layout.activity_fragment_demo_navigation;
	}

	@Override
	protected void buildNavigation( final NavigationManager manager )
	{
		super.buildNavigation( manager );

		// Settings
		manager.add(
			TextListViewModel.class,
			new FragmentNavigationHandler( FragmentSimpleList.class, R.id.fragment_container )
		);

		manager.navigateTo( TextListViewModel.class );
	}
}
