package com.swarmnyc.mvvmlib;

import android.databinding.ObservableField;

public abstract class MvvmSpinnerViewModel extends MvvmListViewModel
{

	private ObservableField<MvvmViewModel> m_selectedItem = new ObservableField<>();

	// Data Bind to this field
	public ObservableField<MvvmViewModel> getSelectedItem()
	{
		return m_selectedItem;
	}

	// Get & Set this value.
	public MvvmViewModel getSelectedVM()
	{
		return m_selectedItem.get();
	}

	public void setSelectedVM( final MvvmViewModel selection )
	{
		m_selectedItem.set( selection );
		// TODO setup selection
	}

}
